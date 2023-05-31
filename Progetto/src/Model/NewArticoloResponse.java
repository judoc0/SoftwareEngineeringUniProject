package Model;

public class NewArticoloResponse {

    public enum newArticoloResult {NEW_ARTICOLO_OK, ARTICOLO_ALREADY_EXISTS}

    String message;
    int jOptionPane;
    NewArticoloResponse.newArticoloResult result;

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

    public newArticoloResult getResult() {
        return result;
    }

    public void setResult(newArticoloResult result) {
        this.result = result;
    }
}
