package View;

import Business.UtenteBusiness;
import Model.Utente;
import Business.SessionManager;
import View.Listener.SideMenuListener;

import javax.swing.*;
import java.awt.*;

public class SideMenu extends JPanel {

    private SideMenuListener listener;

    public SideMenu(SideMenuListener listener) {

        this.listener = listener;
        setLayout(new GridLayout(20, 1));
        setBackground(new Color(150, 150, 150));

        refresh();
    }


    public void refresh() {

        removeAll();
        Menu menu = new GuestMenu();  //DECORATOR PATTERN client
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        if (u != null) {

            if (UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.CLIENT))   //cliente
                menu = new ClienteMenuDecorator(menu);

            if (UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.MANAGE_SHOP)) //manager
                menu = new ManagerMenuDecorator(menu);

            if (UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.ADMIN_SYSTEM)) //amministratore
                menu = new AmministratoreMenuDecorator(menu);
        }

        for (JButton btn: menu.getPulsanti()) {
            btn.addActionListener(listener);
            add(btn);
        }

        invalidate();
        validate();
        repaint();
    }


    public void invisible() {
        removeAll();
    }

}
