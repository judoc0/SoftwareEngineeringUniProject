package View.Listener;

import Business.*;
import Model.*;
import View.AllArticoliPanel;
import View.AppFrame;
import View.NewServizioPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//OBSERVER PATTERN
//ConcreteObserver
public class NewServicePanelListener implements ActionListener {

    NewServizioPanel newServizioPanel;
    AppFrame appFrame;

    public NewServicePanelListener(AppFrame appFrame, NewServizioPanel newServizioPanel) {
        this.newServizioPanel = newServizioPanel;
        this.appFrame = appFrame;
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

                    newServizioPanel.setFoto(imagePath);

                }
            }
        }

        // CREAZIONE SERVIZIO
        if ("newArticolo".equals(cmd)) {

            try {
            ServizioFactory pFac = (ServizioFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.SERVIZIO); //ABSTRACT FACTORY client
            IServizio s = pFac.crea();
            s.setNome(newServizioPanel.getNome());
            s.setPrezzo(newServizioPanel.getPrezzo());
            s.setIdCategoria(newServizioPanel.getCategoria());
            s.setDescrizione(newServizioPanel.getDescrizione());
            s.setPercorsoFoto(newServizioPanel.getFoto());
            s.setIdFornitore(newServizioPanel.getFornitore());

            NewArticoloResponse res = ServizioBusiness.getInstance().newServizio((Servizio) s);
            JOptionPane.showMessageDialog(newServizioPanel, res.getMessage(), "Creazione Articolo", res.getjOptionPane());
            if (res.getjOptionPane() == 1) appFrame.setCurrentMainPanel(new AllArticoliPanel(appFrame)); //newServizioPanel.clearFields();

            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(newServizioPanel,
                        "Errore formato prezzo",
                        "Creazione Articolo",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
