package View;

import Business.*;
import Model.*;
import Business.SessionManager;
import View.Listener.ListaUtentiPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class ListaUtentiPanel extends JPanel {

    Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
    Manager m = ManagerBusiness.getInstance().getById(u.getIdUtente());

    public ListaUtentiPanel(AppFrame appFrame) {

        //CREAZIONE PANEL
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);


        //NORTH PANEL
        JLabel benvenuto = new JLabel("Clienti registrati nel Punto Vendita " + PuntoVenditaBusiness.getInstance().getOnePuntoVendita(m.getIdPuntoVendita()).getCitta());
        northPanel.add(benvenuto);


        //CENTER PANEL
        List<Cliente> lista = ClienteBusiness.getInstance().getClientiByPuntoVendita(m.getIdPuntoVendita());
        JTable table = new Tabella(new ListaUtentiTabelModel(lista)).getTabella();
        ListaUtentiPanelListener listaUtentiPanelListener = new ListaUtentiPanelListener(appFrame, lista);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(listaUtentiPanelListener);


        //SOUTH PANEL
        JButton invia = new JButton("Invia messaggio a tutti i clienti");
        invia.setActionCommand("invia");
        southPanel.add(invia);
        invia.addActionListener(listaUtentiPanelListener);

    }

}
