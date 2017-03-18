package ru.matevosyan;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

/**
 * Created DropAbuses for delete abuses.
 * Created on 09.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DropAbuses {

    /**
     * DropAbuses created for deleting abuses from String array.
     *
     * @param in     inputStream passing value.
     * @param out    outputStream passing value.
     * @param abuses an array with abuse words inside.
     */

    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) {

        try (BufferedReader buff = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
            String s;
            while ((s = buff.readLine()) != null) {
                for (String abuseWord : abuses) {
                    //delete if sentence contain abuse word
                    s = s.replaceAll(abuseWord, "");
                }
                //print to the console
                PrintStream printStream = new PrintStream(out);
                printStream.print(s);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
