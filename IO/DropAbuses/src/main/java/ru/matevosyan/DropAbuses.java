package ru.matevosyan;


import java.io.*;

/**
 * Created DropAbuses for delete abuses.
 * Created on 09.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DropAbuses {
    //private boolean check = false;

    /**
     * dropAbuses created for deleting abuses from String array.
     */

    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) {

            try (BufferedReader buff = new BufferedReader(new InputStreamReader(in))) {
                String s;
                int i = 0;
                while ((s = buff.readLine()) != null) {
                    String[] strings = s.split(" ");

                    if (strings[i].contains(abuses[i])) {
                        abuses[i] = null;
                    }

                    if (abuses[i] != null) {
                        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out))) {
                            bufferedWriter.write(abuses[i]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    i++;
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

    }



}
