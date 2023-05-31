package View;

import Business.*;
import Model.*;
import View.Listener.AllArticoliPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class AllArticoliPanel extends JPanel{

    public AllArticoliPanel(AppFrame appFrame) {

        setLayout(new BorderLayout());


        //NORTH PANEL
        JPanel northPanel =new JPanel();
        add(northPanel, BorderLayout.NORTH);
        JLabel label = new JLabel("Lista degli articoli venduti");
        northPanel.add(label);


        //CENTER PANEL
        List<Articolo> lista = ArticoloBusiness.getInstance().getProducts();
        AllArticoliPanelListener allArticoliPanelListener = new AllArticoliPanelListener(appFrame);
        JTable table = new Tabella(new ArticoloAdminTabelModel(lista)).getTabella();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(allArticoliPanelListener);


        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);

        JButton creaArticolo = new JButton("Crea articolo");
        creaArticolo.setActionCommand("crea");
        southPanel.add(creaArticolo);
        creaArticolo.addActionListener(allArticoliPanelListener);

    }

}
