package View;

import Business.*;
import Model.*;
import View.Listener.UserArticlesPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//OBSERVER PATTERN
//Subject
public class UsersArticlesPanel extends JPanel{

    public UsersArticlesPanel(AppFrame appFrame, ListaArticoli listaArticoli) {

        //CREAZIONE PANEL
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);


        //NORTH PANEL
        JLabel benvenuto = new JLabel("Questi sono gli articoli presenti nella lista: " + listaArticoli.getNome());
        northPanel.add(benvenuto);


        //CENTER PANEL
        List<ArticoliLista> lista = Lista_has_ArticoloBusiness.getInstance().getArticoli(listaArticoli.getIdLista());
        if (!lista.isEmpty()) {
            String text = Lista_has_ArticoloBusiness.getInstance().updateLista(lista);
            if (!text.equals(""))
                JOptionPane.showMessageDialog(null,
                        text,
                        "Lista",
                        JOptionPane.INFORMATION_MESSAGE);
            lista = Lista_has_ArticoloBusiness.getInstance().getArticoli(listaArticoli.getIdLista());
        }
        UserArticlesPanelListener userArticlesPanelListener = new UserArticlesPanelListener(appFrame, listaArticoli);
        JTable table = new Tabella(new UserArticlesTabelModel(lista, listaArticoli.getStato())).getTabella();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(userArticlesPanelListener);


        //SOUTH PANEL
        JButton listaCorrente = new JButton("Seleziona come lista corrente");
        listaCorrente.setActionCommand("corrente");
        if (listaArticoli.getStato().equals(ListaArticoli.StatoLista.NON_PAGATA)) southPanel.add(listaCorrente);
        listaCorrente.addActionListener(userArticlesPanelListener);
        southPanel.add(Box.createHorizontalStrut(100));

        JLabel prezzoLista = new JLabel("Costo lista: " + Lista_has_ArticoloBusiness.getInstance().getPrezzoLista(lista) + "€");
        southPanel.add(prezzoLista);
        southPanel.add(Box.createHorizontalStrut(100));

        JButton promemoria = new JButton("Invia promemoria alla tua email");
        promemoria.setActionCommand("promemoria");
        southPanel.add(promemoria);
        promemoria.addActionListener(userArticlesPanelListener);
        southPanel.add(Box.createHorizontalStrut(100));

        if (listaArticoli.getStato() == ListaArticoli.StatoLista.PAGATA) {
            JLabel data = new JLabel("Data Acquisto: " + listaArticoli.getData());
            southPanel.add(data);

        }
    }
}
