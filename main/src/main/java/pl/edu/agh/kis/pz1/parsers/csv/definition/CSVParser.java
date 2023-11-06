package pl.edu.agh.kis.pz1.parsers.csv.definition;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import pl.edu.agh.kis.pz1.entities.CtrlLine;
import pl.edu.agh.kis.pz1.entities.Invoice;
import pl.edu.agh.kis.pz1.entities.Invoices;
import pl.edu.agh.kis.pz1.parsers.xlsx.definition.XLSXParser;
import pl.edu.agh.kis.pz1.util.enums.InvoiceType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import static pl.edu.agh.kis.pz1.util.Util.reformatStringNumber;
import static pl.edu.agh.kis.pz1.util.Util.reformatStringNumberPln;

/**
 * CSV parser implementation
 */
public class CSVParser {
    private static final Logger logger = Logger.getLogger(CSVParser.class.getName());
    private String path;
    private Reader in;
    private final String out;
    private Iterable<CSVRecord> records;

    /**
     *
     * @param path path of csv file
     * @param out path of result xml file
     */
    public CSVParser(String path, String out) {
        this.path = path;
        this.out = out;
        init();
    }

    /**
     * Prepare data
     */
    private void init() {
        try {
            in = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            records = CSVFormat.EXCEL
                    .withDelimiter('\t')
                    .parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Generates the XML file and saves it.
     * @throws JAXBException since we're using JAXB methods
     */
    public void generateXML() throws JAXBException {

        Invoices invoices = new Invoices();
        List<Invoice> invoiceList = new ArrayList<>();
        Iterator<CSVRecord> recordIterator = records.iterator();
        BigDecimal valueOfInvoices = new BigDecimal(0);
        recordIterator.next();

        while (recordIterator.hasNext()) {
            CSVRecord record = recordIterator.next();
            String cell;
            Iterator<String> contentIterator = record.iterator();
            Invoice invoice = new Invoice();

            cell = contentIterator.next();
            invoice.setRecipientName(cell);
            cell = contentIterator.next();
            invoice.setRecipientAddress(cell);
            cell = contentIterator.next();
            invoice.setNip(cell);
            cell = contentIterator.next();
            invoice.setIssueData(cell);
            cell = contentIterator.next();
            invoice.setSaleDate(cell);
            cell = contentIterator.next();
            invoice.setInvoiceNumber(cell);
            cell = contentIterator.next();
            invoice.setTitle(cell);
            cell = contentIterator.next();
            invoice.setNbOfItems(reformatStringNumber(cell));
            cell = contentIterator.next();
            invoice.setUnitPrice(reformatStringNumberPln(cell));
            cell = contentIterator.next();
            invoice.setTaxRate(reformatStringNumber(cell));
            cell = contentIterator.next();
            invoice.setAmountTax(reformatStringNumberPln(cell));
            cell = contentIterator.next();
            invoice.setNetPriceItem(reformatStringNumberPln(cell));
            cell = contentIterator.next();
            invoice.setGrossPriceItem(reformatStringNumberPln(cell));
            cell = contentIterator.next();
            invoice.setNetPrice(reformatStringNumberPln(cell));
            cell = contentIterator.next();
            invoice.setGrossPrice(reformatStringNumberPln(cell));

            invoice.setInvoiceType(invoice.getGrossPrice().compareTo(new BigDecimal(0)) < 0
                    ? InvoiceType.CORRECTION.getLabel() : InvoiceType.VAT.getLabel());

            valueOfInvoices = valueOfInvoices.add(reformatStringNumberPln(cell));

            invoiceList.add(invoice);
            invoices.setInvoices(invoiceList);

        }
        CtrlLine ctrlLine = new CtrlLine();
        ctrlLine.setNumberOfLinesInvoices((long) invoiceList.size());
        ctrlLine.setValueOfLinesInvoices(valueOfInvoices);
        invoices.setControlLine(ctrlLine);
        //finally...
        JAXBContext context = JAXBContext.newInstance(Invoices.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(invoices, System.out);
        m.marshal(invoices, new File(out));

    }


    //region getters setters

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        init();
    }


    public Iterable<CSVRecord> getRecords() {
        return records;
    }
    //endregion
}
