package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.exceptions.WrongPathException;
import pl.edu.agh.kis.pz1.parsers.csv.definition.CSVParser;
import pl.edu.agh.kis.pz1.parsers.xlsx.definition.XLSXParser;
import pl.edu.agh.kis.pz1.util.Util.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.logging.Logger;

import static pl.edu.agh.kis.pz1.util.Util.*;

/**
 * Runner class. Takes two arguments [input xlsx/csv file path] [output csv file path]
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * @param args [input xlsx/csv file path] [output csv file path]
     * @throws IOException in case of bad input etc.
     */
    public static void main(String[] args) throws IOException {

        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

        if (args == null || args.length == 0) {
            throw new WrongPathException();
        }
        String path = args[0];
        String out = args[1];

        if (!out.contains(XML_EXT)) {
            throw new WrongPathException();
        }

        if (path.contains(XLSX_EXT)) {

            XLSXParser xlsxParser = new XLSXParser(path, out);
            try {
                xlsxParser.generateXML();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } else if (path.contains(CSV_EXT)) {
            CSVParser csvParser = new CSVParser(path, out);
            try {
                csvParser.generateXML();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }
}
