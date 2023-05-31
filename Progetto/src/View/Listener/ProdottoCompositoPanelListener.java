package View.Listener;

import Business.*;
import Model.*;
import View.AllArticoliPanel;
import View.AppFrame;
import View.ProdottoCompositoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
//OBSERVER PATTERN
//ConcreteObserver
public class ProdottoCompositoPanelListener extends MouseAdapter implements ActionListener {

    AppFrame appFrame;
    ProdottoComposito prodottoComposito;

    public ProdottoCompositoPanelListener(AppFrame appFrame, ProdottoComposito prodottoComposito) {
        this.appFrame = appFrame;
        this.prodottoComposito = prodottoComposito;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        //AGGIUNGI PRODOTTO
        if ("add".equals(cmd)) {

            // Make the two lists
            List<Prodotto> prodotti = ProdottoBusiness.getInstance().getProducts();
            List<String> list1 = new ArrayList<>();
            for (Prodotto a : prodotti) list1.add(a.getNome());

            List<String> list2 = new ArrayList<>();
            for (IProdotto p : prodottoComposito.getSottoprodotti()) list2.add(p.getNome());
            // Prepare a union
            List<String> union = new ArrayList<>(list1);
            union.addAll(list2);
            // Prepare an intersection
            List<String> intersection = new ArrayList<>(list1);
            intersection.retainAll(list2);
            // Subtract the intersection from the union
            union.removeAll(intersection);

            String[] array = new String[union.size()-1];
            for (int i = 0; i < array.length; i++) {
                if (!prodottoComposito.getNome().equals(union.get(i)))
                    array[i] = union.get(i);
            }

            String nome = "" + JOptionPane.showInputDialog(null, "Indica il prodotto da aggiungere", "Articolo", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
            if (!"null".equals(nome)) {
                String[] array1 = new String[5];
                for (int i = 0; i < 5; i++) array1[i] = (i+1)+"";
                Articolo articolo = ArticoloBusiness.getInstance().getByName(nome);
                Prodotto p = ProdottoBusiness.getInstance().getOneProducts(articolo.getId());
                String quantita = "" + JOptionPane.showInputDialog(null, "Indica il prodotto da aggiungere", "Articolo", JOptionPane.PLAIN_MESSAGE, null, array1, array1[0]);
                if (!"null".equals(quantita)) {
                    p.setQuantita(Integer.parseInt(quantita));
                    ProdottoCompositoBusiness.getInstance().addComponente(prodottoComposito, p);
                    JOptionPane.showMessageDialog(null, "Prodotto aggiunto", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            prodottoComposito = ProdottoCompositoBusiness.getInstance().getOneProdottoComposito(prodottoComposito.getId());
            //prodottoComposito.setPrezzo(prodottoComposito.getPrezzo()); lo faccio già nel model di prodottoComposito

            ArticoloBusiness.getInstance().updateArticolo(prodottoComposito);
            appFrame.setCurrentMainPanel(new ProdottoCompositoPanel(appFrame, prodottoComposito));
        }

        if ("fine".equals(cmd)) appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();
        int row = table.rowAtPoint(point);
        int column = table.columnAtPoint(point);

        try {
            int idArticolo = (int)table.getValueAt(row, 0);
            int quantita = (int)table.getValueAt(row, 3);
            String campo = (String) table.getValueAt(row, column);

            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Modifica quantità".equals(campo)) {

                //MODIFICA QUANTITÀ
                String[] options = {"Aggiungi", "Rimuovi"};
                int res = JOptionPane.showOptionDialog(null, "", "Quantità prodotto", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                switch (res) {
                    //AGGIUNGI QUANTITÀ
                    case 0 -> {
                        Integer[] array = new Integer[5 - quantita];
                        for (int i = 1; i <= array.length; i++) {
                            array[i - 1] = i;
                        }
                        int qdAaggiungere = (int) JOptionPane.showInputDialog(null, "Quantita da aggiungere", "Aggiunta prodotto", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
                        for (IProdotto p : prodottoComposito.getSottoprodotti()) {
                            if (p.getId() == idArticolo) {
                                p.setQuantita(p.getQuantita() + qdAaggiungere);
                                ProdottoCompositoBusiness.getInstance().updateQuantity(prodottoComposito);
                                break;
                            }
                        }
                    }

                    //RIMUOVI QUANTITÀ
                    case 1 -> {
                        Integer[] array = new Integer[quantita];
                        for (int i = 1; i <= quantita; i++) {
                            array[i - 1] = i;
                        }
                        int qdaRimuovere = (int) JOptionPane.showInputDialog(null, "Quantita da rimuovere", "Rimozione prodotto", JOptionPane.PLAIN_MESSAGE, null, array, array[array.length - 1]);
                        for (IProdotto p : prodottoComposito.getSottoprodotti()) {
                            if (p.getId() == idArticolo) {
                                p.setQuantita(p.getQuantita() - qdaRimuovere);
                                ProdottoCompositoBusiness.getInstance().updateQuantity(prodottoComposito);
                                break;
                            }
                        }
                    }

                }
            }

           // prodottoComposito.setPrezzo(prodottoComposito.getPrezzo()); lo faccio già nel model di prodottoComposito
            ArticoloBusiness.getInstance().updateArticolo(prodottoComposito);
            appFrame.setCurrentMainPanel(new ProdottoCompositoPanel(appFrame, prodottoComposito));

        } catch (ClassCastException | IndexOutOfBoundsException | NullPointerException ignored) {}
    }

}
