package View;

import Business.*;
import Model.*;
import Business.SessionManager;
import View.Listener.DisponibilitaPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class DisponibilitaPanel extends JPanel {

    Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
    Manager m = ManagerBusiness.getInstance().getById(u.getIdUtente());

    public DisponibilitaPanel(AppFrame appFrame) {

        setLayout(new BorderLayout());


        //NORTH PANEL
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        JLabel benvenuto = new JLabel("Scorte presenti nel magazzino");
        northPanel.add(benvenuto);


        //CENTER PANEL
        List<Disponibilita> lista = DisponibilitaBusiness.getInstance().getDisponibilita(m.getIdPuntoVendita());
        JTable table = new Tabella(new DisponibilitaTabelModel(lista)).getTabella();
        for (int i = 0; i<table.getColumnCount(); i++) table.getColumnModel().getColumn(i).setCellRenderer(new DisponibilitaCellRenderer());

        DisponibilitaPanelListener disponibilitaPanelListener = new DisponibilitaPanelListener(appFrame);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(disponibilitaPanelListener);

    }

}
