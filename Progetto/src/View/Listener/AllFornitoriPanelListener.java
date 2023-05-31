package View.Listener;

import Business.*;
import Model.Fornitore;
import Model.IFornitore;
import Model.IProduttore;
import Model.Produttore;
import View.AllFornitoriPanel;
import View.AllProduttoriPanel;
import View.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//OBSERVER PATTERN
//ConcreteObserver
public class AllFornitoriPanelListener implements ActionListener {

    AppFrame appFrame;

    public AllFornitoriPanelListener(AppFrame appFrame) {
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
                ServizioFactory pFac = (ServizioFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.SERVIZIO); //ABSTRACT FACTORY client
                IFornitore p = pFac.creaProduttore();
                p.setNome(nome.getText());
                p.setSito(sito.getText());
                p.setCitta(citta.getText());
                p.setNazione(nazione.getText());

                if (FornitoreBusiness.getInstance().existsFornitore((Fornitore) p))
                    JOptionPane.showMessageDialog(null, "Fornitore già esistente","Fornitore",JOptionPane.INFORMATION_MESSAGE);
                else {
                    FornitoreBusiness.getInstance().addFornitore((Fornitore) p);
                    appFrame.setCurrentMainPanel(new AllFornitoriPanel(appFrame));
                    JOptionPane.showMessageDialog(null, "Fornitore aggiunto", "Fornitore", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

}
