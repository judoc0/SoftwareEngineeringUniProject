package Model;

public class NewUtenteResponse {

    public enum newUtenteResult {NEW_USER_OK, USER_ALREADY_EXISTS}

    String message;
    int jOptionPane;
    Utente utente;
    NewUtenteResponse.newUtenteResult result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public int getjOptionPane() {
        return jOptionPane;
    }

    public void setjOptionPane(int jOptionPane) {
        this.jOptionPane = jOptionPane;
    }

    public newUtenteResult getResult() {
        return result;
    }

    public void setResult(newUtenteResult result) {
        this.result = result;
    }
}
