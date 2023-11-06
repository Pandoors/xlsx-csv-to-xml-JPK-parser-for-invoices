package pl.edu.agh.kis.pz1.util.enums;

public enum InvoiceType {

    VAT("VAT"),
    CORRECTION("KOREKTA");

    private final String label;

    InvoiceType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
