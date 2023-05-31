package DAO;

import Model.ArticoloAcquistato;

import java.util.ArrayList;
import java.util.List;

public interface IArticoloAcquistatoDAO {

    ArrayList<ArticoloAcquistato> findAll();

    List<ArticoloAcquistato> getByIdAcquisto(int idAcquisto);

    void add(ArticoloAcquistato articoloAcquistato);

    ArticoloAcquistato getByArticoloAndCliente(int idArticolo, int idCliente);

    void removeByIdUtente(int id);

    void removeArticolo(int idArticolo);
}
