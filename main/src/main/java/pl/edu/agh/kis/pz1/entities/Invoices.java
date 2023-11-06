package pl.edu.agh.kis.pz1.entities;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "JPKFile")
@XmlType(propOrder = {"invoices", "controlLine"})
public class Invoices {

    private List<Invoice> invoices;
    private CtrlLine controlLine;


    @XmlElementWrapper(name = "faktury")
    @XmlElement(name = "faktura")
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @XmlElement(name = "FakturaWierszCtrl")
    public CtrlLine getControlLine() {
        return controlLine;
    }

    public void setControlLine(CtrlLine controlLine) {
        this.controlLine = controlLine;
    }
}
