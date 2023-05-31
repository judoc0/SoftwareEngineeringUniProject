package Business;

import Model.ArticoliLista;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// BRIDGE PATTERN
//Refined Abstractions
public class DocumentoListaAcquisto extends Documento{

    List<ArticoliLista> lista;

    public DocumentoListaAcquisto(List<ArticoliLista> lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public void creaDocumento(String indirizzo) {

        // 1. genera il pdf
        List<String> list = new ArrayList<>();
        String a1 = "ARTICOLO";
        String a2 = "QUANTITA";
        String a3 = "POSIZIONE";
        String text = String.format("%s%25s%25s",a1,a2,a3);
        list.add(text);

        for (ArticoliLista a : lista) {
            String a4 = a.getArticolo().getNome();
            int b4 = a4.length();
            int c4 = 30 - b4;
            String a5 = a.getQuantita()+"";
            int b5 = a5.length();
            int c5 = 35 - b5;
            String a6 = "";
            if (ArticoloBusiness.getInstance().typeArticolo(a.getArticolo(), ArticoloBusiness.TipoArticolo.PRODOTTO))
                a6 = "Corsia: " + a.getPosizione().getCorsia() + " Scaffale: " + a.getPosizione().getScaffale();
            text = String.format("%s%"+c4+"s%"+c5+"s",a4,a5,a6);
            list.add(text);
        }


        File tempFile = null;

        try {
            tempFile = File.createTempFile("listaAcquisto", ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempFile != null) pdfAPI.creaPdf(list, tempFile.getAbsolutePath());

        // 2. lo invia...
        // codice per inviare mail all'indirizzo specificato

        Email email = new Email(new MailJavaxAPI());
        email.inviaEmail(tempFile, indirizzo);

    }
}
