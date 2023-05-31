package View.Listener;

import Business.*;
import Model.*;
import View.AllPuntiVenditaPanel;
import View.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

//OBSERVER PATTERN
//ConcreteObserver
public class AllPuntiVenditaPanelListener extends MouseAdapter implements ActionListener {

    AppFrame appFrame;

    public AllPuntiVenditaPanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        //CREAZIONE PUNTO VENDITA
        if ("crea".equals(cmd)) {
            String citta = ""+JOptionPane.showInputDialog(appFrame, "Città: ","Punto vendita", JOptionPane.PLAIN_MESSAGE);

            if (!"null".equals(citta) && !"".equals(citta)){
                IPuntoVenditaFactory factoryMethodProvider = new IPuntoVenditaFactory();  //FACTORY METHOD

                IPuntoVendita p = factoryMethodProvider.getIPuntoVendita("PUNTOVENDITA");
                p.setCitta(citta);
                NewPuntoVenditaResponse res = PuntoVenditaBusiness.getInstance().newPuntoVendita((PuntoVendita) p);  //RISPOSTA

                appFrame.setCurrentMainPanel(new AllPuntiVenditaPanel(appFrame));
                JOptionPane.showMessageDialog(null, res.getMessage(),"Punto vendita", res.getjOptionPane());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        JTable table =(JTable) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();
        int row = table.rowAtPoint(point);
        int column = table.columnAtPoint(point);

        try {
            int idPuntoVendita = (int)table.getValueAt(row, 0);
            String campo = (String) table.getValueAt(row, column);
            String campo2 = (String) table.getValueAt(row, 3);
            int idMagazzino = (int) table.getValueAt(row, 2);
            //CREAZIONE MANAGER
            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "gestisci punto vendita".equals(campo)) {

                String[] options = {"Assumi manager", "Quali articoli vendere"};
                int r = JOptionPane.showOptionDialog(null, "", "Punto vendita", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                switch (r) {
                    case 0 -> {
                        // SE MANAGER GIÀ PRESENTE
                        if (!campo2.equals("senza manager"))
                            JOptionPane.showMessageDialog(null,
                                    "Manager già assunto",
                                    "Manager",
                                    JOptionPane.ERROR_MESSAGE);
                            // SE SENZA MANAGER
                        else {
                            // CREAZIONE BOX PER INSERIRE I DATI
                            Box box = Box.createVerticalBox();

                            box.add(new JLabel("Username: "));
                            JTextField username = new JTextField();
                            box.add(username);

                            box.add(new JLabel("Password: "));
                            JTextField password = new JTextField();
                            box.add(password);

                            box.add(new JLabel("Nome: "));
                            JTextField nome = new JTextField();
                            box.add(nome);

                            box.add(new JLabel("Cognome: "));
                            JTextField cognome = new JTextField();
                            box.add(cognome);

                            box.add(new JLabel("Email: "));
                            JTextField email = new JTextField();
                            box.add(email);

                            box.add(new JLabel("Telefono: "));
                            JTextField telefono = new JTextField();
                            box.add(telefono);

                            box.add(new JLabel("Età: "));
                            JTextField eta = new JTextField();
                            box.add(eta);

                            box.add(new JLabel("Residenza: "));
                            JTextField residenza = new JTextField();
                            box.add(residenza);

                            box.add(new JLabel("Lavoro: "));
                            JTextField lavoro = new JTextField();
                            box.add(lavoro);

                            if (JOptionPane.showConfirmDialog(null, box, "Manager", JOptionPane.YES_NO_OPTION) == 0) {

                                IManagerFactory factoryMethodProvider = new IManagerFactory();
                                IManager manager = factoryMethodProvider.getIManager("MANAGER"); //FACTORY METHOD

                                manager.setUsername(username.getText());
                                manager.setPassword(HashClass.getInstance().encrypt(password.getText()));
                                manager.setName(nome.getText());
                                manager.setSurname(cognome.getText());
                                manager.setEmail(email.getText());
                                manager.setPhoneNumber(telefono.getText());
                                manager.setAge(Integer.parseInt(eta.getText()));
                                manager.setResidence(residenza.getText());
                                manager.setJob(lavoro.getText());
                                manager.setIdPuntoVendita(idPuntoVendita);

                                NewUtenteResponse res = ManagerBusiness.getInstance().newAccount((Manager) manager);

                                JOptionPane.showMessageDialog(null, res.getMessage(), "Punto vendita", res.getjOptionPane());

                                appFrame.setCurrentMainPanel(new AllPuntiVenditaPanel(appFrame));
                            }
                        }
                    }
                    case 1 -> {

                        List<Articolo> articoloList = ArticoloBusiness.getInstance().getProducts();
                        List<Articolo> punto1 = PuntoVendita_has_articoloBusiness.getInstance().getByPuntoVendita(idPuntoVendita);

                        Box box = Box.createVerticalBox();

                        JCheckBox[] array = new JCheckBox[articoloList.size()];
                        for (int i = 0; i < array.length; i++) {
                            boolean check = false;
                            for (Articolo p : punto1) {
                                if (articoloList.get(i).getNome().equals(p.getNome())) {
                                    check = true;
                                    break;
                                }
                            }
                            array[i] = new JCheckBox(articoloList.get(i).getNome(), check);
                            box.add(array[i]);
                        }

                        if (JOptionPane.showConfirmDialog(null, box, "Punto vendita", JOptionPane.YES_NO_OPTION) == 0) {
                            for (JCheckBox jCheckBox : array) {
                                boolean check = true;
                                for (Articolo p : punto1) {
                                    if (p.getNome().equals(jCheckBox.getText())) {
                                        check = false;
                                        break;
                                    }
                                }
                                Articolo a = ArticoloBusiness.getInstance().getByName(jCheckBox.getText());
                                if (jCheckBox.isSelected() && check) {
                                    if (ArticoloBusiness.getInstance().typeArticolo(a, ArticoloBusiness.TipoArticolo.PRODOTTO)) {
                                        IDisponibilitaFactory factoryMethodProvider = new IDisponibilitaFactory();  //FACTORY METHOD client
                                        IDisponibilita d = factoryMethodProvider.getIDisponibilita("DISPONIBILITA");
                                        d.setIdMagazzino(idMagazzino);
                                        d.setIdProdotto(a.getId());
                                        d.setQuantita(0);

                                        DisponibilitaBusiness.getInstance().addDisponibilita((Disponibilita) d);
                                    }
                                    IPuntoVendita_has_articoloFactory iPuntoVendita_has_articoloFactory = new IPuntoVendita_has_articoloFactory();  //FACTORY METHOD client
                                    IPuntoVendita_has_articolo puntoVendita_has_articolo = iPuntoVendita_has_articoloFactory.getPuntoVendita_has_articolo("PUNTOVENDITA_HAS_ARTICOLO");
                                    puntoVendita_has_articolo.setIdArticolo(a.getId());
                                    puntoVendita_has_articolo.setIdPuntoVendita(idPuntoVendita);
                                    PuntoVendita_has_articoloBusiness.getInstance().add((PuntoVendita_has_articolo) puntoVendita_has_articolo);

                                } else if (!jCheckBox.isSelected()){
                                    if (ArticoloBusiness.getInstance().typeArticolo(ArticoloBusiness.getInstance().getOneProducts(a.getId()), ArticoloBusiness.TipoArticolo.PRODOTTO)) {
                                        DisponibilitaBusiness.getInstance().removebyMagazzino(a.getId(), idMagazzino);
                                    }
                                    PuntoVendita_has_articoloBusiness.getInstance().remove(idPuntoVendita, a.getId());
                                }
                            }
                            appFrame.setCurrentMainPanel(new AllPuntiVenditaPanel(appFrame));
                            JOptionPane.showMessageDialog(null, "Disponibilità aggiornata", "Punto vendita", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        } catch (ClassCastException | IndexOutOfBoundsException ignored) {}
    }

}
