package View.Listener;

import Business.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
//OBSERVER PATTERN
//ConcreteObserver
public class FeedbacksPanelListener extends MouseAdapter {

    List<FeedBack> lista;

    public FeedbacksPanelListener(List<FeedBack> lista) {
        this.lista = lista;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();
        int row = table.rowAtPoint(point);
        int column = table.columnAtPoint(point);
        try {
            int idArticolo = (int)table.getValueAt(row,3);
            int idAcquisto = (int)table.getValueAt(row,0);
            String campo = (String) table.getValueAt(row, column);

            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Vedi Commento".equals(campo)) {
                Acquisto a = AcquistoBusiness.getInstance().getOneAcquisto(idAcquisto);
                String[] options = {"Invia", "Cancella"};
                JTextArea ta = new JTextArea(20, 40);
                FeedBack feedBack = FeedbackBusiness.getInstance().getByArticoloCliente(idArticolo, a.getIdCliente());
                ta.setText(feedBack.getCommento());
                if (JOptionPane.showOptionDialog(null, new JScrollPane(ta), "Messaggio", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]) == JOptionPane.YES_NO_OPTION) {
                    feedBack.setCommento(ta.getText());
                    FeedbackBusiness.getInstance().updateCommento(feedBack);
                    JOptionPane.showMessageDialog(null, "Risposta inviata","Punto vendita", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (ClassCastException | IndexOutOfBoundsException ignored) {}
    }

}
