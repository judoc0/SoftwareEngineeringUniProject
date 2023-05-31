package Business;

import Model.Notifica;
//STRATEGY PATTERN
//Strategy
public interface INotificaStrategy {

    void sendNotification(Notifica notifica);
}
