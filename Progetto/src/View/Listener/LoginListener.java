package View.Listener;

import Business.*;
import Model.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//OBSERVER PATTERN
//ConcreteObserver
public class LoginListener implements ActionListener {

    AppFrame appFrame;

    public LoginListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();


        if ("btnLogin".equals(cmd)) {
            // chiamare la classe di business per fare login

            LoginResponse res = UtenteBusiness.getInstance().login(appFrame.getHeader().getUsername(), appFrame.getHeader().getPassword());

            if (!res.getResult().equals(LoginResponse.LoginResult.LOGIN_OK))
            JOptionPane.showMessageDialog(appFrame, res.getMessage(), "Login error", JOptionPane.ERROR_MESSAGE);

             else {
                //login ok
                appFrame.getHeader().clearFields();
                SessionManager.getInstance().getSession().put("loggedUser", res.getUtente());
                appFrame.getHeader().refresh();
                appFrame.getSideMenu().refresh();
                appFrame.setCurrentMainPanel(new WelcomePanel(appFrame));  //this
            }

        }
        if ("btnLogout".equals(cmd)) {
            logOut();
        }

        if ("btnEsc".equals(cmd)) {
            logOut();
            appFrame.getBar().setLoggedOutStatus();
            appFrame.setCurrentMainPanel(new PuntoVenditaPanel(appFrame));
        }

        if ("creaAccount".equals(cmd)) {
            appFrame.getHeader().setInvisileStatus();
            appFrame.setCurrentMainPanel(new RegisterPanel());
        }

    }

    public void logOut(){

        if (SessionManager.getInstance().getLoginList() != null) {
            if (Lista_has_ArticoloBusiness.getInstance().isEmpty(SessionManager.getInstance().getLoginList().getIdLista()))
                ListaArticoliBusiness.getInstance().removeById(SessionManager.getInstance().getLoginList().getIdLista());
            else {
                if (ListaArticoli.StatoLista.NON_PAGATA.equals(ListaArticoliBusiness.getInstance().getOneLista(SessionManager.getInstance().getLoginList().getIdLista()).getStato())) {
                    int res = JOptionPane.showConfirmDialog(null, "Vuoi salvare la lista " + SessionManager.getInstance().getLoginList().getNome(), "Lista Acquisto", JOptionPane.YES_NO_OPTION);
                    if (res == 1)
                        ListaArticoliBusiness.getInstance().removeById(SessionManager.getInstance().getLoginList().getIdLista());
                }
            }
        }

        // reset della view mostrando interfaccia guest
        SessionManager.getInstance().getSession().remove("loggedUser");
        appFrame.getHeader().refresh();
        appFrame.getSideMenu().refresh();
        appFrame.setCurrentMainPanel(new WelcomePanel(appFrame));
        SessionManager.getInstance().setLoginList(null);
        SessionManager.getInstance().setCurrentList(null);
    }
}
