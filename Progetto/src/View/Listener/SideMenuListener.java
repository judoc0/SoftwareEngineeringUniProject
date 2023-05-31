package View.Listener;


import Business.Lista_has_ArticoloBusiness;
import Business.SessionManager;
import Model.ListaArticoli;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//OBSERVER PATTERN
//ConcreteObserver
public class SideMenuListener implements ActionListener {

    AppFrame appFrame;


    public SideMenuListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        appFrame.getHeader().setInvisileStatus();

        switch (cmd) {

            case "browse" -> appFrame.setCurrentMainPanel(new BrowsePanel(appFrame));
            case "lists" -> appFrame.setCurrentMainPanel(new UsersListPanel(appFrame));
            case "home" -> appFrame.setCurrentMainPanel(new WelcomePanel(appFrame));
            case "cassa" -> {
                ListaArticoli listaArticoli = SessionManager.getInstance().getCurrentList();
                if (listaArticoli == null || Lista_has_ArticoloBusiness.getInstance().isEmpty(listaArticoli.getIdLista()))
                    JOptionPane.showMessageDialog(appFrame, "La tua lista acquisto è vuota", "Lista Acquisto", JOptionPane.ERROR_MESSAGE);
                else appFrame.setCurrentMainPanel(new CassaPanel(appFrame));
            }
            case "magazzino" -> appFrame.setCurrentMainPanel(new DisponibilitaPanel(appFrame));
            case "users" -> appFrame.setCurrentMainPanel(new ListaUtentiPanel(appFrame));
            case "feedbacks" -> appFrame.setCurrentMainPanel(new FeedbacksPanel());
            case "articoli" -> appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
            case "categorie" -> appFrame.setCurrentMainPanel(new AllCategoriePanel(appFrame));
            case "manufacturers" -> appFrame.setCurrentMainPanel(new AllProduttoriPanel(appFrame));
            case "fornitori" -> appFrame.setCurrentMainPanel(new AllFornitoriPanel(appFrame));
            case "punti" -> appFrame.setCurrentMainPanel(new AllPuntiVenditaPanel(appFrame));

        }
    }
}
