package View;

import Model.FeedBack;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FeedbacksTabelModel extends AbstractTableModel {

    List<FeedBack> lista;

    public FeedbacksTabelModel(List<FeedBack> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID ACQUISTO";
            case 1 -> "DATA";
            case 2 -> "USERNAME CLIENTE";
            case 3 -> "ID ARTICOLO";
            case 4 -> "PUNTEGGIO";
            default -> null;
        };
    }


    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        // ID, NOME, CATEGORIA, PREZZO
        return 6;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        FeedBack f = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> f.getIdAcquisto();
            case 1 -> f.getDate();
            case 2 -> f.getCliente().getUsername();
            case 3 -> f.getIdArticolo();
            case 4 -> f.getPunteggio();
            case 5 -> "Vedi Commento";
            default -> null;
        };

    }

    public Object getValueAtRow(int row) {
        return getValueAt(row, 4);
    }

    public List<FeedBack> getLista() {
        return lista;
    }

}
