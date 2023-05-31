package Business;
//ABSTRACT FACTHORY PATTERN
//Client
public class FactoryProvider {

    public enum TipoFactory {PRODOTTO, SERVIZIO}

    public static AbstractFactory getFactory(TipoFactory choice) {

        if (TipoFactory.PRODOTTO.equals(choice))
            return new ProdottoFactory();
        else if (TipoFactory.SERVIZIO.equals(choice))
            return new ServizioFactory();
        return null;
    }

}
