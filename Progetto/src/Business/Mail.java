package Business;

import Model.Notifica;
import java.io.File;
// BRIDGE PATTERN
// Abstraction
public abstract class Mail {

    protected MailAPI mailAPI;

    protected Mail(MailAPI mailAPI) {
        this.mailAPI = mailAPI;
    }

    public abstract void inviaEmail(File tempfile, String indirizzo);

    public abstract void inviaEmail(Notifica notifica);
}
