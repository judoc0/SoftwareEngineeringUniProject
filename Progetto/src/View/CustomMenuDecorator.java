package View;

import javax.swing.*;
import java.util.List;
//DECORATOR PATTERN
//Decorator
public abstract class CustomMenuDecorator extends Menu {

    protected Menu menu;

    @Override
    public abstract List<JButton> getPulsanti();
}
