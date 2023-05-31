package View;

import Model.*;
import View.Listener.ArticoloPanelListener;

import javax.swing.*;
import java.awt.*;

//OBSERVER PATTERN
//Subject
public class ServicePanel extends JPanel implements IArticoloPanel {

    Servizio servizio;

    public ServicePanel(AppFrame appFrame, Servizio servizio) {

        this.servizio = servizio;


        // CREAZIONE DEI PANEL
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel soutPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        northPanel.setBackground(new Color(0 ,125 ,125));
        westPanel.setBackground(new Color(100, 170,125));
        eastPanel.setBackground(new Color(0, 125,0));
        soutPanel.setBackground(new Color(125, 0,0));
        centerPanel.setBackground(new Color(125, 0,125));

        add(northPanel, BorderLayout.NORTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(soutPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        northPanel.setPreferredSize(new Dimension(200,50));
        westPanel.setPreferredSize(new Dimension(400,200));
        eastPanel.setPreferredSize(new Dimension(400,200));
        soutPanel.setPreferredSize(new Dimension(200,200));
        centerPanel.setPreferredSize(new Dimension(100,100));


        // NORTH PANEL
        JLabel label = new JLabel(servizio.getNome());
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
        for (FeedBack f : servizio.getFeedbacks()) {
            if (!f.getCommento().equals("")) {
                JTextArea commento = new JTextArea("Data: " + f.getDate() + "\nUsername: " + f.getCliente().getUsername() + "\nPunteggio: " + f.getPunteggio() + "\n" + f.getCommento()); //nomeUtente
                commento.setEditable(false);
                soutPanel.add(commento);
            }
            punteggio += f.getPunteggio();
        }
        if (punteggio != 0) punteggio /= servizio.getFeedbacks().size();


        //WEST PANEL
        westPanel.setLayout(new GridLayout(3, 1));
        JLabel descrizione = new JLabel("<html>"+servizio.getDescrizione()+"<html>");
        JLabel fornitore = new JLabel("Fornitore: " + servizio.getFornitore().getNome(), SwingConstants.CENTER);
        JLabel gradimento = new JLabel("Gradimento: " + punteggio + " su 5", SwingConstants.CENTER);
        westPanel.add(descrizione);
        westPanel.add(fornitore);
        westPanel.add(gradimento);


        // EAST PANEL
        eastPanel.setLayout(new GridLayout(2,1));
        ArticoloPanelListener listener = new ArticoloPanelListener(appFrame, this);

        JLabel posizione = new JLabel("Prezzo " +servizio.getPrezzo()+"€", SwingConstants.CENTER);
        posizione.setOpaque(true);
        posizione.setBackground(Color.gray);
        eastPanel.add(posizione);

        JButton addlista = new JButton("Metti nella Lista Acquisto");
        addlista.setActionCommand("add");
        addlista.addActionListener(listener);
        eastPanel.add(addlista);


        // CENTER PANEL
        JLabel label2 = new JLabel();

        if (servizio.getFoto() != null) {
            ImageIcon image = new ImageIcon(servizio.getFoto());
            Image image1 = image.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
            ImageIcon asd = new ImageIcon(image1);
            label2.setIcon(asd);
        }
        centerPanel.add(label2);

    }

    public Articolo getArticolo() {
        return servizio;
    }

}
