package org.test.TestLog;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger l = Logger.getLogger(App.class);

    public static void main(String[] args) throws IOException {

        l.debug("test");
        Enumeration<URL> u = App.class.getClassLoader().getResources("log4j.xml");
        int i = 1;
        while (u.hasMoreElements()) {
            URL l = (URL) u.nextElement();
            System.out.println((i++) + "." + l.getPath());
        }
    }
}
