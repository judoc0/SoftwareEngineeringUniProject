package DAO;

import Model.ArticoliLista;

import java.util.ArrayList;

public interface ILista_has_ArticoloDAO {

    ArrayList<ArticoliLista> getArticoliLista(int idLista);

    void add(ArticoliLista articoliLista);

    void removeById(ArticoliLista articoliLista);

    void removeAll(int idLista);

    boolean isEmpty(int idLista);

    boolean has_articolo(int idLista, int idArticolo);

    void updateQuantity(ArticoliLista articoliLista);

    ArticoliLista getOneArticoliLista(int idLista, int idArticolo);

    void removefromAllListe(int idArticolo);
}
