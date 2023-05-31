package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DisponibilitaCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DisponibilitaTabelModel d = (DisponibilitaTabelModel) table.getModel();
        setHorizontalAlignment(SwingConstants.CENTER);
        int disponibilita = (int) d.getValueAtRow(row);

        setHorizontalAlignment(SwingConstants.CENTER);
        if (column == 4) setForeground(Color.BLUE);

        if (disponibilita <= 10) {
            setBackground(new Color(233,150,122));
        }
        else if (disponibilita >= 30) {
            setBackground(new Color(144, 238, 144));
        }
        else {
            setBackground(new Color(255,255,204));
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
