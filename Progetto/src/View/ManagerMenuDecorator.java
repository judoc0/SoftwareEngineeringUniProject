package View;

import javax.swing.*;
import java.util.List;
//DECORATOR PATTERN
//ConcreteDecorator
public class ManagerMenuDecorator extends CustomMenuDecorator{

    public ManagerMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti() {
        if (pulsanti.size() > 0) return pulsanti;
        pulsanti.addAll(this.menu.getPulsanti());

        JButton utenti = new JButton("Lista clienti");
        utenti.setActionCommand("users");
        pulsanti.add(utenti);

        JButton disponibilita = new JButton("Disponibilità");
        disponibilita.setActionCommand("magazzino");
        pulsanti.add(disponibilita);

        JButton feedbacks = new JButton("Feedbacks");
        feedbacks.setActionCommand("feedbacks");
        pulsanti.add(feedbacks);

        return pulsanti;
    }
}
