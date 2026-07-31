package model;

import java.text.Normalizer;
import java.util.Locale;

public class Message {
    private String originalText;
    private String normalizedText;

    public Message(String originalText) {
        this.originalText = originalText;
        this.normalizedText = normalizedMessage();
    }
    public String normalizedMessage() {
        if (originalText == null)   {
            return "";
        }

        String text = originalText.toLowerCase();
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");
        return text;
    }
    public boolean contains(String term)    {
        if (term == null || normalizedText == null) {
            return false;
        }
        String normalizedTerm = Normalizer.normalize(term.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return normalizedText.contains(normalizedTerm);

    }
    public String getOriginalText() {
        return originalText;
    }
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
        this.normalizedText = normalizedMessage();
    }
    public String getNormalizedText() {
        return normalizedText;
    }

}
