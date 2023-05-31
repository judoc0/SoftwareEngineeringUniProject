package View;

import Business.*;
import Model.*;
import View.Listener.ArticoloPanelListener;
import Business.SessionManager;
import javax.swing.*;
import java.awt.*;

//OBSERVER PATTERN
//Subject
public class ProductPanel extends JPanel implements IArticoloPanel {

    Prodotto prodotto;

    public ProductPanel(AppFrame appFrame, Prodotto prodotto) {

        this.prodotto = prodotto;


        // CREAZIONE DEI PANEL
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        northPanel.setBackground(new Color(0 ,125 ,125));
        westPanel.setBackground(new Color(100, 170,125));
        eastPanel.setBackground(new Color(0, 125,0));
        southPanel.setBackground(new Color(125, 0,0));
        centerPanel.setBackground(new Color(125, 0,125));

        add(northPanel, BorderLayout.NORTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        northPanel.setPreferredSize(new Dimension(200,50));
        westPanel.setPreferredSize(new Dimension(400,200));
        eastPanel.setPreferredSize(new Dimension(400,200));
        southPanel.setPreferredSize(new Dimension(200,200));
        centerPanel.setPreferredSize(new Dimension(100,100));


        // NORTH PANEL
        JLabel label = new JLabel(prodotto.getNome());
        label.setFont(new Font(label.getFont().getFontName(), label.getFont().getStyle(), 25));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(255,0,0));
        label.setBackground(new Color(0,0,0));
        label.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0)));
        label.setOpaque(true);
        northPanel.add(label);


        // SOUTH PANEL
        int punteggio = 0;
        for (FeedBack f : prodotto.getFeedbacks()) {
            if (!f.getCommento().equals("")) {
                JTextArea commento = new JTextArea("Data: " + f.getDate() + "\nUsername: " + f.getCliente().getUsername() + "\nPunteggio: " + f.getPunteggio() + "\n" + f.getCommento()); //nomeUtente
                commento.setEditable(false);
                southPanel.add(commento);
            }
            punteggio += f.getPunteggio();
        }
        if (punteggio != 0) punteggio /= prodotto.getFeedbacks().size();


        // WEST PANEL
        String composizion = prodotto.toString();
        if (ProdottoCompositoBusiness.getInstance().compositoExists(prodotto.getId())) {
            ProdottoComposito pro = ProdottoCompositoBusiness.getInstance().getOneProdottoComposito(prodotto.getId());
            composizion = pro.toString();
        }

        westPanel.setLayout(new GridLayout(4, 1));
        JLabel descrizione = new JLabel("<html>"+prodotto.getDescrizione()+"<html>");
        JLabel produttore = new JLabel("Produttore: " + prodotto.getProduttore().getNome(), SwingConstants.CENTER);
        JLabel composizione = new JLabel("Composizione: " + composizion, SwingConstants.CENTER);
        JLabel gradimento = new JLabel("Gradimento: " + punteggio + " su 5", SwingConstants.CENTER);
        westPanel.add(descrizione);
        westPanel.add(composizione);
        westPanel.add(produttore);
        westPanel.add(gradimento);


        // EAST PANEL
        eastPanel.setLayout(new GridLayout(3,1));

        JLabel label1 = new JLabel("Posizione prodotto: Corsia " + prodotto.getPosizione().getCorsia() + " Scaffale " + prodotto.getPosizione().getScaffale(), SwingConstants.CENTER);
        label1.setHorizontalAlignment(JLabel.CENTER);
        eastPanel.add(label1);

        Disponibilita d = DisponibilitaBusiness.getInstance().getOneDisponibilita(prodotto.getId(), SessionManager.getInstance().getCurrentPuntoVendita().getMagazzino().getIdMagazzino());
        int quantita = d.getQuantita();
        JLabel posizione = new JLabel("<html> Prezzo " +prodotto.getPrezzo()+"€" + "<br/>Disponibilità: " + quantita + " unità</html>", SwingConstants.CENTER);
        posizione.setOpaque(true);
        posizione.setBackground(Color.gray);
        eastPanel.add(posizione);

        ArticoloPanelListener listener = new ArticoloPanelListener(appFrame, this);
        if (quantita > 0) {
            JButton addlista = new JButton("Metti nella Lista Acquisto");
            addlista.setActionCommand("add");
            addlista.addActionListener(listener);
            eastPanel.add(addlista);
        } else {
            JButton prenota = new JButton("Prenota Articolo");
            prenota.setActionCommand("prenota");
            prenota.addActionListener(listener);
            eastPanel.add(prenota);
        }


        // CENTER PANEL
        JLabel label2 = new JLabel();
        if (prodotto.getFoto() != null) {
            ImageIcon image = new ImageIcon(prodotto.getFoto());
            Image image1 = image.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
            ImageIcon asd = new ImageIcon(image1);
            label2.setIcon(asd);
        }
        centerPanel.add(label2);

    }

    public Articolo getArticolo() {
        return prodotto;
    }

}
