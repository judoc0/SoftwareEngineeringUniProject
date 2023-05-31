package Model;

//FACTHORY METHOD PATTERN
//Concrete product

public class NotificaSMS extends Notifica{

    public NotificaSMS(String msg, Cliente c) {
        super(msg, c);
    }

    @Override
    public void notificaUtente() {
        System.out.println("Invio una notifica SMS all'utente");
    }

    @Override
    public String tipoNotifica() {
        return "Notifica SMS";
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
