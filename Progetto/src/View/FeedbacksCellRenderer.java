package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class FeedbacksCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        FeedbacksTabelModel f = (FeedbacksTabelModel) table.getModel();
        int punteggio = (int) f.getValueAtRow(row);

        setHorizontalAlignment(SwingConstants.CENTER);
        if (column == 5) setForeground(Color.BLUE);

        if (punteggio <= 2) {
            setBackground(new Color(233,150,122));
        }
        else setBackground(new Color(144,238,144));


        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
