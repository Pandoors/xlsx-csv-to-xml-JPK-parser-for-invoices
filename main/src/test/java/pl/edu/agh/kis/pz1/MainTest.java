package pl.edu.agh.kis.pz1;

import junit.framework.TestCase;

import java.io.IOException;

public class MainTest extends TestCase {

    public void testMain() {
        String[] arr = new String[2];
        arr[0] = "path to csv";
        arr[1] = "out.xml";
        try {
            Main.main(arr);
        } catch (IOException ignored) {

        }

    }
}