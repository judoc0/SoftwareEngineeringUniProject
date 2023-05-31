package View;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel{

    public Footer() {
        JLabel credits = new JLabel("Software realizzato nell' ambito del progetto di Principi e Ingegneria del Software");
        add(credits);
        setBackground(new Color(150, 150, 150));
    }
}
