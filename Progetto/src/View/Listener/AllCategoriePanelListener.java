package View.Listener;

import Business.*;
import Model.*;
import View.AllCategoriePanel;
import View.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
//OBSERVER PATTERN
//ConcreteObserver
public class AllCategoriePanelListener implements ActionListener {

    AppFrame appFrame;

    public AllCategoriePanelListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("crea".equals(cmd)) {

            //CREA CATEGORIA
            String[] options = {"Prodotto", "Servizio"};
            int res = JOptionPane.showOptionDialog(null, "", "Categoria", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            switch (res) {

                case 0 -> {

                    ProdottoFactory pFac = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO);  //ABSTRACT FACTORY client
                    ICategoria c = pFac.creaCategoria();
                    String nome = JOptionPane.showInputDialog(null, "Inserisci nome", "CategoriaArticolo", JOptionPane.PLAIN_MESSAGE)+"";

                    if (!"null".equals(nome) && !"".equals(nome)) {
                        if (CategoriaArticoloBusiness.getInstance().existCategoria(nome))
                            JOptionPane.showMessageDialog(null, "Categoria già esistente", "Categoria", JOptionPane.ERROR_MESSAGE);

                        else  {
                            c.setNome(nome);
                            List<CategoriaArticolo> categoriaArticolos = CategoriaArticoloBusiness.getInstance().getbyTipo(CategoriaArticolo.tipoCategoria.PRODOTTO);
                            String[] array1 = new String[categoriaArticolos.size() + 1];
                            array1[0] = "nessuna";
                            for (int i = 1; i < array1.length; i++) array1[i] = categoriaArticolos.get(i - 1).getNome();

                            String categoria = "" + JOptionPane.showInputDialog(null, "Indica la categoria padre", "Categoria", JOptionPane.PLAIN_MESSAGE, null, array1, array1[0]);
                            if (!"null".equals(categoria)) {
                                if (!"nessuna".equals(categoria))
                                    c.setIdCategoria_padre(CategoriaArticoloBusiness.getInstance().findByName(categoria).getIdCategoria());
                                CategoriaArticoloBusiness.getInstance().addCategoria((CategoriaArticolo)c);
                            }
                            appFrame.setCurrentMainPanel(new AllCategoriePanel(appFrame));
                        }
                    }
                }

                case 1 -> {

                    ServizioFactory pFac = (ServizioFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.SERVIZIO); //ABSTRACT FACTORY client
                    ICategoria c =  pFac.creaCategoria();
                    String nome = JOptionPane.showInputDialog(null, "Inserisci nome", "CategoriaArticolo", JOptionPane.PLAIN_MESSAGE)+"";

                    if (!"null".equals(nome) && !"".equals(nome)) {

                        if (CategoriaArticoloBusiness.getInstance().existCategoria(nome))
                            JOptionPane.showMessageDialog(null, "Categoria già esistente", "Categoria", JOptionPane.ERROR_MESSAGE);

                        else {
                            c.setNome(nome);
                            List<CategoriaArticolo> categoriaArticolos = CategoriaArticoloBusiness.getInstance().getbyTipo(CategoriaArticolo.tipoCategoria.SERVIZIO);
                            String[] array1 = new String[categoriaArticolos.size() + 1];
                            array1[0] = "nessuna";
                            for (int i = 1; i < array1.length; i++) array1[i] = categoriaArticolos.get(i - 1).getNome();

                            String categoria = "" + JOptionPane.showInputDialog(null, "Indica la categoria padre", "Categoria", JOptionPane.PLAIN_MESSAGE, null, array1, array1[0]);
                            if (!"null".equals(categoria)) {
                                if (!"nessuna".equals(categoria))
                                    c.setIdCategoria_padre(CategoriaArticoloBusiness.getInstance().findByName(categoria).getIdCategoria());
                                CategoriaArticoloBusiness.getInstance().addCategoria((CategoriaArticolo) c);
                            }
                            appFrame.setCurrentMainPanel(new AllCategoriePanel(appFrame));
                        }
                    }
                }
            }
        }
    }
}
