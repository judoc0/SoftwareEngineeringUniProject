package View;

import Model.Utente;
import View.Listener.LoginListener;
import Business.SessionManager;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    JPanel loggedIn = new JPanel();
    JPanel loggedOut = new JPanel();
    JLabel welcome;
    JTextField username;
    JPasswordField password;

    public Header(LoginListener list) {
        setLayout(new FlowLayout());
        setBackground(new Color(150, 150, 150));

        username = new JTextField(20);
        password = new JPasswordField(20);
        JLabel lblUsername = new JLabel("username: ");
        JLabel lblPassword = new JLabel("password: ");
        JButton btnLogin = new JButton("Login");
        btnLogin.setActionCommand("btnLogin");
        btnLogin.addActionListener(list);

        JButton creaAccount = new JButton("Crea Acccount");
        creaAccount.setActionCommand("creaAccount");
        creaAccount.addActionListener(list);

        loggedOut.add(lblUsername);
        loggedOut.add(username);
        loggedOut.add(lblPassword);
        loggedOut.add(password);
        loggedOut.add(btnLogin);
        loggedOut.add(creaAccount);

        welcome = new JLabel("Benvenuto _____");
        JButton btnLogout = new JButton("Logout");
        btnLogout.setActionCommand("btnLogout");
        btnLogout.addActionListener(list);
        loggedIn.add(welcome);
        loggedIn.add(btnLogout);

        add(loggedIn);
        add(loggedOut);

        setLoggedOutStatus();
    }

    public void setLoggedInStatus() {
        loggedIn.setVisible(true);
        loggedOut.setVisible(false);
    }

    public void setLoggedOutStatus() {
        loggedIn.setVisible(false);
        loggedOut.setVisible(true);
    }

    public void setInvisileStatus() {
        loggedIn.setVisible(false);
        loggedOut.setVisible(false);
    }

    public void refresh() {
        // 1. prendere l'utente loggato u
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        // 2. se u è null -> setLoggedOutStatus()
        if (u == null) setLoggedOutStatus();

        // 3. altrimenti setLoggedInSatus();
        else {
            welcome.setText("benvenuto, " + u.getName());
            setLoggedInStatus();
        }
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public void clearFields() { //<<dovremmo prevedere nell'appframe un reset della view(la frase sotto i campi di login, in particolare)
        username.setText("");
        password.setText("");
    }
}
