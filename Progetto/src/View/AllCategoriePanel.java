package View;

import Business.*;
import Model.*;
import View.Listener.AllCategoriePanelListener;
import View.Listener.AllProduttoriPanelListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class AllCategoriePanel extends JPanel{

    public AllCategoriePanel(AppFrame appFrame) {

        setLayout(new BorderLayout());


        //NORTH PANEL
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        JLabel label = new JLabel("Lista delle categorie");
        northPanel.add(label);


        //CENTER PANEL
        List<CategoriaArticolo> lista = CategoriaArticoloBusiness.getInstance().getAllCategorie();
        JTable table = new Tabella(new CategoriaTabelModel(lista)).getTabella();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getModel().getColumnCount(); i++) table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);

        JButton creaCategoria = new JButton("Crea categoria");
        creaCategoria.setActionCommand("crea");
        southPanel.add(creaCategoria);
        AllCategoriePanelListener allCategoriePanelListener = new AllCategoriePanelListener(appFrame);
        creaCategoria.addActionListener(allCategoriePanelListener);
    }
}
