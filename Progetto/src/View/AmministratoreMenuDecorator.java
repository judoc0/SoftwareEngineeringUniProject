package View;

import javax.swing.*;
import java.util.List;
//DECORATOR PATTERN
//ConcreteDecorator
public class AmministratoreMenuDecorator extends CustomMenuDecorator{

    public AmministratoreMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti() {
        if (pulsanti.size() > 0) return pulsanti;
        pulsanti.addAll(this.menu.getPulsanti());

        JButton produttori = new JButton("Lista produttori");
        produttori.setActionCommand("manufacturers");
        pulsanti.add(produttori);

        JButton fornitori = new JButton("Lista fornitori");
        fornitori.setActionCommand("fornitori");
        pulsanti.add(fornitori);

        JButton articoli = new JButton("Lista articoli");
        articoli.setActionCommand("articoli");
        pulsanti.add(articoli);

        JButton categorie = new JButton("Lista categorie");
        categorie.setActionCommand("categorie");
        pulsanti.add(categorie);

        JButton punti = new JButton("Lista punti vendita");
        punti.setActionCommand("punti");
        pulsanti.add(punti);

        return pulsanti;

    }
}
