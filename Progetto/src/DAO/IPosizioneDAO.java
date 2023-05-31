package DAO;

import Model.Posizione;

import java.util.ArrayList;

public interface IPosizioneDAO {

    ArrayList<Posizione> findAll();

    Posizione getById(int id);

    boolean posizioneExists(Posizione posizione);

    void add(Posizione posizione);

    Posizione getByPosizione(Posizione posizione);

    void remove(int posizione);

    void update(Posizione po);
}
