package Model;
//ABSTRACT FACTHORY PATTERN
//Concrete product
public class CategoriaArticolo implements ICategoria{

    public enum tipoCategoria {PRODOTTO, SERVIZIO}

    private String nome;
    private int idCategoria;
    private int idCategoria_padre;
    private tipoCategoria tipoCategoria;

    public CategoriaArticolo(CategoriaArticolo.tipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public CategoriaArticolo() {
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdCategoria_padre() {
        return idCategoria_padre;
    }

    public void setIdCategoria_padre(int idCategoria_padre) {
        this.idCategoria_padre = idCategoria_padre;
    }

    public CategoriaArticolo.tipoCategoria getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(CategoriaArticolo.tipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }
}
