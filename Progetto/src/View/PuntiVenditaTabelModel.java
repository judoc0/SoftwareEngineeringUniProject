package View;

import Model.PuntoVendita;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PuntiVenditaTabelModel extends AbstractTableModel {

    List<PuntoVendita> lista;

    public PuntiVenditaTabelModel(List<PuntoVendita> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {

        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "CITTÀ";
            case 2 -> "MAGAZZINO ID";
            case 3 -> "MANAGER";
            default ->  null;
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

        PuntoVendita a = lista.get(rowIndex);

        String case2 = "senza manager";
        if (a.getManager() != null)
            case2 = a.getManager().getUsername();

        return switch (columnIndex) {
            case 0 -> a.getIdPuntoVendita();
            case 1 -> a.getCitta();
            case 2 -> a.getMagazzino().getIdMagazzino();
            case 3 -> case2;
            case 4 -> "gestisci punto vendita";
            default -> null;
        };

    }

    public List<PuntoVendita> getLista() {
        return lista;
    }


}
