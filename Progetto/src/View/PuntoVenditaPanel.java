package View;

import Business.PuntoVenditaBusiness;
import Model.PuntoVendita;
import View.Listener.PuntoVenditaPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
//OBSERVER PATTERN
//Subject
public class PuntoVenditaPanel extends JPanel {

    public PuntoVenditaPanel(AppFrame appFrame) {
        
        appFrame.getHeader().setInvisileStatus();


        //CREAZIONE PANEL
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);


        //NORTH PANEL
        JLabel label = new JLabel("Scegli in quale punto vendita entrare");
        northPanel.add(label);


        //CENTER PANEL
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 25));
        centerPanel.setBackground(Color.gray);
        
        List<PuntoVendita> list = PuntoVenditaBusiness.getInstance().getPuntiVendita();
        PuntoVenditaPanelListener puntoVenditaPanelListener = new PuntoVenditaPanelListener(appFrame);
        for (PuntoVendita p : list) {
            JButton button = new JButton(p.getCitta());
            button.setPreferredSize(new Dimension(150, 50));
            button.setActionCommand(p.getCitta());
            button.addActionListener(puntoVenditaPanelListener);
            centerPanel.add(button);
        }
        
    }
}
