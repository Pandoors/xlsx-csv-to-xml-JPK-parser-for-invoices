package pl.edu.agh.kis.pz1.util;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Utility class
 */
public class Util {

    private Util() {
    }

    public static String XLSX_EXT = ".xlsx";
    public static String CSV_EXT = ".csv";
    public static String XML_EXT = ".xml";

    /**
     *
     * @param s input String to reformat
     * @return output BigDecimal
     */
    public static BigDecimal reformatStringNumber(String s) {

        s = s.replaceAll(",", ".");
        s = s.replaceAll(" ","");
        return new BigDecimal(s);
    }

    /**
     *
     * @param s input String to reformat
     * @return BigDecimal reformatted value
     */
    public static BigDecimal reformatStringNumberPln(String s) {
        if (s.length() > 3) {
            s = s.substring(0, s.length() - 3);
        }
        s = s.replaceAll(",", ".");

        s = s.replaceAll(" ","");

        return new BigDecimal(s);
    }


}
