package View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
//DECORATOR PATTERN
//Component
public abstract class Menu {

    List<JButton> pulsanti = new ArrayList<>();

    public List<JButton> getPulsanti() {
        return pulsanti;
    }
}
