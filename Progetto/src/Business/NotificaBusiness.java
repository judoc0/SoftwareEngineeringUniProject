package Business;

import Model.Notifica;

public class NotificaBusiness {

    private static NotificaBusiness instance;     //SINGLETON PATTERN

    public static synchronized NotificaBusiness getInstance() {
        if (instance == null) instance = new NotificaBusiness();
        return instance;
    }

    private NotificaBusiness() {}

    public void sendNotifica(Notifica notifica) {
        //STRATEGY PATTERN
        //Client
        INotificaStrategy strategy = new SendNotificaEmail();
        SendNotifica sendNotifica = new SendNotifica(notifica);

        if (notifica.tipoNotifica().equalsIgnoreCase("Notifica Push"))
            strategy= new SendNotificaPush();
        if (notifica.tipoNotifica().equalsIgnoreCase("Notifica SMS"))
            strategy= new SendNotificaSMS();

        sendNotifica.setSendStrategy(strategy);
        sendNotifica.send();

        notifica.notificaUtente();
    }
}
