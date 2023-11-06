package pl.edu.agh.kis.pz1.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement(name = "faktura")
public class Invoice {

    private String recipientName;
    private String recipientAddress;
    private String nip;
    private String issueData;
    private String saleDate;
    private String invoiceNumber;
    private String title;
    private BigDecimal nbOfItems;
    private BigDecimal unitPrice;
    private BigDecimal taxRate;
    private BigDecimal amountTax;
    private BigDecimal netPriceItem;
    private BigDecimal grossPriceItem;
    private BigDecimal netPrice;
    private BigDecimal grossPrice;
    private String invoiceType;
    @XmlElement(name = "nazwaOdbiorcy")
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    @XmlElement(name = "adresOdbiorcy")
    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    @XmlElement(name = "NIPOdbiorcy")
    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @XmlElement(name = "dataWystawienia")
    public String getIssueData() {
        return issueData;
    }

    public void setIssueData(String issueData) {
        this.issueData = issueData;
    }

    @XmlElement(name = "dataSprzedazy")
    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    @XmlElement(name = "nrFaktury")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @XmlElement(name = "tytulPozycji")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "liczbaSztuk")
    public BigDecimal getNbOfItems() {
        return nbOfItems;
    }

    public void setNbOfItems(BigDecimal nbOfItems) {
        this.nbOfItems = nbOfItems;
    }

    @XmlElement(name = "cenaJednostkowa")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @XmlElement(name = "stawkaPodatku")
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @XmlElement(name = "kwotaPodatku")
    public BigDecimal getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(BigDecimal amountTax) {
        this.amountTax = amountTax;
    }

    @XmlElement(name = "cenaNettoPozycji")
    public BigDecimal getNetPriceItem() {
        return netPriceItem;
    }

    public void setNetPriceItem(BigDecimal netPriceItem) {
        this.netPriceItem = netPriceItem;
    }

    @XmlElement(name = "cenaBruttoPozycji")
    public BigDecimal getGrossPriceItem() {
        return grossPriceItem;
    }

    public void setGrossPriceItem(BigDecimal grossPriceItem) {
        this.grossPriceItem = grossPriceItem;
    }

    @XmlElement(name = "cenaNettoFakturyLacznie")
    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    @XmlElement(name = "cenaBruttoFakturyLacznie")
    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }

    @XmlElement(name = "RodzajFakturty")
    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
}
