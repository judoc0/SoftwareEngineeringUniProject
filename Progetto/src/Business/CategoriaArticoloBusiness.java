package Business;

import DAO.CategoriaArticoloDAO;
import Model.CategoriaArticolo;
import Model.ICategoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaArticoloBusiness {

    private static CategoriaArticoloBusiness instance;     //SINGLETON PATTERN

    public static synchronized CategoriaArticoloBusiness getInstance() {
        if (instance == null) instance = new CategoriaArticoloBusiness();
        return instance;
    }

    private CategoriaArticoloBusiness() {}

    public List<CategoriaArticolo> getAllCategorie() {

        CategoriaArticoloDAO uDAO = CategoriaArticoloDAO.getInstance();

        List<CategoriaArticolo> list = uDAO.findCategories();

        for (CategoriaArticolo c : list) {
            c.setNome(c.getNome());
            c.setIdCategoria(c.getIdCategoria());
            c.setIdCategoria_padre(c.getIdCategoria_padre());
            c.setTipoCategoria(c.getTipoCategoria());
        }

        return list;
    }

    public List<CategoriaArticolo> getbyTipo(CategoriaArticolo.tipoCategoria tipoCategoria) {

        List<CategoriaArticolo> list = CategoriaArticoloBusiness.getInstance().getAllCategorie();

        List<CategoriaArticolo> lista = new ArrayList<>();
        for (CategoriaArticolo c : list) {
            if (tipoCategoria.equals(c.getTipoCategoria())) lista.add(c);
        }

        return lista;
    }

    public CategoriaArticolo findByName(String nome) {

        CategoriaArticoloDAO uDAO = CategoriaArticoloDAO.getInstance();

        return  uDAO.findByName(nome);

    }

    public CategoriaArticolo findById(int id) {

        CategoriaArticoloDAO uDAO = CategoriaArticoloDAO.getInstance();

        return  uDAO.findById(id);

    }

    public void addCategoria(CategoriaArticolo c) {

        CategoriaArticoloDAO uDAO = CategoriaArticoloDAO.getInstance();

        uDAO.add(c);
    }

    public boolean existCategoria(String nome) {

        List<CategoriaArticolo> lista = CategoriaArticoloBusiness.getInstance().getAllCategorie();
        for (CategoriaArticolo categoriaArticolo : lista) {
            if (nome.equals(categoriaArticolo.getNome())) {
                return true;
            }
        }
        return false;
    }

}
