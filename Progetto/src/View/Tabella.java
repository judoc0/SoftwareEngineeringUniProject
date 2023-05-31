package View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class Tabella extends DefaultTableCellRenderer{

    AbstractTableModel abstractTableModel;

    public Tabella(AbstractTableModel abstractTableModel) {
        this.abstractTableModel = abstractTableModel;
    }

    public JTable getTabella() {

        JTable table = new JTable(abstractTableModel);

        table.setRowHeight(40);
        table.setBackground(new Color(150, 150,150));
       // table.setSelectionBackground(new Color(153,229,255)); COLORA LA RIGA SELEZIONATA
        table.setFont(new Font(table.getFont().getFontName(), Font.BOLD, 12));
        table.setFillsViewportHeight(true);

        //RENDERER PER METTERE LA STRINGA AL CENTRO DELLA CELLA
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        //RENDERER PER COLORARE LA STRINGA
        DefaultTableCellRenderer blueRenderer = new DefaultTableCellRenderer();
        blueRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        blueRenderer.setForeground(Color.BLUE);

        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            if (i == table.getModel().getColumnCount()-1) table.getColumnModel().getColumn(i).setCellRenderer(blueRenderer); //COLORIAMO DI BLU LA COLONNA CON I LISTENER
        }

        return table;

    }

}
