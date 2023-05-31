package Business;
// BRIDGE PATTERN
// Abstraction
public abstract class Documento {

    protected PdfAPI pdfAPI;

    protected Documento(PdfAPI pdfAPI) {
        this.pdfAPI = pdfAPI;
    }

    public abstract void creaDocumento(String indirizzo);
}
