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
    private BufferedWriter stream = null;

    public BufferedWriter getStream() {
        return stream;
    }

    /**
     * dropAbuses created for deleting abuses from String array.
     */

    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) {

            try (BufferedReader buff = new BufferedReader(new InputStreamReader(in))) {
                String s;
                int i = 0;
                while ((s = buff.readLine()) != null) {
                    String[] strings = s.split(" ");

                    for (int p = 0; p < abuses.length; p++) {
                        for (int j = 0; j < strings.length; j++) {
                            if (abuses[p].equals(strings[j])) {
                                strings[j] = null;
                            }
                        }
                    }

                    for (int k = 0; k < strings.length; k++) {
                        if (strings[k] != null) {
//                            String[] splitToWords = new String[strings.length + (strings.length / 2)];
                            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out))) {
//                                String[] split = abuses[k].split(" ");
//                                ?splitToWords = abuses[k].split(" ");
                                bufferedWriter.write(strings[k]);
                                this.stream = bufferedWriter;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    i++;
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

    }

    @Override
    public String toString() {
        return "DropAbuses{" +
                "stream=" + stream +
                '}';
    }
}
