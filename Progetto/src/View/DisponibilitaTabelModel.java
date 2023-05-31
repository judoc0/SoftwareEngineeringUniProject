package View;

import Model.Articolo;
import Model.Disponibilita;
import Model.ListaArticoli;
import Model.Magazzino;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DisponibilitaTabelModel extends AbstractTableModel {

    List<Disponibilita> lista;

    public DisponibilitaTabelModel(List<Disponibilita> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "ID";
            case 1: return "NOME";
            case 2: return "POSIZIONE";
            case 3: return "QUANTITÀ";
            case 4:
        }
        return null;
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

        Disponibilita l = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> l.getProdotto().getId();
            case 1 -> l.getProdotto().getNome();
            case 2 -> "Corsia: " + l.getProdotto().getPosizione().getCorsia() + " Scaffale: " + l.getProdotto().getPosizione().getScaffale();
            case 3 -> l.getQuantita();
            case 4 -> "Rifornisci";
            default -> null;
        };

    }

    public Object getValueAtRow(int row) {
        return getValueAt(row, 3);
    }

    public List<Disponibilita> getLista() {
        return lista;
    }

}
