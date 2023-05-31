package Business;

import Model.Notifica;

import java.io.File;
// BRIDGE PATTERN
//Refined Abstractions
public class Email extends Mail{

    public Email(MailAPI mailAPI) {
        super(mailAPI);
    }

    @Override
    public void inviaEmail(File tempFile, String indirizzo) {

        String oggettoEmail = "Progetto Principi di Ingegneria del Software";

        String testoEmail = indirizzo + "\nPromemoria per prelevare gli articoli dal magazzino in formato pdf in allegato";

        mailAPI.inviaEmailAllegato(tempFile,indirizzo, oggettoEmail, testoEmail);

        mailAPI.inviaEmailAllegato(tempFile,"jeansanzia@gmail.com", oggettoEmail, testoEmail);

    }

    @Override
    public void inviaEmail(Notifica notifica) {

        String oggettoEmail = notifica.tipoNotifica();

        String testoEmail = notifica.getClient().getEmail() + "\n" + notifica.getMsg();

        mailAPI.inviaEmail(notifica.getClient().getEmail(), oggettoEmail, testoEmail);

        mailAPI.inviaEmail("jeansanzia@gmail.com", oggettoEmail, testoEmail);

    }


}
