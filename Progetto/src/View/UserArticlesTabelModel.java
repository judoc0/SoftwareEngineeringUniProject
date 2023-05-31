package View;

import Model.ArticoliLista;
import Model.ListaArticoli;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserArticlesTabelModel extends AbstractTableModel {

    List<ArticoliLista> lista;
    ListaArticoli.StatoLista statoLista;


    public UserArticlesTabelModel(List<ArticoliLista> lista, ListaArticoli.StatoLista statoLista) {
        this.lista = lista;
        this.statoLista = statoLista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID ARTICOLO";
            case 1 -> "NOME";
            case 2 -> "PREZZO";
            case 3 -> "QUANTITÀ";
            case 4 -> "PREZZO TOTALE";
            case 5 -> "POSIZIONE MAGAZZINO";
            default -> null;
        };
    }


    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        ArticoliLista a = lista.get(rowIndex);

        String case6 = "Lascia Feedback";
        if (statoLista.equals(ListaArticoli.StatoLista.NON_PAGATA))
            case6 = "Rimuovi";
        String case5 = "";
        if (a.getPosizione() != null) case5 = "Corsia: " + a.getPosizione().getCorsia() +" Scaffale: " + a.getPosizione().getScaffale();

        return switch (columnIndex) {
            case 0 -> a.getIdArticolo();
            case 1 -> a.getArticolo().getNome();
            case 2 -> a.getArticolo().getPrezzo();
            case 3 -> a.getQuantita();
            case 4 -> a.getArticolo().getPrezzo() * a.getQuantita();
            case 5 -> case5;
            case 6 -> case6;
            default -> null;
        };

    }

}
