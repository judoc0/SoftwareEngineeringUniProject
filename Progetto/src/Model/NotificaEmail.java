package Model;

//FACTHORY METHOD PATTERN
//Concrete product
public class NotificaEmail extends Notifica {

    public NotificaEmail(String msg, Cliente c) {
        super(msg, c);
    }

    @Override
    public void notificaUtente() {
        System.out.println("Invio una notifica email all'utente");
    }

    @Override
    public String tipoNotifica() {
        return "Notifica Email";
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Cliente getClient() {
        return c;
    }
}
