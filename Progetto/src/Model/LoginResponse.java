package Model;

public class LoginResponse {

    public enum LoginResult {LOGIN_OK, WRONG_PASSWORD, USER_NOT_EXISTS, USER_DISABLED}

    String message;
    Utente utente;
    int jOptionPane;
    LoginResult result;


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

    public LoginResult getResult() {
        return result;
    }

    public void setResult(LoginResult result) {
        this.result = result;
    }
}
