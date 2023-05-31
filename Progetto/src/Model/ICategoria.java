package Model;
//ABSTRACT FACTHORY PATTERN
//Abstract Products
public interface ICategoria {

    String getNome();

    void setNome(String nome);

    int getIdCategoria();

    void setIdCategoria_padre(int idCategoria_padre);
}
