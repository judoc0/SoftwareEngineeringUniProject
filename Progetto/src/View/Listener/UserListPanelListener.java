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
//OBSERVER PATTERN
//ConcreteObserver
public class UserListPanelListener extends MouseAdapter implements ActionListener {

    AppFrame appFrame;
    Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
    PuntoVendita pu = SessionManager.getInstance().getCurrentPuntoVendita();

    public UserListPanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("creaLista".equals(cmd)) {
            //CREAZIONE LISTA
            String input = ""+JOptionPane.showInputDialog(null, "Inserisci il nome della nuova lista", "Lista Acquisto", JOptionPane.PLAIN_MESSAGE);
            if (!" null".equals(input) && !"".equals(input)) {

                IListaArticoliFactory factoryMethodProvider = new IListaArticoliFactory();
                IListaArticoli l = factoryMethodProvider.getIListaArticoli("LISTAARTICOLI");  //FACTORY METHOD

                l.setIdCliente(u.getIdUtente());
                l.setNome(input);
                l.setStato(ListaArticoli.StatoLista.NON_PAGATA);
                l.setIdPuntoVendita(pu.getIdPuntoVendita());

                NewListaResponse res = ListaArticoliBusiness.getInstance().newLista((ListaArticoli) l);  //RISPOSTA
                JOptionPane.showMessageDialog(null, res.getMessage(),"Creazione lista", res.getjOptionPane());

                appFrame.setCurrentMainPanel(new UsersListPanel(appFrame));
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
            String nome = (String)table.getValueAt(row,0);
            String campo = (String) table.getValueAt(row, column);

            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Gestisci lista".equals(campo)) {

                String[] options = {"Vedi gli Articoli","Cambia nome lista","Elimina lista"}; //SE LISTA NON_PAGATA

                int ras = JOptionPane.showOptionDialog(null, "", "Lista acquisto", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                switch (ras){

                    case (0) -> //VEDI ARTICOLI
                            appFrame.setCurrentMainPanel(new UsersArticlesPanel(appFrame, ListaArticoliBusiness.getInstance().getbyClienteAndNome(u.getIdUtente(), nome)));

                    case (1) -> { //CAMBIA NOME
                        String input = JOptionPane.showInputDialog(null, "Inserisci il nuovo nome per lista", "Lista Acquisto", JOptionPane.PLAIN_MESSAGE);
                        if (input != null && !"".equals(input)) {
                            ListaArticoli listaArticoli = ListaArticoliBusiness.getInstance().getbyClienteAndNome(u.getIdUtente(), nome);
                            listaArticoli.setNome(input);
                            NewListaResponse change = ListaArticoliBusiness.getInstance().changeName(listaArticoli);
                            appFrame.setCurrentMainPanel(new UsersListPanel(appFrame));
                            JOptionPane.showMessageDialog(appFrame,
                                    change.getMessage(),
                                    "Lista acquisto",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    case (2) -> { // ELIMINA
                        int res = JOptionPane.showConfirmDialog(null, "Sei sicuro di eliminare la lista " + nome, "Cassa", JOptionPane.YES_NO_OPTION);
                        if (res == 0) {
                            ListaArticoli lista = SessionManager.getInstance().getCurrentList();
                            if (lista != null && lista.getNome().equals(nome)) SessionManager.getInstance().setCurrentList(null);
                            ListaArticoliBusiness.getInstance().removeById(ListaArticoliBusiness.getInstance().getbyClienteAndNome(u.getIdUtente(), nome).getIdLista());
                            appFrame.setCurrentMainPanel(new UsersListPanel(appFrame));
                            JOptionPane.showMessageDialog(appFrame,
                                    "Lista " + nome + " eliminata",
                                    "Lista acquisto",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }
            }
        } catch (ClassCastException | IndexOutOfBoundsException ignored) {}
    }
}
