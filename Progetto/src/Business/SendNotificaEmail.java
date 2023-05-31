package Business;

import Model.Notifica;
//STRATEGY PATTERN
//Concrete Strategy
public class SendNotificaEmail implements INotificaStrategy{

    @Override
    public void sendNotification(Notifica notifica) {
        Email email = new Email(new MailJavaxAPI());
        email.inviaEmail(notifica);
    }
}
