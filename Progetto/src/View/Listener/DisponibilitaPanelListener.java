package View.Listener;

import Business.DisponibilitaBusiness;
import View.AppFrame;
import View.DisponibilitaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//OBSERVER PATTERN
//ConcreteObserver
public class DisponibilitaPanelListener extends MouseAdapter{

    AppFrame appFrame;

    public DisponibilitaPanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }


    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();
        int row = table.rowAtPoint(point);
        int column = table.columnAtPoint(point);
        try {
            int idArticolo = (int)table.getValueAt(row,0);
            String nome = (String) table.getValueAt(row,1);
            int quantita = (int)table.getValueAt(row,3);
            String campo = (String) table.getValueAt(row, column);

            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Rifornisci".equals(campo)) {
                //RIFORNISCI
                Integer[] array = new Integer[100 - quantita];
                for (int i = 1; i <= array.length; i++) array[i - 1] = i;

                Integer qdaAggiungere = (Integer) JOptionPane.showInputDialog(null, "Quantità da rifonire: ", "Disponibilita", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
                if (qdaAggiungere != null) {
                    DisponibilitaBusiness.getInstance().rifornimento(idArticolo, qdaAggiungere);
                    appFrame.setCurrentMainPanel(new DisponibilitaPanel(appFrame));
                    JOptionPane.showMessageDialog(appFrame,
                            "Riforniti " + qdaAggiungere + " " + nome,
                            "Disponibilità",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (ClassCastException | IndexOutOfBoundsException ignored) {}
    }

}
