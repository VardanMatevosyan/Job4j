package ru.matevosyan;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created SortFile for sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class SortFile {

    public void sort(File source, File dist) throws IOException{
//        String pathSource = "C://Abbat.txt";
//        source = new File(pathSource);

        try (RandomAccessFile toRead = new RandomAccessFile(source, "r");
                RandomAccessFile otWrite = new RandomAccessFile(dist, "w")) {
            int countEnd = 500;
            int countStart = 0;
            toRead.seek(countStart);
            String[] read = new String[500];
            String[] read2 = new String[500];
            String[] read3 = new String[500];
            int i = 0;
            while (countStart < countEnd) {
                read[i++] = toRead.readLine();
                countStart = countEnd;
                countEnd += 500;
                toRead.seek(countStart);

                while (countStart < countEnd) {
                    read2[i++] = toRead.readLine();
                    countStart++;
                }
                countStart++;
            }

            } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }


    }

}
