package View;

import Model.ListaArticoli;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserListTabelModel extends AbstractTableModel {

    List<ListaArticoli> lista;

    public UserListTabelModel(List<ListaArticoli> lista) {
        this.lista = lista;
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        ListaArticoli a = lista.get(rowIndex);
        if (columnIndex == 1) {
            a.setNome(value.toString());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "NOME";
            case 1 -> "STATO LISTA";
            default -> null;
        };
    }


    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        ListaArticoli l = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> l.getNome();
            case 1 -> l.getStato();
            case 2 -> "Gestisci lista";
            default -> null;
        };

    }

    public List<ListaArticoli> getLista() {
        return lista;
    }

}
