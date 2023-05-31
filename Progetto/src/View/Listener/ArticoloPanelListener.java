package View.Listener;

import Business.*;
import Model.*;
import View.AppFrame;
import View.IArticoloPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
//OBSERVER PATTERN
//ConcreteObserver
public class ArticoloPanelListener implements ActionListener {

    AppFrame appFrame;
    Articolo a;
    Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
    PuntoVendita pu = SessionManager.getInstance().getCurrentPuntoVendita();
    ListaArticoli lista;


    public ArticoloPanelListener(AppFrame appFrame, IArticoloPanel iArticoloPanel) {
        this.appFrame = appFrame;
        a = iArticoloPanel.getArticolo();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        Disponibilita d = DisponibilitaBusiness.getInstance().getOneDisponibilita(a.getId(), pu.getMagazzino().getIdMagazzino());
        int quantita = 0;

        if (u != null) {
            //SE UTENTE è LOGGATO
            if (UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.CLIENT)) {
                if (ArticoloBusiness.getInstance().typeArticolo(a, ArticoloBusiness.TipoArticolo.PRODOTTO))
                    quantita = d.getQuantita();

                try {
                    if ("add".equals(cmd)) {

                        if (SessionManager.getInstance().getCurrentList() == null) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date date = new Date();

                            IListaArticoliFactory factoryMethodProvider = new IListaArticoliFactory();
                            IListaArticoli iListaArticoli = factoryMethodProvider.getIListaArticoli("LISTAARTICOLI");
                            iListaArticoli.setIdCliente(u.getIdUtente());
                            iListaArticoli.setNome(formatter.format(date) + " " + pu.getCitta());
                            iListaArticoli.setStato(ListaArticoli.StatoLista.NON_PAGATA);
                            iListaArticoli.setIdPuntoVendita(pu.getIdPuntoVendita());

                            NewListaResponse ras = ListaArticoliBusiness.getInstance().newLista((ListaArticoli) iListaArticoli);
                            lista = ListaArticoliBusiness.getInstance().getbyClienteAndNome(ras.getListaArticoli().getIdCliente(), ras.getListaArticoli().getNome());
                            SessionManager.getInstance().setLoginList(lista);
                            SessionManager.getInstance().setCurrentList(lista);

                        }
                        lista = SessionManager.getInstance().getCurrentList();

                        if (ArticoloBusiness.getInstance().typeArticolo(a, ArticoloBusiness.TipoArticolo.PRODOTTO)) {
                            //SE PRODOTTO GIÀ PRESENTE NELLA LISTA
                            if (Lista_has_ArticoloBusiness.getInstance().getOneArticoliLista(lista.getIdLista(), a.getId()) != null) {
                                // disponibilità - nel carrello
                                int carrello = Lista_has_ArticoloBusiness.getInstance().getOneArticoliLista(lista.getIdLista(), a.getId()).getQuantita();
                                int total2 = quantita - carrello;
                                if (total2 == 0)   JOptionPane.showMessageDialog(null,
                                        "Disponibilità max raggiunta nella lista acquisto",
                                        "Lista acquisto",
                                        JOptionPane.ERROR_MESSAGE);
                                else {
                                Integer[] array = new Integer[total2];
                                for (int i = 1; i <= total2; i++) {
                                    array[i - 1] = i;
                                }

                                String qdaAggiungere = ""+JOptionPane.showInputDialog(null, "Quantità presente nel carrello: " + carrello + "\nQuantità da aggiungere:", "Lista Acquisto", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
                                    if (!qdaAggiungere.equals("null")) {
                                        ArticoliLista articoliLista = Lista_has_ArticoloBusiness.getInstance().getOneArticoliLista(lista.getIdLista(), a.getId());
                                        articoliLista.setQuantita(Integer.parseInt(qdaAggiungere));
                                        Lista_has_ArticoloBusiness.getInstance().addQuantity(articoliLista);
                                        JOptionPane.showMessageDialog(null,
                                                qdaAggiungere + " " + a.getNome() + " aggiunti alla lista",
                                                "Lista acquisto",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }else {
                                //SE PRODOTTO NON PRESENTE NELLA LISTA
                                Integer[] array = new Integer[quantita];
                                for (int i = 1; i <= quantita; i++) {
                                    array[i - 1] = i;
                                }
                                String qdaAggiungere = ""+JOptionPane.showInputDialog(null, "Quantità da aggiungere:", "Lista Acquisto", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
                                if (!qdaAggiungere.equals("null")) {
                                    ILista_has_articoloFactory iLista_has_articoloFactory = new ILista_has_articoloFactory();
                                    ILista_has_articolo lista_has_articolo = iLista_has_articoloFactory.getILista_has_articolo("ARTICOLILISTA");
                                    lista_has_articolo.setIdArticolo(a.getId());
                                    lista_has_articolo.setIdLista(lista.getIdLista());
                                    lista_has_articolo.setQuantita(Integer.parseInt(qdaAggiungere));
                                    Lista_has_ArticoloBusiness.getInstance().addArticolo((ArticoliLista) lista_has_articolo);
                                    JOptionPane.showMessageDialog(null,
                                            qdaAggiungere + " " + a.getNome() + " aggiunti alla lista",
                                            "Lista acquisto",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            // SE IL SERVIZIO GIÀ PRESENTE NELLA LISTA
                            if (Lista_has_ArticoloBusiness.getInstance().getOneArticoliLista(lista.getIdLista(), a.getId())  != null) {
                                JOptionPane.showMessageDialog(null,
                                        "Servizio già aggiunto alla lista",
                                        "Errore lista",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                ILista_has_articoloFactory iLista_has_articoloFactory = new ILista_has_articoloFactory();
                                ILista_has_articolo lista_has_articolo = iLista_has_articoloFactory.getILista_has_articolo("ARTICOLILISTA");
                                lista_has_articolo.setIdArticolo(a.getId());
                                lista_has_articolo.setIdLista(lista.getIdLista());
                                lista_has_articolo.setQuantita(1);
                                Lista_has_ArticoloBusiness.getInstance().addArticolo((ArticoliLista) lista_has_articolo);
                                JOptionPane.showMessageDialog(null,
                                        a.getNome() + " aggiunto alla lista",
                                        "Lista acquisto",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    }

                    if ("prenota".equals(cmd)) {
                        //PRENOTA
                        Integer[] array = new Integer[10];
                        for (int i = 1; i <= array.length; i++) {
                            array[i - 1] = i;
                        }

                        String qdaAggiungere = "" + JOptionPane.showInputDialog(null, "Quantità da prenotare:", "Prenotazione", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
                        if (!qdaAggiungere.equals("null")) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date date = new Date();

                            IListaArticoliFactory factoryMethodProvider = new IListaArticoliFactory();
                            IListaArticoli iListaArticoli = factoryMethodProvider.getIListaArticoli("LISTAARTICOLI");
                            iListaArticoli.setIdCliente(u.getIdUtente());
                            iListaArticoli.setNome("Prenotazione " + pu.getCitta() + " " + formatter.format(date));
                            iListaArticoli.setStato(ListaArticoli.StatoLista.PAGATA);
                            iListaArticoli.setIdPuntoVendita(pu.getIdPuntoVendita());
                            iListaArticoli.setData(formatter.format(date)+"");
                            ListaArticoliBusiness.getInstance().newLista((ListaArticoli) iListaArticoli);

                            ListaArticoli pre = ListaArticoliBusiness.getInstance().getbyClienteAndNome(iListaArticoli.getIdCliente(), iListaArticoli.getNome());
                            ILista_has_articoloFactory iLista_has_articoloFactory = new ILista_has_articoloFactory();
                            ILista_has_articolo lista_has_articolo = iLista_has_articoloFactory.getILista_has_articolo("ARTICOLILISTA");
                            lista_has_articolo.setIdArticolo(a.getId());
                            lista_has_articolo.setIdLista(pre.getIdLista());
                            lista_has_articolo.setQuantita(Integer.parseInt(qdaAggiungere));
                            Lista_has_ArticoloBusiness.getInstance().addArticolo((ArticoliLista) lista_has_articolo);
                            JOptionPane.showMessageDialog(null,
                                    qdaAggiungere + " " + a.getNome() + " prenotati",
                                    "Prenotazione",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                } catch (NullPointerException f) {
                    System.out.println(f.getMessage());
                }

            }
        } else JOptionPane.showMessageDialog(null,
                "Devi fare prima il login",
                "Lista Acquisto",
                JOptionPane.INFORMATION_MESSAGE);
    }
}