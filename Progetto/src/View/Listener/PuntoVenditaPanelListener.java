package View.Listener;

import Business.PuntoVenditaBusiness;
import Business.SessionManager;
import Model.PuntoVendita;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

//OBSERVER PATTERN
//ConcreteObserver
public class PuntoVenditaPanelListener extends MouseAdapter implements ActionListener {

    AppFrame appFrame;

    public PuntoVenditaPanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;

        appFrame.getSideMenu().invisible();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        PuntoVendita p = PuntoVenditaBusiness.getInstance().geyByCity(cmd);

        appFrame.getSideMenu().refresh();
        appFrame.setCurrentMainPanel(new WelcomePanel(appFrame));
        SessionManager.getInstance().setCurrentPuntoVendita(p);
        appFrame.getBar().setLoggedInStatus();


    }
}
