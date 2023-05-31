package Model;

public abstract class Notifica {

    String msg;
    Cliente c;

    public Notifica(String msg, Cliente c) {
        this.msg = msg;
        this.c = c;
    }

    public abstract void notificaUtente();

    public abstract String tipoNotifica();

    public abstract String getMsg();

    public abstract Cliente getClient();
}
