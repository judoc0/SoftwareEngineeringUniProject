package Business;

import Model.*;
//FACTHORY METHOD PATTERN
//Concrete Creator
public class NotificaFactory {

    public Notifica getNotifica(String msg, Cliente c) {

        Cliente.CanalePreferito canale = c.getCanalePreferito();

        if (canale == null)
            return null;

        return switch (canale) {
            case SMS -> new NotificaSMS(msg, c);
            case EMAIL -> new NotificaEmail(msg, c);
            case PUSH -> new NotificaPush(msg, c);
        };
    }
}
