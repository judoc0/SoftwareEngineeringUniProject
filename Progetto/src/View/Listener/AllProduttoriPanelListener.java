package View.Listener;

import Business.*;
import Model.*;
import View.AllFornitoriPanel;
import View.AllProduttoriPanel;
import View.AllPuntiVenditaPanel;
import View.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
//OBSERVER PATTERN
//ConcreteObserver
public class AllProduttoriPanelListener implements ActionListener {

    AppFrame appFrame;

    public AllProduttoriPanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("crea".equals(cmd)) {
            Box box = Box.createVerticalBox();

            box.add(new JLabel("Nome: "));
            JTextField nome = new JTextField();
            box.add(nome);

            box.add(new JLabel("Sito Web: "));
            JTextField sito = new JTextField();
            box.add(sito);

            box.add(new JLabel("Citta: "));
            JTextField citta = new JTextField();
            box.add(citta);

            box.add(new JLabel("Nazione: "));
            JTextField nazione = new JTextField();
            box.add(nazione);

            if (JOptionPane.showConfirmDialog(null, box, "Fornitore", JOptionPane.YES_NO_OPTION) == 0 ){
                ProdottoFactory pFac = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO); //ABSTRACT FACTORY client
                IProduttore p = pFac.creaProduttore();
                p.setNome(nome.getText());
                p.setSito(sito.getText());
                p.setCitta(citta.getText());
                p.setNazione(nazione.getText());

                if (ProduttoreBusiness.getInstance().existsProduttore((Produttore) p))
                    JOptionPane.showMessageDialog(null, "Produttore già esistente","Produttore",JOptionPane.ERROR_MESSAGE);
                else {
                    ProduttoreBusiness.getInstance().addProduttore((Produttore) p);
                    appFrame.setCurrentMainPanel(new AllProduttoriPanel(appFrame));
                    JOptionPane.showMessageDialog(null, "Produttore aggiunto", "Produttore", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

}
