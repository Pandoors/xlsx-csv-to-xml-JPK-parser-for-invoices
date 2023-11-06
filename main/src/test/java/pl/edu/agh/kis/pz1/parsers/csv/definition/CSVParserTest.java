package pl.edu.agh.kis.pz1.parsers.csv.definition;

import junit.framework.TestCase;

import javax.xml.bind.JAXBException;

public class CSVParserTest extends TestCase {

    public void testGenerateXML() {

        CSVParser csvParser = new CSVParser("path", "out.xml");
        try {
            csvParser.generateXML();
        } catch (JAXBException ignored){

        }
    }
}