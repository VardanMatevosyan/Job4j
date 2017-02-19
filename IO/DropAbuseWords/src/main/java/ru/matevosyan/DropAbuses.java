package ru.matevosyan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
     * @param in inputStream passing value.
     * @param out outputStream passing value.
     * @param abuses an array with abuse words inside.
     */

    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) {

            try (BufferedReader buff = new BufferedReader(new InputStreamReader(in))) {
                String s;
                while ((s = buff.readLine()) != null) {
                    String[] strings = s.split(" ");

                    for (String abuse : abuses) {
                        for (int j = 0; j < strings.length; j++) {
                            if (abuse.equals(strings[j])) {
                                strings[j] = null;
                            }
                        }
                    }

                    for (int k = 0; k < strings.length; k++) {

                        //if each words in an array is't null than write that word in the OutputStream.
                        if (strings[k] != null) {
                            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out))) {

                                //creating space to put space between words and assign on the end of words,
                                // to nothing fo cutting it.
                                String space = " ";
                                if (k == strings.length - 1) {
                                    space = "";
                                }

                                //invoke write method to write an array of words to outputStream,
                                // and wrap it with bufferedWriter.
                                bufferedWriter.write(strings[k] + space);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

    }
}
