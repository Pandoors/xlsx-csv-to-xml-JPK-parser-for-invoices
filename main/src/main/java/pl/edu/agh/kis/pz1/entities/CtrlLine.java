package pl.edu.agh.kis.pz1.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "FakturaWierszCtrl")
public class CtrlLine {

    private Long numberOfLinesInvoices;
    private BigDecimal valueOfLinesInvoices;

    @XmlElement(name = "liczbaWierszyFaktur")
    public Long getNumberOfLinesInvoices() {
        return numberOfLinesInvoices;
    }

    public void setNumberOfLinesInvoices(Long numberOfLinesInvoices) {
        this.numberOfLinesInvoices = numberOfLinesInvoices;
    }

    @XmlElement(name = "wartoscWierszyFaktur")
    public BigDecimal getValueOfLinesInvoices() {
        return valueOfLinesInvoices;
    }

    public void setValueOfLinesInvoices(BigDecimal valueOfLinesInvoices) {
        this.valueOfLinesInvoices = valueOfLinesInvoices;
    }
}
