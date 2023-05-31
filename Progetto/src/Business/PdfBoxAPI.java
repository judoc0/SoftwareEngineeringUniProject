package Business;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;
// BRIDGE PATTERN
// Concrete Implementation
public class PdfBoxAPI implements PdfAPI{
    @Override
    public void creaPdf(List<String> text, String outFile) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont font = PDType1Font.COURIER;

            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.beginText();
                contents.setLeading(30f);
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                for (String string : text) {
                    contents.showText(string);
                    contents.newLine();
                }
                contents.endText();
            }

            doc.save(outFile);
            System.out.println("File pdf generato con PdfBox salvato in: " + outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
