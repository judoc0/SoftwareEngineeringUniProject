package View;

import Business.*;
import Model.Fornitore;
import View.Listener.AllFornitoriPanelListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class AllFornitoriPanel extends JPanel{


    public AllFornitoriPanel(AppFrame appFrame) {

        setLayout(new BorderLayout());

        //NORTH PANEL
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        JLabel label = new JLabel("Lista dei fornitori");
        northPanel.add(label);


        //CENTER PANEL
        List<Fornitore> lista = FornitoreBusiness.getInstance().getFornitori();
        JTable table = new Tabella(new FornitoriTabelModel(lista)).getTabella();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getModel().getColumnCount(); i++) table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);
        JButton creaCategoria = new JButton("Aggiungi fornitore");
        creaCategoria.setActionCommand("crea");
        southPanel.add(creaCategoria);
        AllFornitoriPanelListener allFornitoriPanelListener = new AllFornitoriPanelListener(appFrame);
        creaCategoria.addActionListener(allFornitoriPanelListener);
    }
}
