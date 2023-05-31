package View;

import Model.IProdotto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProdottoCompositoTabelModel extends AbstractTableModel {

    List<IProdotto> lista;

    public ProdottoCompositoTabelModel(List<IProdotto> lista) {
        this.lista = lista;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID ARTICOLO";
            case 1 -> "NOME";
            case 2 -> "PREZZO";
            case 3 -> "QUANTITÀ";
            case 4 -> "PREZZO TOTALE";
            default -> null;
        };
    }


    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 6;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        IProdotto p = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> p.getId();
            case 1 -> p.getNome();
            case 2 -> p.getPrezzo();
            case 3 -> p.getQuantita();
            case 4 -> p.getPrezzo() * p.getQuantita();
            case 5 -> "Modifica quantità";
            default -> null;
        };

    }

}
