package View;

import Business.*;
import Model.*;
import View.Listener.AllProduttoriPanelListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class AllProduttoriPanel extends JPanel{

    public AllProduttoriPanel(AppFrame appFrame) {



        setLayout(new BorderLayout());


        //NORTH PANEL
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        JLabel label = new JLabel("Lista dei produttori");
        northPanel.add(label);


        //CENTER PANEL
        List<Produttore> lista = ProduttoreBusiness.getInstance().getProducts();
        JTable table = new Tabella(new ProduttoriTabelModel(lista)).getTabella();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getModel().getColumnCount(); i++) table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);

        JButton creaCategoria = new JButton("Aggiungi produttore");
        creaCategoria.setActionCommand("crea");
        southPanel.add(creaCategoria);
        AllProduttoriPanelListener allProduttoriPanelListener = new AllProduttoriPanelListener(appFrame);
        creaCategoria.addActionListener(allProduttoriPanelListener);
    }
}
