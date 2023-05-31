package View;

import Business.*;
import Model.*;
import Business.SessionManager;
import View.Listener.CassaPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class CassaPanel extends JPanel{

    ListaArticoli listaArticoli = SessionManager.getInstance().getCurrentList();

    public CassaPanel(AppFrame appFrame) {


        //CREAZIONE PANEL
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);


        //NORTH PANEL
        JLabel benvenuto = new JLabel("Lista mostrata alla cassa: " + listaArticoli.getNome());
        northPanel.add(benvenuto);


        //CENTER PANEL
        List<ArticoliLista> lista = Lista_has_ArticoloBusiness.getInstance().getArticoli(listaArticoli.getIdLista());
        JTable table = new Tabella(new UserArticlesTabelModel(lista, listaArticoli.getStato())).getTabella();
        CassaPanelListener cassaPanelListener = new CassaPanelListener(appFrame);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(cassaPanelListener);


        //SOUTH PANEL
        JLabel prezzoLista = new JLabel("Costo Totale: " + Lista_has_ArticoloBusiness.getInstance().getPrezzoLista(lista) + "€");
        southPanel.add(prezzoLista);
        southPanel.add(Box.createHorizontalStrut(100));

        JButton cassa = new JButton("Paga la cassa");
        cassa.setActionCommand("cassa");
        southPanel.add(cassa);
        cassa.addActionListener(cassaPanelListener);
    }
}
