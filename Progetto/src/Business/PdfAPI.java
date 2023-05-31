package Business;

import java.util.List;
// BRIDGE PATTERN
// Implementation
public interface PdfAPI {

    void creaPdf(List<String> text, String outFile);

}
