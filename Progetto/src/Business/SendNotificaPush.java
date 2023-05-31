package Business;

import Model.Notifica;
//STRATEGY PATTERN
//Concrete Strategy
public class SendNotificaPush implements INotificaStrategy{

    @Override
    public void sendNotification(Notifica notifica) {
        // inserire codice per inviare notifica PUSH invece di email
        Email email = new Email(new MailJavaxAPI());
        email.inviaEmail(notifica);
    }
}
