package Model;

public class NewPuntoVenditaResponse {

    public enum newPuntoVenditaResult {NEW_PUNTO_OK, PUNTO_ALREADY_EXISTS}

    String message;
    int jOptionPane;
    NewPuntoVenditaResponse.newPuntoVenditaResult result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getjOptionPane() {
        return jOptionPane;
    }

    public void setjOptionPane(int jOptionPane) {
        this.jOptionPane = jOptionPane;
    }

    public newPuntoVenditaResult getResult() {
        return result;
    }

    public void setResult(newPuntoVenditaResult result) {
        this.result = result;
    }


}
