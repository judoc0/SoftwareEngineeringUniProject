package View;

import View.Listener.LoginListener;
import Business.SessionManager;

import javax.swing.*;


public class Barra extends JMenuBar {

    AppFrame appFrame;
    JPanel loggedIn = new JPanel();
    JMenuBar bar = new JMenuBar();
    JMenu city = new JMenu();

    public Barra(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    public void setLoggedInStatus() {

        LoginListener list = new LoginListener(appFrame);

        String citta = SessionManager.getInstance().getCurrentPuntoVendita().getCitta();
        city = new JMenu("Città: " + citta);
        JMenuItem item = new JMenuItem("Cambia città");
        city.setHorizontalAlignment(SwingConstants.LEFT);
        city.add(item);
        item.setActionCommand("btnEsc");
        item.addActionListener(list);
        bar.add(city);
        loggedIn.add(bar);
        add(loggedIn);
        appFrame.setJMenuBar(bar);
        bar.setVisible(true);

    }

    public void setLoggedOutStatus() {
        bar.remove(city);
    }
}
