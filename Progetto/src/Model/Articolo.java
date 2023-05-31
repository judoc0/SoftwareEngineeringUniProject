package Model;

import java.util.List;

public class Articolo implements IArticolo{

    private int id;
    protected Float prezzo;
    private List<FeedBack> feedbacks;
    private String nome;
    private int idCategoria;
    private CategoriaArticolo categoria;
    private String descrizione;
    private byte[] foto;
    private String percorsoFoto;

    public Articolo() {}

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public CategoriaArticolo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaArticolo categoria) {
        this.categoria = categoria;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getPercorsoFoto() {
        return percorsoFoto;
    }

    public void setPercorsoFoto(String percorsoFoto) {
        this.percorsoFoto = percorsoFoto;
    }

    public List<FeedBack> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedBack> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
