package View;

import Business.*;
import Model.*;
import View.Listener.ProdottoCompositoPanelListener;

import javax.swing.*;
import java.awt.*;
//OBSERVER PATTERN
//Subject
public class ProdottoCompositoPanel extends JPanel {

    public ProdottoCompositoPanel(AppFrame appFrame, Prodotto prodotto) {

        //CREAZIONE PANEL
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);


        //NORTH PANEL
        JLabel benvenuto = new JLabel("Aggiungi prodotti al prodotto composito " + prodotto.getNome());
        northPanel.add(benvenuto);


        //CENTER PANEL
        ProdottoComposito prodottoComposito = ProdottoCompositoBusiness.getInstance().getOneProdottoComposito(prodotto.getId());
        JTable table = new Tabella(new ProdottoCompositoTabelModel(prodottoComposito.getSottoprodotti())).getTabella();
        ProdottoCompositoPanelListener prodottoCompositoPanelListener = new ProdottoCompositoPanelListener(appFrame, prodottoComposito);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(prodottoCompositoPanelListener);



        //SOUTH PANEL
        JLabel prezzoLista = new JLabel("Costo Totale: " + prodotto.getPrezzo() + "€");
        southPanel.add(prezzoLista);
        southPanel.add(Box.createHorizontalStrut(100));

        JButton add = new JButton("Aggiungi prodotto");
        add.setActionCommand("add");
        southPanel.add(add);
        add.addActionListener(prodottoCompositoPanelListener);

        JButton fine = new JButton("Fine");
        fine.setActionCommand("fine");
        southPanel.add(fine);
        fine.addActionListener(prodottoCompositoPanelListener);
    }

}
