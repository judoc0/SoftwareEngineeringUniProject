package Model;

public class NewListaResponse {

    public enum newListaResult {NEW_LISTA_OK, LISTA_ALREADY_EXISTS}

    String message;
    ListaArticoli listaArticoli;
    int jOptionPane;
    NewListaResponse.newListaResult result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListaArticoli getListaArticoli() {
        return listaArticoli;
    }

    public void setListaArticoli(ListaArticoli listaArticoli) {
        this.listaArticoli = listaArticoli;
    }

    public int getjOptionPane() {
        return jOptionPane;
    }

    public void setjOptionPane(int jOptionPane) {
        this.jOptionPane = jOptionPane;
    }

    public newListaResult getResult() {
        return result;
    }

    public void setResult(newListaResult result) {
        this.result = result;
    }
}
