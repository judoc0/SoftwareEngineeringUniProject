package View;

import Business.*;
import Model.*;
import Business.SessionManager;
import View.Listener.FeedbacksPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class FeedbacksPanel extends JPanel {

    Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
    Manager m = ManagerBusiness.getInstance().getById(u.getIdUtente());

    public FeedbacksPanel() {

        setLayout(new BorderLayout());


        //NORTH PANEL
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        JLabel benvenuto = new JLabel("Feedbacks lasciati dagli utenti acquirenti");
        northPanel.add(benvenuto);


        //CENTER PANEL
        List<FeedBack> lista = FeedbackBusiness.getInstance().findCommenti(m.getIdPuntoVendita());
        JTable table = new Tabella(new FeedbacksTabelModel(lista)).getTabella();
        for (int i = 0; i<table.getColumnCount(); i++) table.getColumnModel().getColumn(i).setCellRenderer(new FeedbacksCellRenderer());

        FeedbacksPanelListener feedbacksPanelListener = new FeedbacksPanelListener(lista);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(feedbacksPanelListener);

    }
}
