package View;

import Model.CategoriaArticolo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CategoriaTabelModel extends AbstractTableModel {

    List<CategoriaArticolo> lista;

    public CategoriaTabelModel(List<CategoriaArticolo> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {

        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "NOME";
            case 2 -> "ID CATEGORIA PADRE";
            case 3 -> "TIPO";
            default -> null;
        };
    }


    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        CategoriaArticolo a = lista.get(rowIndex);

        String case2 = a.getIdCategoria_padre()+"";
        if (a.getIdCategoria_padre() == 0) case2 = "null";


        return switch (columnIndex) {
            case 0 -> a.getIdCategoria();
            case 1 -> a.getNome();
            case 2 -> case2;
            case 3 -> a.getTipoCategoria();
            default -> null;
        };

    }

    public List<CategoriaArticolo> getLista() {
        return lista;
    }

}
