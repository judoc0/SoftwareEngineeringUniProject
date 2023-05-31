package View.Listener;

import Business.*;
import Model.*;
import View.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
//OBSERVER PATTERN
//ConcreteObserver
public class AllArticoliPanelListener extends MouseAdapter implements ActionListener {

    AppFrame appFrame;

    public AllArticoliPanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if ("crea".equals(cmd)) {
            //CREA ARTICOLO
            String[] options = {"Prodotto", "Servizio"};
            int res = JOptionPane.showOptionDialog(null, "", "Articolo", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (res) {

                case 0 -> {
                    //PRODOTTO
                    List<Produttore> produttores = ProduttoreBusiness.getInstance().getProducts();
                    String[] array = new String[produttores.size()];
                    for (int i = 0; i < array.length; i++) {
                        array[i] = produttores.get(i).getNome();
                    }
                    String produttore = "" + JOptionPane.showInputDialog(null, "Indica il produttore del nuovo articolo", "Articolo", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
                    if (!"null".equals(produttore)) {
                        List<CategoriaArticolo> categorieProdotto = CategoriaArticoloBusiness.getInstance().getbyTipo(CategoriaArticolo.tipoCategoria.PRODOTTO);
                        String[] array1 = new String[categorieProdotto.size()];
                        for (int i = 0; i < array1.length; i++) {
                            array1[i] = categorieProdotto.get(i).getNome();
                        }
                        String categoria = "" + JOptionPane.showInputDialog(null, "Indica la categoria del nuovo articolo", "Articolo", JOptionPane.PLAIN_MESSAGE, null, array1, array1[0]);
                        if (!"null".equals(categoria)) {
                            appFrame.setCurrentMainPanel(new NewProdottoPanel(appFrame, produttore, categoria));
                        }
                    }
                }

                case 1 -> {
                    //SERVIZIO
                    List<Fornitore> fornitores = FornitoreBusiness.getInstance().getFornitori();
                    String[] array = new String[fornitores.size()];
                    for (int i = 0; i < array.length; i++) {
                        array[i] = fornitores.get(i).getNome();
                    }
                    String fornitore = "" + JOptionPane.showInputDialog(null, "Indica il fornitore del nuovo servizio", "Articolo", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
                    if (!"null".equals(fornitore)) {
                        List<CategoriaArticolo> categorieProdotto = CategoriaArticoloBusiness.getInstance().getbyTipo(CategoriaArticolo.tipoCategoria.SERVIZIO);
                        String[] array1 = new String[categorieProdotto.size()];
                        for (int i = 0; i < array1.length; i++) {
                            array1[i] = categorieProdotto.get(i).getNome();
                        }
                        String categoria = "" + JOptionPane.showInputDialog(null, "Indica la categoria del nuovo articolo", "Articolo", JOptionPane.PLAIN_MESSAGE, null, array1, array1[0]);
                        if (!"null".equals(categoria)) {
                            appFrame.setCurrentMainPanel(new NewServizioPanel(appFrame, fornitore, categoria));
                        }
                    }
                }
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
            int id = (int)table.getValueAt(row, 0);
            String nome = table.getValueAt(row, 1)+"";
            String campo = (String) table.getValueAt(row, column);
            Articolo a = ArticoloBusiness.getInstance().getOneProducts(id);

            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1 && "Gestisci Articolo".equals(campo)) {
                //GESTISCI ARTICOLO
                String[] options = {"Modifica articolo","Elimina articolo", "Dove vendere"};
                int res = JOptionPane.showOptionDialog(null, nome, "Articolo", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                switch (res) {

                    case 0 -> {
                        String[] options1 = {"Nome", "Categoria", "Prezzo", "Descrizione", "Foto"};

                        if (ArticoloBusiness.getInstance().typeArticolo(ArticoloBusiness.getInstance().getOneProducts(id), ArticoloBusiness.TipoArticolo.PRODOTTO)) {

                            options1 = new String[]{"Nome", "Categoria", "Prezzo", "Descrizione", "Foto", "Posizione"};
                            if (ProdottoCompositoBusiness.getInstance().compositoExists(id))
                                options1 = new String[]{"Nome", "Categoria", "Prezzo", "Descrizione", "Foto", "Posizione", "Composizione"};
                        }
                        int ras = JOptionPane.showOptionDialog(null, "Modifica", "Articolo", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]);

                        switch (ras) {

                            case 0 -> {
                                //NOME
                                String nuovoNome = ""+JOptionPane.showInputDialog(null, "Vecchio nome: " + nome + "\nNuovo nome: ", "Articolo", JOptionPane.PLAIN_MESSAGE);
                                if (!nuovoNome.equals("null") && !nuovoNome.equals("")) {
                                    if (ArticoloBusiness.getInstance().nomeExists(nuovoNome)) JOptionPane.showMessageDialog(null, "Nome già esistente", "Articolo", JOptionPane.ERROR_MESSAGE);
                                    else {
                                        a.setNome(nuovoNome);
                                        ArticoloBusiness.getInstance().updateArticolo(a);
                                        appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                                        JOptionPane.showMessageDialog(null, "Nome modificato", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }

                            case 1 -> {
                                //CATEGORIA
                                List<CategoriaArticolo> categorieProdotto = new ArrayList<>();
                                if (ArticoloBusiness.getInstance().typeArticolo(a, ArticoloBusiness.TipoArticolo.PRODOTTO)) categorieProdotto = CategoriaArticoloBusiness.getInstance().getbyTipo(CategoriaArticolo.tipoCategoria.PRODOTTO);
                                else  categorieProdotto = CategoriaArticoloBusiness.getInstance().getbyTipo(CategoriaArticolo.tipoCategoria.SERVIZIO);
                                String[] array = new String[categorieProdotto.size()];
                                for (int i = 0; i < array.length; i++) {
                                    array[i] = categorieProdotto.get(i).getNome();
                                }
                                String nuovaCategoria = ""+JOptionPane.showInputDialog(null, "Vecchia categoria: " + a.getCategoria().getNome() + "\nNuovo categoria: ", "Articolo", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
                                if (!"null".equals(nuovaCategoria)) {
                                    a.setCategoria(CategoriaArticoloBusiness.getInstance().findByName(nuovaCategoria));
                                    a.setIdCategoria(CategoriaArticoloBusiness.getInstance().findByName(nuovaCategoria).getIdCategoria());
                                    ArticoloBusiness.getInstance().updateArticolo(a);
                                    appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                                    JOptionPane.showMessageDialog(null, "Categoria modificata", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }

                            case 2 -> {
                                //PREZZO
                                String nuovoPrezzo = JOptionPane.showInputDialog(null, "Vecchio Prezzo: " + a.getPrezzo() + "\nNuovo prezzo: ", "Articolo", JOptionPane.PLAIN_MESSAGE);
                                if (nuovoPrezzo != null) {
                                    a.setPrezzo(Float.parseFloat(nuovoPrezzo));
                                    ArticoloBusiness.getInstance().updateArticolo(a);
                                    appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                                    JOptionPane.showMessageDialog(null, "Prezzo modificato", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }

                            case 3 -> {
                                //DESCRIZIONE
                                String[] options2 = {"Invia", "Cancella"};
                                JTextArea ta = new JTextArea(20, 40);
                                ta.setText(a.getDescrizione());

                                if (JOptionPane.showOptionDialog(null, new JScrollPane(ta), "Articolo", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]) == JOptionPane.YES_NO_OPTION) {
                                    a.setDescrizione(ta.getText().replace("'", "\\'"));
                                    ArticoloBusiness.getInstance().updateArticolo(a);
                                    appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                                    JOptionPane.showMessageDialog(null, "Descrizione modificata", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }

                            case 4 -> {
                                //FOTO
                                /*String percorsoFoto = ""+JOptionPane.showInputDialog(null,"Percorso nuova foto: ", "Articolo", JOptionPane.PLAIN_MESSAGE);
                                if (!percorsoFoto.equals("null") && !percorsoFoto.equals("")) {
                                    a.setPercorsoFoto(percorsoFoto);
                                    ArticoloBusiness.getInstance().updateArticolo(a);

                                    appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                                    JOptionPane.showMessageDialog(null, "Foto modificata", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                                }*/
                                JFileChooser fileChooser = new JFileChooser();
                                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                fileChooser.setFileFilter(new FileFilter() {
                                    @Override
                                    public boolean accept(File f) {
                                        if (f.isDirectory()) {
                                            return true;
                                        }

                                        String extension = getFileExtension(f);
                                        if (extension != null) {
                                            return extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png");
                                        }

                                        return false;
                                    }

                                    @Override
                                    public String getDescription() {
                                        return "Immagini (.jpg, .png)";
                                    }

                                    private String getFileExtension(File file) {
                                        String name = file.getName();
                                        int lastDotIndex = name.lastIndexOf(".");
                                        if (lastDotIndex > 0 && lastDotIndex < name.length() - 1) {
                                            return name.substring(lastDotIndex + 1).toLowerCase();
                                        }
                                        return null;
                                    }
                                });

                                int result = fileChooser.showOpenDialog(null);
                                if (result == JFileChooser.APPROVE_OPTION) {
                                    File selectedFile = fileChooser.getSelectedFile();
                                    if (selectedFile != null) {
                                        String imagePath = selectedFile.getAbsolutePath();
                                        // Puoi utilizzare il percorso dell'immagine selezionata per ulteriori elaborazioni

                                        a.setPercorsoFoto(imagePath);
                                        ArticoloBusiness.getInstance().updateArticolo(a);
                                        appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                                        JOptionPane.showMessageDialog(null, "Foto modificata", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }

                            }


                            case 5 -> {
                                //POSIZIONE
                                Box box = Box.createVerticalBox();

                                Prodotto p = ProdottoBusiness.getInstance().getOneProducts(id);
                                Posizione po = p.getPosizione();
                                box.add(new JLabel("Vecchia posizione: Corsia " + po.getCorsia() + ", Scaffale " + po.getScaffale()));

                                box.add(new JLabel("Corsia: "));
                                JTextField corsia = new JTextField();
                                box.add(corsia);

                                box.add(new JLabel("Scaffale: "));
                                JTextField scaffale = new JTextField();
                                box.add(scaffale);


                                if (JOptionPane.showConfirmDialog(null, box, "Posizione", JOptionPane.YES_NO_OPTION) == 0 ){

                                    po.setCorsia(corsia.getText());
                                    po.setScaffale(scaffale.getText());

                                    if (!PosizioneBusiness.getInstance().posizioneExists(po)) {

                                        PosizioneBusiness.getInstance().update(po);

                                        appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                                        JOptionPane.showMessageDialog(null, "Posizione modificata", "Articolo", JOptionPane.INFORMATION_MESSAGE);

                                    } else JOptionPane.showMessageDialog(null, "Posizione già esistente", "Articolo", JOptionPane.ERROR_MESSAGE);
                                }
                            }


                            //COMPOSIZIONE
                            case 6 -> appFrame.setCurrentMainPanel(new ProdottoCompositoPanel(appFrame, ProdottoBusiness.getInstance().getOneProducts(id)));
                        }
                    }

                    case 1 -> {
                        //ELIMINA ARTICOLO
                        if (ProdottoCompositoBusiness.getInstance().belongComposito(id))
                            JOptionPane.showMessageDialog(null, "NON puoi elimanare un articolo presente in un prodotto composito", "Articolo", JOptionPane.ERROR_MESSAGE);
                        else {
                            ArticoloBusiness.getInstance().remove(id);
                            appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                            JOptionPane.showMessageDialog(null, nome + " eliminato", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    case 2 -> {
                        //DISPONIBILITÀ
                        List<PuntoVendita> puntoVenditas = PuntoVenditaBusiness.getInstance().getPuntiVendita();
                        List<PuntoVendita_has_articolo> punto1 = PuntoVendita_has_articoloBusiness.getInstance().getWhereVenduto(id);

                        Box box = Box.createVerticalBox();
                        JCheckBox[] array = new JCheckBox[puntoVenditas.size()];
                        for (int i = 0; i < array.length; i++) {
                            boolean check = false;
                            for (PuntoVendita_has_articolo p : punto1) {
                                if (puntoVenditas.get(i).getCitta().equals(p.getPuntoVendita().getCitta())) {
                                    check = true;
                                    break;
                                }
                            }

                            array[i] = new JCheckBox(puntoVenditas.get(i).getCitta(), check);
                            box.add(array[i]);
                        }

                        if (JOptionPane.showConfirmDialog(null, box, "Articolo", JOptionPane.YES_NO_OPTION) == 0) {
                            for (JCheckBox jCheckBox : array) {
                                boolean check = true;
                                for (PuntoVendita_has_articolo p : punto1) {
                                    if (p.getPuntoVendita().getCitta().equals(jCheckBox.getText())) {
                                        check = false;
                                        break;
                                    }
                                }
                                if (jCheckBox.isSelected() && check) {
                                    if (ArticoloBusiness.getInstance().typeArticolo(ArticoloBusiness.getInstance().getOneProducts(id), ArticoloBusiness.TipoArticolo.PRODOTTO)) {
                                        IDisponibilitaFactory factoryMethodProvider = new IDisponibilitaFactory();  //FACTORY METHOD client
                                        IDisponibilita d = factoryMethodProvider.getIDisponibilita("DISPONIBILITA");
                                        d.setIdMagazzino(PuntoVenditaBusiness.getInstance().geyByCity(jCheckBox.getText()).getMagazzino().getIdMagazzino());
                                        d.setIdProdotto(id);
                                        d.setQuantita(0);

                                        DisponibilitaBusiness.getInstance().addDisponibilita((Disponibilita) d);
                                    }
                                    IPuntoVendita_has_articoloFactory iPuntoVendita_has_articoloFactory = new IPuntoVendita_has_articoloFactory();  //FACTORY METHOD client
                                    IPuntoVendita_has_articolo puntoVendita_has_articolo = iPuntoVendita_has_articoloFactory.getPuntoVendita_has_articolo("PUNTOVENDITA_HAS_ARTICOLO");
                                    puntoVendita_has_articolo.setIdArticolo(id);
                                    puntoVendita_has_articolo.setIdPuntoVendita(PuntoVenditaBusiness.getInstance().geyByCity(jCheckBox.getText()).getIdPuntoVendita());
                                    PuntoVendita_has_articoloBusiness.getInstance().add((PuntoVendita_has_articolo) puntoVendita_has_articolo);

                                } else if (!jCheckBox.isSelected()){
                                    if (ArticoloBusiness.getInstance().typeArticolo(ArticoloBusiness.getInstance().getOneProducts(id), ArticoloBusiness.TipoArticolo.PRODOTTO)) {
                                        DisponibilitaBusiness.getInstance().removebyMagazzino(id, PuntoVenditaBusiness.getInstance().geyByCity(jCheckBox.getText()).getMagazzino().getIdMagazzino());
                                    }
                                    PuntoVendita_has_articoloBusiness.getInstance().remove(PuntoVenditaBusiness.getInstance().geyByCity(jCheckBox.getText()).getIdPuntoVendita(), id);
                                }
                            }
                            appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                            JOptionPane.showMessageDialog(null, "Disponibilità aggiornata", "Articolo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        } catch (ClassCastException | IndexOutOfBoundsException ignored) {}
    }
}
