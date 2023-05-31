package Model;

//FACTHORY METHOD PATTERN
//Concrete product

public class NotificaPush extends Notifica{

    public NotificaPush(String msg, Cliente c) {
        super(msg, c);
    }

    @Override
    public void notificaUtente() {
        System.out.println("Invio una notifica push all'utente");
    }

    @Override
    public String tipoNotifica() {
        return "Notifica Push";
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
