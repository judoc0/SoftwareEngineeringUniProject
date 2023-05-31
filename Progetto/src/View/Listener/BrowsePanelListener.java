package View.Listener;

import Business.ArticoloBusiness;
import Business.ProdottoBusiness;
import Business.ServizioBusiness;
import Model.Articolo;
import View.AppFrame;
import View.ProductPanel;
import View.ServicePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//OBSERVER PATTERN
//ConcreteObserver
public class BrowsePanelListener extends MouseAdapter{

    AppFrame appFrame;

    public BrowsePanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }


    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();
        int row = table.rowAtPoint(point);
        int column = table.columnAtPoint(point);

        try {
            int id = (int)table.getValueAt(row, 0);
            String campo = (String) table.getValueAt(row, column);

            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Vai all'articolo".equals(campo)) {
                //PAGINA ARTICOLO
                Articolo a = ArticoloBusiness.getInstance().getOneProducts(id);
                if (ArticoloBusiness.getInstance().typeArticolo(a, ArticoloBusiness.TipoArticolo.PRODOTTO))
                    appFrame.setCurrentMainPanel(new ProductPanel(appFrame, ProdottoBusiness.getInstance().getOneProducts(id)));
                else appFrame.setCurrentMainPanel(new ServicePanel(appFrame, ServizioBusiness.getInstance().getOneServizio(id)));
            }

        } catch (ClassCastException | IndexOutOfBoundsException ignored) {}
    }

}
