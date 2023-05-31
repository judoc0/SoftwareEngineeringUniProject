package DAO;

import Model.PuntoVendita_has_articolo;

import java.util.ArrayList;
import java.util.List;

public interface IPuntoVendita_has_articoloDAO {

    ArrayList<PuntoVendita_has_articolo> findAll();

    List<PuntoVendita_has_articolo> getById(int id);

    List<PuntoVendita_has_articolo> findbyArticolo(int id);

    void add(PuntoVendita_has_articolo p);

    void remove(int idPuntoVendita, int idArticolo);

    void removeAll(int idArticolo);
}
