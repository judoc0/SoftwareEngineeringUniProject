package View;

import Business.*;
import Model.*;
import Business.SessionManager;
import View.Listener.BrowsePanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class BrowsePanel extends JPanel{

    PuntoVendita puntoVendita = SessionManager.getInstance().getCurrentPuntoVendita();

    public BrowsePanel(AppFrame appFrame) {

        setLayout(new BorderLayout());


        //NORTH PANEL
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        JLabel label = new JLabel("Benvenuto nel punto vendita di " + puntoVendita.getCitta());
        northPanel.add(label);


        //CENTER PANEL
        List<Articolo> lista = PuntoVendita_has_articoloBusiness.getInstance().getByPuntoVendita(puntoVendita.getIdPuntoVendita());
        JTable table = new Tabella(new ArticoloTabelModel(lista)).getTabella();
        BrowsePanelListener browsePanelListener = new BrowsePanelListener(appFrame);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(browsePanelListener);

    }
}
