package Business;

import java.io.File;
// BRIDGE PATTERN
// Implementation
public interface MailAPI {

    void inviaEmailAllegato(File tempFile, String indirizzo, String oggetto, String testo);

    void inviaEmail(String indirizzo, String oggetto, String testo);

}
