package View;

import Model.Fornitore;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FornitoriTabelModel extends AbstractTableModel {

    List<Fornitore> lista;

    public FornitoriTabelModel(List<Fornitore> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {

        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "NOME";
            case 2 -> "SITO WEB";
            case 3 -> "CITTÀ";
            case 4 -> "NAZIONE";
            default -> null;
        };
    }


    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Fornitore a = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> a.getIdFornitore();
            case 1 -> a.getNome();
            case 2 -> a.getSito();
            case 3 -> a.getCitta();
            case 4 -> a.getNazione();
            default -> null;
        };

    }

    public List<Fornitore> getLista() {
        return lista;
    }

}
