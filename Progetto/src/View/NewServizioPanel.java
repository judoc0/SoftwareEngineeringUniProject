package View;

import Business.CategoriaArticoloBusiness;
import Business.FornitoreBusiness;
import View.Listener.NewServicePanelListener;

import javax.swing.*;
import java.awt.*;
//OBSERVER PATTERN
//Subject
public class NewServizioPanel extends JPanel {

    JTextField nome;
    JTextField prezzo;
    JTextArea descrizione;
    JButton foto;
    JTextField percorsoFoto;
    JLabel fornitore;
    JLabel categoria;

    public NewServizioPanel(AppFrame appFrame, String nomeFornitore, String nomeCategoria) {

        NewServicePanelListener listener = new NewServicePanelListener(appFrame,this);

        setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel fotoPanel = new JPanel();
        fotoPanel.setLayout(new BoxLayout(fotoPanel, BoxLayout.X_AXIS));

        panel1.setBackground(Color.gray);
        panel2.setBackground(Color.gray);

        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        add(panel3, BorderLayout.NORTH);
        panel1.setLayout(new GridLayout(8, 2));

        nome = new JTextField(20);
        prezzo = new JTextField(20);
        categoria = new JLabel(nomeCategoria);
        descrizione = new JTextArea(20, 20);
        foto = new JButton("Seleziona immagine");
        foto.setActionCommand("foto");
        foto.addActionListener(listener);
        percorsoFoto = new JTextField(10);
        fornitore = new JLabel(nomeFornitore);

        nome.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        prezzo.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        categoria.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        descrizione.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        foto.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        percorsoFoto.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        fornitore.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));

        nome.setHorizontalAlignment(SwingConstants.CENTER);
        prezzo.setHorizontalAlignment(SwingConstants.CENTER);
        categoria.setHorizontalAlignment(SwingConstants.CENTER);
       // descrizione.setHorizontalAlignment(SwingConstants.CENTER);
        foto.setHorizontalAlignment(SwingConstants.CENTER);
        percorsoFoto.setHorizontalAlignment(SwingConstants.CENTER);
        fornitore.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNome = new JLabel("Nome: ", SwingConstants.CENTER);
        JLabel lblPrezzo = new JLabel("Prezzo: ", SwingConstants.CENTER);
        JLabel lblCategoria = new JLabel("Categoria: ", SwingConstants.CENTER);
        JLabel lblDescrizione = new JLabel("Descrizione: ", SwingConstants.CENTER);
        JLabel lblFoto = new JLabel("Foto: ", SwingConstants.CENTER);
        JLabel lblProduttore = new JLabel("Nome produttore: ", SwingConstants.CENTER);

        lblNome.setFont(new Font(lblNome.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblPrezzo.setFont(new Font(lblPrezzo.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblCategoria.setFont(new Font(lblCategoria.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblDescrizione.setFont(new Font(lblDescrizione.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblFoto.setFont(new Font(lblFoto.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblProduttore.setFont(new Font(lblFoto.getFont().getFontName(), lblNome.getFont().getStyle(), 17));

        JButton newArticolo = new JButton("Crea Articolo");
        newArticolo.setFont(new Font(newArticolo.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        newArticolo.setActionCommand("newArticolo");
        newArticolo.addActionListener(listener);
        newArticolo.setPreferredSize(new Dimension(300,50));

        JLabel istruzioni = new JLabel("Inserisci i dati per completare la creazione del servizio");
        panel3.add(istruzioni);

        panel1.add(lblNome);
        panel1.add(nome);
        panel1.add(lblPrezzo);
        panel1.add(prezzo);
        panel1.add(lblCategoria);
        panel1.add(categoria);
        panel1.add(lblDescrizione);
        panel1.add(new JScrollPane(descrizione));
        panel1.add(lblFoto);
        fotoPanel.add(foto);
        fotoPanel.add(percorsoFoto);
        panel1.add(fotoPanel);
        panel1.add(lblProduttore);
        panel1.add(fornitore);
        panel2.add(newArticolo);

    }

    public String getNome() {
        return nome.getText();
    }

    public Float getPrezzo() {
        return Float.parseFloat(prezzo.getText());
    }

    public int getCategoria() {
        return CategoriaArticoloBusiness.getInstance().findByName(categoria.getText()).getIdCategoria();
    }

    public String getDescrizione() {
        return descrizione.getText();
    }

    public String getFoto() {
        return percorsoFoto.getText();
    }

    public void clearFields() {
        nome.setText("");
        prezzo.setText("");
        descrizione.setText("");
        foto.setText("");
        nome.setText("");
    }


    public int getFornitore() {
        return FornitoreBusiness.getInstance().getByName(fornitore.getText()).getIdFornitore();
    }

    public void setFoto(String imagePath) {
        percorsoFoto.setText(imagePath);
    }
}
