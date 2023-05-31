package View.Listener;

import Business.*;
import Model.*;
import View.AllArticoliPanel;
import View.AppFrame;
import View.NewProdottoPanel;
import View.ProdottoCompositoPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ItemEvent;

//OBSERVER PATTERN
//ConcreteObserver
public class NewProductPanelListener implements ActionListener, ItemListener{

    NewProdottoPanel newProdottoPanel;
    AppFrame appFrame;
    JCheckBox jCheckBox;

    public NewProductPanelListener(AppFrame appFrame, NewProdottoPanel newProdottoPanel) {
        this.newProdottoPanel = newProdottoPanel;
        this.appFrame = appFrame;
        this.jCheckBox = newProdottoPanel.getCheckBox2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        // SELEZIONE IMMAGINE
        if ("foto".equals(cmd)) {
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

                    newProdottoPanel.setFoto(imagePath);

                }
            }
        }

        // CREAZIONE PRODOTTO
        if ("newArticolo".equals(cmd)) {

            ProdottoFactory pFac = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO); //ABSTRACT FACTORY client
            IProdotto p = pFac.crea();

            try {

                p.setNome(newProdottoPanel.getNome());
                p.setPrezzo(newProdottoPanel.getPrezzo());
                p.setIdCategoria(newProdottoPanel.getCategoria());
                p.setCategoria(CategoriaArticoloBusiness.getInstance().findById(p.getIdCategoria()));
                p.setDescrizione(newProdottoPanel.getDescrizione());
                p.setPercorsoFoto(newProdottoPanel.getFoto());
                p.setIdProduttore(newProdottoPanel.getProduttore());

                IPosizioneFactory factoryMethodProvider = new IPosizioneFactory(); //FACTORY METHOD client
                IPosizione posizione = factoryMethodProvider.getIPosizione("POSIZIONE");
                posizione.setCorsia(newProdottoPanel.getCorsia());
                posizione.setScaffale(newProdottoPanel.getScaffale());
                p.setPosizione((Posizione)posizione);

                NewArticoloResponse res = ProdottoBusiness.getInstance().newProdotto((Prodotto) p);
                JOptionPane.showMessageDialog(newProdottoPanel, res.getMessage(), "Creazione Articolo", res.getjOptionPane());

                if (res.getjOptionPane() == 0)
                    PosizioneBusiness.getInstance().removePosizione(posizione.getIdPosizione());  //newProdottoPanel.clearFields();
                else {
                    if (newProdottoPanel.isProdottoComposito())
                        appFrame.setCurrentMainPanel(new ProdottoCompositoPanel(appFrame, (Prodotto) p));
                    else appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame));
                }

            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(newProdottoPanel,
                        "Errore formato prezzo",
                        "Creazione Articolo",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            newProdottoPanel.setPrezzo("Il prezzo verrà deciso nella seguente pagina");
            newProdottoPanel.getPrezzoText().setEditable(false);
        }
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            newProdottoPanel.getPrezzoText().setEditable(true);
            newProdottoPanel.getPrezzoText().setText("");
        }
    }
}
