package View;

import View.Listener.LoginListener;
import View.Listener.SideMenuListener;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    JPanel currentMainPanel;
    Header header;
    SideMenu sideMenu;
    Barra bar;

    public AppFrame() {
        super("App");

        SideMenuListener list = new SideMenuListener(this);
        LoginListener llist = new LoginListener(this);


        setLayout(new BorderLayout());

        header = new Header(llist);
        sideMenu = new SideMenu(list);
        bar = new Barra(this);
        PuntoVenditaPanel panel = new PuntoVenditaPanel(this);
        Footer footer = new Footer();

        add(header, BorderLayout.NORTH);
        add(sideMenu, BorderLayout.WEST);
        add(footer, BorderLayout.SOUTH);
        setCurrentMainPanel(panel);

        setSize(1930,850);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    public void setCurrentMainPanel(JPanel panel) {
        // 1. togliere quello che c'era prima nel CENTER
        if(currentMainPanel!=null) remove(currentMainPanel);
        setBackground(new Color(150, 150,150));

        //2. aggiungere nuovo panel
        add(panel, BorderLayout.CENTER);

        currentMainPanel = panel;

        invalidate();
        validate();
        repaint();
    }

    public Header getHeader() {
        return this.header;
    }

    public SideMenu getSideMenu() {
        return this.sideMenu;
    }

    public Barra getBar() {
        return bar;
    }

}
