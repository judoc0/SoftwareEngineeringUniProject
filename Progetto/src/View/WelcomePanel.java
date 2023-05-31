package View;

import Business.SessionManager;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel(AppFrame appFrame) {

        appFrame.getHeader().setLoggedInStatus();
        setBackground(new Color(150, 150,150));

        JLabel benvenuto = new JLabel();
        if (SessionManager.getInstance().getSession().containsKey("loggedUser")) {
            appFrame.getHeader().setLoggedInStatus();
            benvenuto.setText("Bentornato, usa i pulsanti lato!");

        } else {
            appFrame.getHeader().setLoggedOutStatus();
            benvenuto.setText("Ciao, effettua il login per iniziare, oppure sfoglia il catalogo usando il pulsante a lato!");
        }
        add(benvenuto);

    }
}
