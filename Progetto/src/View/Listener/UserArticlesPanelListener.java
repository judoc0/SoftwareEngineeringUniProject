package View.Listener;

import Business.*;
import Model.*;
import View.AppFrame;
import View.UsersArticlesPanel;
import View.UsersListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;
//OBSERVER PATTERN
//ConcreteObserver
public class UserArticlesPanelListener extends MouseAdapter implements ActionListener {

    AppFrame appFrame;
    Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
    ListaArticoli listaArticoli;

    public UserArticlesPanelListener(AppFrame appFrame, ListaArticoli listaArticoli) {
        this.appFrame = appFrame;
        this.listaArticoli = listaArticoli;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("corrente".equals(cmd)) {  //SELEZIONA COME LISTA CORRENTE
            SessionManager.getInstance().setCurrentList(ListaArticoliBusiness.getInstance().getbyClienteAndNome(u.getIdUtente(), listaArticoli.getNome()));
            JOptionPane.showMessageDialog(appFrame,
                    "Lista corrente: "+SessionManager.getInstance().getCurrentList().getNome(),
                    "Lista acquisto",
                    JOptionPane.INFORMATION_MESSAGE);
            appFrame.setCurrentMainPanel(new UsersListPanel(appFrame));
        }

        if ("promemoria".equals(cmd)) {
            //CREA PROMEMORIA
            List<ArticoliLista> lista = Lista_has_ArticoloBusiness.getInstance().getArticoli(listaArticoli.getIdLista());
            Documento doc = new DocumentoListaAcquisto(lista, new PdfBoxAPI());
            doc.creaDocumento(u.getEmail());
            JOptionPane.showMessageDialog(null, "Promemoria inviato", "Lista Acquisto", JOptionPane.INFORMATION_MESSAGE);
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
                //RIMUOVI DALLA LISTA
                Integer[] array = new Integer[quantita];
                for (int i = 1; i <= quantita; i++) {
                    array[i-1] = i;
                }
                int qdarimuovere = (int) JOptionPane.showInputDialog(null, "Quantita da rimuovere", "Rimozione Articoli", JOptionPane.PLAIN_MESSAGE, null, array, array[array.length-1]);
                ArticoliLista articoliLista = Lista_has_ArticoloBusiness.getInstance().getOneArticoliLista(listaArticoli.getIdLista(),idArticolo);
                articoliLista.setQuantita(qdarimuovere);
                Lista_has_ArticoloBusiness.getInstance().removeQuantity(articoliLista);
                JOptionPane.showMessageDialog(null, ArticoloBusiness.getInstance().getOneProducts(idArticolo).getNome() + " rimossi: " + qdarimuovere, "Rimozione Articoli", JOptionPane.INFORMATION_MESSAGE);
                appFrame.setCurrentMainPanel(new UsersArticlesPanel(appFrame, listaArticoli));
            }

            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Lascia Feedback".equals(campo)) {

                if (FeedbackBusiness.getInstance().CommentoExists(idArticolo, u.getIdUtente())) {
                    //SE ESISTE IL COMMENTO
                    FeedBack esistente = FeedbackBusiness.getInstance().getByArticoloCliente(idArticolo, u.getIdUtente());
                    String[] options = {"Sovrascrivi","Elimina"};
                    int res = JOptionPane.showOptionDialog(null, "Hai già lasciato il feedback per questo articolo:\n"+esistente.getCommento()+"\nPunteggio: "+esistente.getPunteggio(), "Feedback", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                        if (res == 0) {
                            //SOVRASCRIVI
                            String[] options1 = {"Invia", "Cancella"};
                            JTextArea ta = new JTextArea(20, 40);
                            ta.setText(FeedbackBusiness.getInstance().getByArticoloCliente(idArticolo, esistente.getIdCliente()).getCommento());

                            if (JOptionPane.showOptionDialog(null, new JScrollPane(ta), "Messaggio", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]) == JOptionPane.YES_NO_OPTION) {
                                Integer[] array = new Integer[5];
                                for (int i = 1; i <= array.length; i++) {
                                    array[i - 1] = i;
                                }
                                int gradimento = JOptionPane.showOptionDialog(null, "Indica il tuo punteggio", "Feedback", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, array[0]) + 1;
                                if (gradimento >= 1) {
                                    esistente.setCommento(ta.getText());
                                    FeedbackBusiness.getInstance().updateCommento(esistente);
                                    JOptionPane.showMessageDialog(null, "Feedback modificato", "Feedback", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }

                        }
                        //ELIMINA
                        if (res == 1) {
                            FeedbackBusiness.getInstance().removeCommento(esistente.getId());
                        JOptionPane.showMessageDialog(null, "Feedback eliminato", "Feedback", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    //SE NON ESISTE IL COMMENTO
                    String[] options1 = {"Invia", "Cancella"};
                    JTextArea ta = new JTextArea(20, 40);
                    if (JOptionPane.showOptionDialog(null, new JScrollPane(ta), "Messaggio", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]) == JOptionPane.YES_NO_OPTION) {

                        Integer[] array = new Integer[5];
                        for (int i = 1; i <= array.length; i++) {
                            array[i - 1] = i;
                        }
                        int gradimento = JOptionPane.showOptionDialog(null, "Indica il tuo punteggio", "Feedback", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, array[0]) + 1;
                        if (gradimento >= 1) {
                            int idAcquisto = ArticoloAcquistatoBusiness.getInstance().getByArticoloAndCliente(idArticolo, u.getIdUtente()).getIdAcquisto();
                            Date date = new Date(System.currentTimeMillis());
                            IFeedbackFactory factoryMethodProvider = new IFeedbackFactory();
                            IFeedback f = factoryMethodProvider.getIFeedback("FEEDBACK");  //FACTORY METHOD
                            f.setCommento(ta.getText());
                            f.setPunteggio(gradimento);
                            f.setIdAcquisto(idAcquisto);
                            f.setIdArticolo(idArticolo);
                            f.setIdCliente(u.getIdUtente());
                            f.setDate(date);
                            FeedbackBusiness.getInstance().addCommento((FeedBack)f);  //FACTORY METHOD
                            JOptionPane.showMessageDialog(null, "Feedback inviato", "Feedback", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        } catch (ClassCastException | IndexOutOfBoundsException | NullPointerException ignored) {}
    }
}
