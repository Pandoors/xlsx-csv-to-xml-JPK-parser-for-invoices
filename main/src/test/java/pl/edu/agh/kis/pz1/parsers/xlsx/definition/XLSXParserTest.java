package pl.edu.agh.kis.pz1.parsers.xlsx.definition;

import junit.framework.TestCase;

import javax.xml.bind.JAXBException;

public class XLSXParserTest extends TestCase {

    public void testGenerateXML() {

        XLSXParser xlsxParser = new XLSXParser("path", "out.xml");

        try {
            xlsxParser.generateXML();
        } catch (JAXBException ignored){

        }
    }
}