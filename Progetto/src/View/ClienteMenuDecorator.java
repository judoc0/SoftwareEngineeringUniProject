package View;

import javax.swing.*;
import java.util.List;
//DECORATOR PATTERN
//ConcreteDecorator
public class  ClienteMenuDecorator extends CustomMenuDecorator{

    public ClienteMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti() {
        if (pulsanti.size() > 0) return pulsanti;
        pulsanti.addAll(this.menu.getPulsanti());
        JButton lists = new JButton("Le tue liste");
        lists.setActionCommand("lists");
        pulsanti.add(lists);
        JButton cassa = new JButton("Vai alla cassa");
        cassa.setActionCommand("cassa");
        pulsanti.add(cassa);
        return pulsanti;
    }
}
