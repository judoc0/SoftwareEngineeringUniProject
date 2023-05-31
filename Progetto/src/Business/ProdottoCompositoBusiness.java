package Business;

import DAO.CategoriaArticoloDAO;
import DAO.ProdottoCompositoDAO;
import Model.CategoriaArticolo;
import Model.IProdotto;
import Model.Prodotto;
import Model.ProdottoComposito;

public class ProdottoCompositoBusiness {

    private static ProdottoCompositoBusiness instance;     //SINGLETON PATTERN

    public static synchronized ProdottoCompositoBusiness getInstance() {
        if (instance == null) instance = new ProdottoCompositoBusiness();
        return instance;
    }

    private ProdottoCompositoBusiness() {}

    public ProdottoComposito getOneProdottoComposito(int id) {

        ProdottoCompositoDAO uDao = ProdottoCompositoDAO.getInstance();

        ProdottoComposito prodottoComposito = uDao.getById(id);
        Prodotto pr = ProdottoBusiness.getInstance().getOneProducts(id);

        prodottoComposito.setId(pr.getId());
        prodottoComposito.setNome(pr.getNome());
        prodottoComposito.setPrezzo(pr.getPrezzo());
        prodottoComposito.setIdCategoria(pr.getIdCategoria());
        CategoriaArticolo cp = CategoriaArticoloDAO.getInstance().findById(pr.getIdCategoria());
        prodottoComposito.setCategoria(cp);
        prodottoComposito.setDescrizione(pr.getDescrizione());
        prodottoComposito.setFoto(pr.getFoto());
        prodottoComposito.setIdPosizione(pr.getIdPosizione());
        prodottoComposito.setPosizione(PosizioneBusiness.getInstance().getOnePosizione(prodottoComposito.getIdPosizione()));

        int i = 0;
        for (IProdotto prodotto : prodottoComposito.getSottoprodotti()) {
            int quantita = prodotto.getQuantita();
            prodotto = ProdottoBusiness.getInstance().getOneProducts(prodotto.getId());
            prodotto.setQuantita(quantita);

            prodottoComposito.getSottoprodotti().set(i, prodotto);
            i++;
        }
        return prodottoComposito;
    }

    public void addComponente(ProdottoComposito prodottoComposito, Prodotto p) {

        ProdottoCompositoDAO uDao = ProdottoCompositoDAO.getInstance();

        uDao.add(prodottoComposito , p);
    }


    public void removeComponente(int idProdotto) {

        ProdottoCompositoDAO uDao = ProdottoCompositoDAO.getInstance();

        uDao.remove(idProdotto);
    }

    public void updateQuantity(ProdottoComposito prodottoComposito) {
        ProdottoCompositoDAO uDao = ProdottoCompositoDAO.getInstance();

        for (IProdotto prodotto : prodottoComposito.getSottoprodotti()) {
            if (prodotto.getQuantita() == 0)
                ProdottoCompositoBusiness.getInstance().deletefromComposito(prodottoComposito, (Prodotto) prodotto);
            else uDao.updateQuantity(prodottoComposito, (Prodotto) prodotto);
        }

    }

    private void deletefromComposito(ProdottoComposito prodottoComposito, Prodotto p) {

        ProdottoCompositoDAO uDao = ProdottoCompositoDAO.getInstance();

        uDao.deletefromComposito(prodottoComposito, p);
    }

    public boolean compositoExists(int id) {

        ProdottoCompositoDAO uDao = ProdottoCompositoDAO.getInstance();

        return uDao.compositoExists(id);
    }

    public boolean belongComposito(int id) {

        ProdottoCompositoDAO uDao = ProdottoCompositoDAO.getInstance();

        return uDao.belongComposito(id);
    }
}
