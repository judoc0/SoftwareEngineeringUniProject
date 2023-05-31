package Business;

import Model.Notifica;
//STRATEGY PATTERN
//Context
public class SendNotifica {

    private Notifica notifica;
    private INotificaStrategy sendStrategy;

    public SendNotifica(Notifica notifica) {
        this.notifica = notifica;
    }

    public void setSendStrategy(INotificaStrategy sendStrategy) {
        this.sendStrategy = sendStrategy;
    }

    public void send() {
        sendStrategy.sendNotification(notifica);
    }
}
