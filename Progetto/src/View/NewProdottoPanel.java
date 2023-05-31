package View;

import Business.CategoriaArticoloBusiness;
import Business.ProduttoreBusiness;
import View.Listener.NewProductPanelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//OBSERVER PATTERN
//Subject
public class NewProdottoPanel extends JPanel {

    JTextField nome;
    JTextField prezzo;
    JTextArea descrizione;
    JButton foto;
    JTextField percorsoFoto;
    JTextField corsia;
    JTextField scaffale;
    JCheckBox checkBox2;

    JLabel produttore;
    JLabel categoria;

    public NewProdottoPanel(AppFrame appFrame, String nomeProduttore, String nomeCategoria) {

        NewProductPanelListener listener = new NewProductPanelListener(appFrame,this);

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
        panel1.setLayout(new GridLayout(9, 2));

        nome = new JTextField(20);
        prezzo = new JTextField(20);
        categoria = new JLabel(nomeCategoria);
        descrizione = new JTextArea(20, 20);
        foto = new JButton("Seleziona immagine");
        foto.setActionCommand("foto");
        foto.addActionListener(listener);
        percorsoFoto = new JTextField(10);
        produttore = new JLabel(nomeProduttore);
        corsia = new JTextField(20);
        scaffale = new JTextField(20);

        nome.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        prezzo.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        categoria.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        descrizione.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        foto.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        percorsoFoto.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        produttore.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        corsia.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));
        scaffale.setFont(new Font(nome.getFont().getFontName(), nome.getFont().getStyle(), 17));

        nome.setHorizontalAlignment(SwingConstants.CENTER);
        prezzo.setHorizontalAlignment(SwingConstants.CENTER);
        categoria.setHorizontalAlignment(SwingConstants.CENTER);
       // descrizione.setHorizontalAlignment(SwingConstants.CENTER);
        foto.setHorizontalAlignment(SwingConstants.CENTER);
        percorsoFoto.setHorizontalAlignment(SwingConstants.CENTER);
        produttore.setHorizontalAlignment(SwingConstants.CENTER);
        corsia.setHorizontalAlignment(SwingConstants.CENTER);
        scaffale.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNome = new JLabel("Nome: ", SwingConstants.CENTER);
        JLabel lblPrezzo = new JLabel("Prezzo: ", SwingConstants.CENTER);
        JLabel lblCategoria = new JLabel("Categoria: ", SwingConstants.CENTER);
        JLabel lblDescrizione = new JLabel("Descrizione: ", SwingConstants.CENTER);
        JLabel lblFoto = new JLabel("Foto (percorso): ", SwingConstants.CENTER);
        JLabel lblProduttore = new JLabel("Nome produttore: ", SwingConstants.CENTER);
        JLabel lblCorsia = new JLabel("Corsia (lettera): ", SwingConstants.CENTER);
        JLabel lblScaffale = new JLabel("Scaffale (numero): ", SwingConstants.CENTER);
        JLabel lblProdottoComposito = new JLabel("Prodotto Composito",SwingConstants.CENTER);

        lblNome.setFont(new Font(lblNome.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblPrezzo.setFont(new Font(lblPrezzo.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblCategoria.setFont(new Font(lblCategoria.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblDescrizione.setFont(new Font(lblDescrizione.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblFoto.setFont(new Font(lblFoto.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblProduttore.setFont(new Font(lblFoto.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblCorsia.setFont(new Font(lblFoto.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblScaffale.setFont(new Font(lblFoto.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        lblProdottoComposito.setFont(new Font(lblFoto.getFont().getFontName(), lblNome.getFont().getStyle(), 17));

        JButton newArticolo = new JButton("Crea Articolo");
        newArticolo.setFont(new Font(newArticolo.getFont().getFontName(), lblNome.getFont().getStyle(), 17));
        newArticolo.setActionCommand("newArticolo");
        newArticolo.addActionListener(listener);
        newArticolo.setPreferredSize(new Dimension(300,50));


        checkBox2 = new JCheckBox();
        checkBox2.setBounds(100,150, 50,50);
        checkBox2.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox2.setActionCommand("checkBox2");
        checkBox2.addItemListener(listener);


        JLabel istruzioni = new JLabel("Inserisci i dati per completare la creazione del prodotto");
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
        panel1.add(produttore);
        panel1.add(lblCorsia);
        panel1.add(corsia);
        panel1.add(lblScaffale);
        panel1.add(scaffale);
        panel1.add(lblProdottoComposito);
        panel1.add(checkBox2);

        panel2.add(newArticolo);

    }

    public String getNome() {
        return nome.getText();
    }

    public void setPrezzo(String prezzo) {
        this.prezzo.setText(prezzo);
    }

    public JTextField getPrezzoText() {
        return prezzo;
    }

    public Float getPrezzo() {
        if (prezzo.getText().equals("Il prezzo verrà deciso nella seguente pagina")) return 0f;
        return Float.parseFloat(prezzo.getText());
    }

    public JCheckBox getCheckBox2() {
        return checkBox2;
    }

    public void setCheckBox2(JCheckBox checkBox2) {
        this.checkBox2 = checkBox2;
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

    public void setFoto(String imagePath){
        percorsoFoto.setText(imagePath);
    }

    public void clearFields() {
        nome.setText("");
        prezzo.setText("");
        descrizione.setText("");
        percorsoFoto.setText("");
        nome.setText("");
        corsia.setText("");
        scaffale.setText("");
    }

    public String getCorsia() {
        return corsia.getText();
    }

    public String getScaffale() {
        return scaffale.getText();
    }

    public int getProduttore() {
        return ProduttoreBusiness.getInstance().getProduttoreByName(produttore.getText()).getIdProduttore();
    }

    public Boolean isProdottoComposito() {
        return checkBox2.isSelected();
    }
}
