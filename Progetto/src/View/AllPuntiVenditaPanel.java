package View;

import Business.*;
import Model.*;
import View.Listener.AllPuntiVenditaPanelListener;
import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class AllPuntiVenditaPanel extends JPanel{

    public AllPuntiVenditaPanel(AppFrame appFrame) {

        //CREAZIONE PANEL
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);


        //NORTH PANEL
        JLabel label = new JLabel("Lista dei punti vendita");
        northPanel.add(label);


        //CENTER PANEL
        List<PuntoVendita> lista = PuntoVenditaBusiness.getInstance().getPuntiVendita();
        JTable table = new Tabella(new PuntiVenditaTabelModel(lista)).getTabella();
        AllPuntiVenditaPanelListener allPuntiVenditaPanelListener = new AllPuntiVenditaPanelListener(appFrame);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(allPuntiVenditaPanelListener);


        //SOUTH PANEL
        JButton creaCategoria = new JButton("Aggiungi punto vendita");
        creaCategoria.setActionCommand("crea");
        southPanel.add(creaCategoria);
        creaCategoria.addActionListener(allPuntiVenditaPanelListener);
    }
}
