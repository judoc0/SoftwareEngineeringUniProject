package View.Listener;

import Business.*;
import Model.*;
import View.AllPuntiVenditaPanel;
import View.AppFrame;
import View.ListaUtentiPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.util.List;
//OBSERVER PATTERN
//ConcreteObserver
public class ListaUtentiPanelListener extends MouseAdapter implements ActionListener {

    AppFrame appFrame;
    List<Cliente> lista;

    public ListaUtentiPanelListener(AppFrame appFrame, List<Cliente> lista) {
        this.appFrame = appFrame;
        this.lista = lista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        // INVIA MESSAGGIO A TUTTI I CLIENTI
        if ("invia".equals(cmd)) {
            String[] options = {"Invia", "Cancella"};
            JTextArea ta = new JTextArea(20, 40);
            if (JOptionPane.showOptionDialog(null, new JScrollPane(ta), "Messaggio", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]) == JOptionPane.YES_NO_OPTION) {

                NotificaFactory factoryMethodProvider = new NotificaFactory();
                for (Cliente c : lista) {
                    Notifica n = factoryMethodProvider.getNotifica(ta.getText(), c);
                    NotificaBusiness.getInstance().sendNotifica(n);
                }

                JOptionPane.showMessageDialog(null, "Messaggio inviato a tutti i clienti", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
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

            int id = (int)table.getValueAt(row,0);
            String username = table.getValueAt(row, 1)+"";
            String campo = table.getValueAt(row, column)+"";


            //GESTISCI CLIENTE
            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Gestisci cliente".equals(campo)) {
                String[] options = {"Attiva","Disabilita", "Elimina", "Invia messaggio"};
                int res = JOptionPane.showOptionDialog(null, "Username cliente: "+username, "Gestisci cliente", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                Cliente c = ClienteBusiness.getInstance().getCliente(id);
                switch (res) {
                    case 0 -> { //ATTIVA
                        c.setStatoCliente(Cliente.StatoCliente.ATTIVO);
                        ClienteBusiness.getInstance().updateStatoCliente(c);
                        JOptionPane.showMessageDialog(null, "Cliente attivato", "Gestisci cliente", JOptionPane.INFORMATION_MESSAGE);
                    }

                    case 1 -> { //DISABILITA
                        c.setStatoCliente(Cliente.StatoCliente.DISABILITATO);
                        ClienteBusiness.getInstance().updateStatoCliente(c);
                        JOptionPane.showMessageDialog(null, "Cliente disabilitato", "Gestisci cliente", JOptionPane.INFORMATION_MESSAGE);
                    }

                    case 2 -> { //ELIMINA
                        UtenteBusiness.getInstance().removeById(UtenteBusiness.getInstance().getById(id));
                        JOptionPane.showMessageDialog(null, "Cliente eliminato", "Gestisci cliente", JOptionPane.INFORMATION_MESSAGE);
                    }

                    case 3 -> { //INVIA MESSAGGIO

                        String[] options1 = {"Invia", "Cancella"};
                        JTextArea ta = new JTextArea(20, 40);

                        if (JOptionPane.showOptionDialog(null, new JScrollPane(ta), "Messaggio", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]) == JOptionPane.YES_NO_OPTION) {
                            NotificaFactory factoryMethodProvider = new NotificaFactory();
                            Notifica n = factoryMethodProvider.getNotifica(ta.getText(), c);
                            NotificaBusiness.getInstance().sendNotifica(n);

                            JOptionPane.showMessageDialog(null, "Messaggio inviato", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }
                appFrame.setCurrentMainPanel(new ListaUtentiPanel(appFrame));
            }
        } catch (ClassCastException | IndexOutOfBoundsException ignored) {}
    }

}
