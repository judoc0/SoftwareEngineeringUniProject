package View.Listener;

import Business.*;
import Model.*;
import View.AppFrame;
import View.CassaPanel;
import View.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
//OBSERVER PATTERN
//ConcreteObserver
public class CassaPanelListener extends MouseAdapter implements ActionListener {

    AppFrame appFrame;
    Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
    ListaArticoli listaArticoli = SessionManager.getInstance().getCurrentList();
    List<ArticoliLista> lista;

    public CassaPanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;
        lista = Lista_has_ArticoloBusiness.getInstance().getArticoli(listaArticoli.getIdLista());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("cassa".equals(cmd)) {
            //PAGA
            if (lista.isEmpty()) JOptionPane.showMessageDialog(appFrame, "La tua lista acquisto è vuota", "Lista Acquisto", JOptionPane.ERROR_MESSAGE);

            else {
                int res = JOptionPane.showConfirmDialog(null, "Vuoi confermare l'acquisto", "Cassa", JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                    ListaArticoliBusiness.getInstance().pagaLista(listaArticoli);

                    IAcquistoFactory factoryMethodProvider = new IAcquistoFactory();  //FACTORY METHOD client
                    IAcquisto acquisto = factoryMethodProvider.getIAcquisto("ACQUISTO");
                    acquisto.setIdCliente(u.getIdUtente());
                    acquisto.setIdPuntoVendita(SessionManager.getInstance().getCurrentPuntoVendita().getIdPuntoVendita());
                    AcquistoBusiness.getInstance().addAcquisto((Acquisto) acquisto);

                    int idAcquisto = AcquistoBusiness.getInstance().getLastAcquisto().getIdAcquisto();
                    for (ArticoliLista a : lista) {
                        if (ArticoloBusiness.getInstance().typeArticolo(a.getArticolo(), ArticoloBusiness.TipoArticolo.PRODOTTO)) {
                            DisponibilitaBusiness.getInstance().removeQuantityMagazzino(a.getIdArticolo(), a.getQuantita(), PuntoVenditaBusiness.getInstance().getOnePuntoVendita(listaArticoli.getIdPuntoVendita()).getMagazzino().getIdMagazzino());
                        }

                        IArticoloAcquistatoFactory factoryMethodProvider1 = new IArticoloAcquistatoFactory();  //FACTORY METHOD client
                        IArticoloAcquistato articoloAcquistato = factoryMethodProvider1.getIArticoloAcquistato("ARTICOLOACQUISTATO");
                        articoloAcquistato.setIdAcquisto(idAcquisto);
                        articoloAcquistato.setIdArticolo(a.getIdArticolo());
                        articoloAcquistato.setIdCliente(u.getIdUtente());
                        ArticoloAcquistatoBusiness.getInstance().addArticoloAcquistato((ArticoloAcquistato) articoloAcquistato);
                    }

                    JOptionPane.showMessageDialog(null, "Acquisto effettuato", "Cassa", JOptionPane.INFORMATION_MESSAGE);

                    appFrame.setCurrentMainPanel(new WelcomePanel(appFrame));
                    SessionManager.getInstance().setCurrentList(null);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();
        int row = table.rowAtPoint(point);
        int column = table.columnAtPoint(point);

        try {
            int idArticolo = (int)table.getValueAt(row, 0);
            int quantita = (int)table.getValueAt(row, 3);
            String campo = (String) table.getValueAt(row, column);

            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Rimuovi".equals(campo)) {
                //RIMUOVI ARTICOLO
                Integer[] array = new Integer[quantita];
                for (int i = 1; i <= quantita; i++) {
                    array[i-1] = i;
                }
                int qdarimuovere = (int) JOptionPane.showInputDialog(null, "Quantita da rimuovere", "Rimozione Articoli", JOptionPane.PLAIN_MESSAGE, null, array, array[array.length-1]);
                ArticoliLista articoliLista = Lista_has_ArticoloBusiness.getInstance().getOneArticoliLista(listaArticoli.getIdLista(), idArticolo);
                articoliLista.setQuantita(qdarimuovere);
                Lista_has_ArticoloBusiness.getInstance().removeQuantity(articoliLista);
                appFrame.setCurrentMainPanel(new CassaPanel(appFrame));

                JOptionPane.showMessageDialog(null, ArticoloBusiness.getInstance().getOneProducts(idArticolo).getNome() + " rimossi: " + qdarimuovere, "Rimozione Articoli", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (ClassCastException | IndexOutOfBoundsException | NullPointerException ignored) {}
    }

}
