package View;

import javax.swing.*;
//DECORATOR PATTERN
//ConcreteComponent
public class GuestMenu extends Menu {

    public GuestMenu() {
        JButton browse = new JButton("Sfoglia catalogo");
        browse.setActionCommand("browse");

        JButton home = new JButton("Pagina Principale");
        home.setActionCommand("home");

        pulsanti.add(home);
        pulsanti.add(browse);

    }

}
