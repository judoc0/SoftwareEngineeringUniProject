package View;

import Model.Articolo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ArticoloTabelModel extends AbstractTableModel {

    List<Articolo> lista;

    public ArticoloTabelModel(List<Articolo> lista) {
        this.lista = lista;
    }


    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Articolo a = lista.get(rowIndex);
        if (columnIndex == 1) {
            a.setNome(value.toString());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "NOME";
            case 2 -> "CATEGORIA";
            case 3 -> "PREZZO";
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

        Articolo a = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> a.getId();
            case 1 -> a.getNome();
            case 2 -> a.getCategoria().getNome();
            case 3 -> a.getPrezzo();
            case 4 -> "Vai all'articolo";
            default -> null;
        };

    }

    public List<Articolo> getLista() {
        return lista;
    }

}
