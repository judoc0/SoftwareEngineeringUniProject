package DAO;

import Model.FeedBack;

import java.util.ArrayList;

public interface IFeedbackDAO {

    ArrayList<FeedBack> findAll();

    ArrayList<FeedBack> findAllArticolo(int idArticolo);

    FeedBack getById(int idCommento);

    int add(FeedBack f);

    boolean CommentoExists(int idArticolo, int idCliente);

    FeedBack getByArticoloCliente(int idArticolo, int idCliente);

    int removeById(int idCommento);

    int update(FeedBack feedBack);

    int removeByCliente(int idCliente);

    void removeCommentoArticolo(int idArticolo);
}
