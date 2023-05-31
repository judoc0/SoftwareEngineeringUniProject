package Model;

import java.util.*;
import java.util.List;

//COMPOSITE PATTERN
//Composite
public class ProdottoComposito extends Prodotto implements IProdotto{

    private IProdotto prodotto;
    private final List<IProdotto> sottoprodotti = new ArrayList<>(); //composite pattern

    public void add(IProdotto prodotto) {

        if (this == prodotto) return;
        sottoprodotti.add(prodotto);
    }

    public void add(List<IProdotto> prodotti) {
        sottoprodotti.addAll(prodotti); //shallow copy
    }


    @Override
    public Float getPrezzo() {
        float p = 0F;

        //strategia 1 : metodo classico
        for (IProdotto prodotto : sottoprodotti)  {
            p += prodotto.getPrezzo() * prodotto.getQuantita();
        }

        return p;
    }

    @Override
    public String toString() {
        String composizion = "";
        for (IProdotto composito : getSottoprodotti())  composizion += composito.getQuantita()+ " " + composito.getNome() + " ";
        return composizion;
    }

    public IProdotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public List<IProdotto> getSottoprodotti() {
        return sottoprodotti;
    }


}
