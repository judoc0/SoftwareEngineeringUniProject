package Business;

import Model.Notifica;
//STRATEGY PATTERN
//Concrete Strategy
public class SendNotificaSMS implements INotificaStrategy{

    @Override
    public void sendNotification(Notifica notifica) {
        // inserire codice per inviare notifica SMS invece di email
        Email email = new Email(new MailJavaxAPI());
        email.inviaEmail(notifica);
    }
}
