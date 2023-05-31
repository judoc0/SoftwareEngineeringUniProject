package View;

import Business.ListaArticoliBusiness;
import Business.Lista_has_ArticoloBusiness;
import Model.ListaArticoli;
import Model.PuntoVendita;
import Model.Utente;
import Business.SessionManager;
import View.Listener.UserListPanelListener;
import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class UsersListPanel extends JPanel{

    Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
    PuntoVendita pu = SessionManager.getInstance().getCurrentPuntoVendita();

    public UsersListPanel(AppFrame appFrame) {

        //CREAZIONE PANEL
        setLayout(new BorderLayout());

        JPanel northpanel = new JPanel();
        JPanel southPanel = new JPanel();

        add(northpanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);


        //NORTH PANEL
        JLabel benvenuto = new JLabel("Queste sono le tue liste");
        northpanel.add(benvenuto);


        //CENTER PANEL
        List<ListaArticoli> lista = ListaArticoliBusiness.getInstance().getPuntovenditaClienteListe(u.getIdUtente(), pu.getIdPuntoVendita());
        JTable table = new Tabella(new UserListTabelModel(lista)).getTabella();
        UserListPanelListener userListPanelListener = new UserListPanelListener(appFrame);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(userListPanelListener);


        //SOUTH PANEL
        String nameList = "";
        if (SessionManager.getInstance().getCurrentList() != null) nameList = SessionManager.getInstance().getCurrentList().getNome();
        JLabel currentList = new JLabel("Lista corrente: " + nameList);
        southPanel.add(currentList);
        southPanel.add(Box.createHorizontalStrut(100));

        JButton creaLista = new JButton("Crea una nuova lista acquisto");
        creaLista.setActionCommand("creaLista");
        southPanel.add(creaLista);
        creaLista.addActionListener(userListPanelListener);

    }
}
