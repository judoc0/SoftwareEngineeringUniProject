package View;

import Model.Produttore;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProduttoriTabelModel extends AbstractTableModel {

    List<Produttore> lista;

    public ProduttoriTabelModel(List<Produttore> lista) {
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

        Produttore a = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> a.getIdProduttore();
            case 1 -> a.getNome();
            case 2 -> a.getSito();
            case 3 -> a.getCitta();
            case 4 -> a.getNazione();
            default -> null;
        };

    }

    public List<Produttore> getLista() {
        return lista;
    }

}
