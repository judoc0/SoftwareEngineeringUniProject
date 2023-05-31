package View;

import Model.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListaUtentiTabelModel extends AbstractTableModel {

    List<Cliente> lista;

    public ListaUtentiTabelModel(List<Cliente> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "USERNAME";
            case 2 -> "NOME";
            case 3 -> "COGNOME";
            case 4 -> "EMAIL";
            case 5 -> "NUMERO TELEFONICO";
            case 6 -> "ETÀ";
            case 7 -> "RESIDENZA";
            case 8 -> "PROFESSIONE";
            case 9 -> "CANALE PREFERITO";
            case 10 -> "STATO CLIENTE";
            default -> null;
        };
    }


    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 12;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Cliente c = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> c.getIdUtente();
            case 1 -> c.getUsername();
            case 2 -> c.getName();
            case 3 -> c.getSurname();
            case 4 -> c.getEmail();
            case 5 -> c.getPhoneNumber();
            case 6 -> c.getAge();
            case 7 -> c.getResidence();
            case 8 -> c.getJob();
            case 9 -> c.getCanalePreferito();
            case 10 -> c.getStatoCliente();
            case 11 -> "Gestisci cliente";
            default -> null;
        };

    }

    public List<Cliente> getLista() {
        return lista;
    }

}
