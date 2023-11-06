package pl.edu.agh.kis.pz1.parsers.xlsx.definition;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.edu.agh.kis.pz1.entities.CtrlLine;
import pl.edu.agh.kis.pz1.entities.Invoice;
import pl.edu.agh.kis.pz1.entities.Invoices;
import pl.edu.agh.kis.pz1.util.enums.InvoiceType;

import javax.swing.text.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import static pl.edu.agh.kis.pz1.util.Util.reformatStringNumberPln;

/**
 * XLSX parser class
 */
public class XLSXParser {
    private static final Logger logger = Logger.getLogger(XLSXParser.class.getName());

    private XSSFWorkbook workbook;
    private String path;
    private final String out;
    private XSSFSheet sheet;

    /**
     * @param path path of xlsx file
     * @param out  path of result xml file
     */
    public XLSXParser(String path, String out) {
        this.path = path;
        this.out = out;
        init();
    }

    /**
     * Prepare data
     */
    private void init() {
        try {
            workbook = new XSSFWorkbook(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheetAt(0);
        logger.info("Successfully read .xlsx file");

    }

    private Integer getNumberOfCells() {
        return sheet.getLastRowNum() - 1;
    }

    /**
     *
     * @return List of attributes
     * Left jic
     */
    private List<String> getAttributesNames() {
        List<String> result = new ArrayList<>();
        Row firstRow = sheet.getRow(0);
        firstRow.forEach(cell -> result.add(cell.getStringCellValue()));
        result.forEach(logger::info);
        return result;
    }

    /**
     * Generates the XML file and saves it.
     * @throws JAXBException since we're using JAXB methods
     */
    public void generateXML() throws JAXBException {
        Invoices invoices = new Invoices();
        List<Invoice> invoiceList = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        BigDecimal valueOfInvoices = new BigDecimal(0);

        //skipping 1st row
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;
            Invoice invoice = new Invoice();

            cell = cellIterator.next();
            invoice.setRecipientName(cell.getStringCellValue());
            cell = cellIterator.next();
            invoice.setRecipientAddress(cell.getStringCellValue());
            cell = cellIterator.next();
            invoice.setNip(cell.getStringCellValue());
            cell = cellIterator.next();
            invoice.setIssueData(cell.getStringCellValue());
            cell = cellIterator.next();
            invoice.setSaleDate(cell.getStringCellValue());
            cell = cellIterator.next();
            invoice.setInvoiceNumber(cell.getStringCellValue());
            cell = cellIterator.next();
            invoice.setTitle(cell.getStringCellValue());
            cell = cellIterator.next();
            invoice.setNbOfItems(BigDecimal.valueOf(cell.getNumericCellValue()));
            cell = cellIterator.next();
            invoice.setUnitPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
            cell = cellIterator.next();
            invoice.setTaxRate(BigDecimal.valueOf(cell.getNumericCellValue()));
            cell = cellIterator.next();
            invoice.setAmountTax(BigDecimal.valueOf(cell.getNumericCellValue()));
            cell = cellIterator.next();
            invoice.setNetPriceItem(BigDecimal.valueOf(cell.getNumericCellValue()));
            cell = cellIterator.next();
            invoice.setGrossPriceItem(BigDecimal.valueOf(cell.getNumericCellValue()));
            cell = cellIterator.next();
            invoice.setNetPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
            cell = cellIterator.next();
            invoice.setGrossPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
            valueOfInvoices = valueOfInvoices.add(BigDecimal.valueOf(cell.getNumericCellValue()));

            invoice.setInvoiceType(invoice.getGrossPrice().compareTo(new BigDecimal(0)) < 0
                    ? InvoiceType.CORRECTION.getLabel() : InvoiceType.VAT.getLabel());


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

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setPath(String path) {
        this.path = path;
        init();
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public String getPath() {
        return path;
    }


    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    //endregion

}