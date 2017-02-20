package ru.matevosyan;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Created SortFile for sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class SortFile {

    public void sort(File source, File dist) throws IOException{


        try (RandomAccessFile toRead = new RandomAccessFile(source, "r");
                RandomAccessFile toWrite = new RandomAccessFile(dist, "rw")) {
            int step = 0;
            int countStart = 0;

            String[] stringsFirstToRead = new String[10];
            String[] stringsSecondToRead = new String[10];
            String[] stringsThirdSortedToRead = new String[20];
            while (toRead.getFilePointer() < 20L) {
                for (int b = 0; b < 10; b++) {
                    stringsFirstToRead[b] = toRead.readLine();
                    if (b >= 9) {
                        for (int c = 0; c < 10; c++) {
                            stringsSecondToRead[c] = toRead.readLine();
                        }
                    }
                }

            }

            stringsThirdSortedToRead = sortArray(stringsFirstToRead, stringsSecondToRead);
           for (int i = 0; i < stringsThirdSortedToRead.length; i++) {
                String aStringsThirdSortedToRead = stringsThirdSortedToRead[i];

                toWrite.write(aStringsThirdSortedToRead.getBytes());
               String s = System.getProperty("line.separator");
                toWrite.write(s.getBytes());
                //toWrite.seek(i);

            }
            //while (toRead.getFilePointer() < 10L) {


//            String[] stringsFirstToReadLast = new String[step];
//            for (int a = 0; a < toRead.length() - step; a++) {
//                toRead.seek(step);
//                stringsFirstToReadLast[a] = toRead.readUTF();
//            }
//            Arrays.sort(stringsFirstToReadLast);
//
//            for (String aStringsFirstToReadLast : stringsFirstToReadLast) {
//                toWrite.seek(stringsThirdSortedToRead.length + 1);
//                toWrite.write(aStringsFirstToReadLast.getBytes());
//            }

            } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }


    }

    public String[] sortArray(String[] firstStrings, String[] secondStrings) {

            int j = 0;
            int i = 0;
            int k = 0;

            int length = firstStrings.length + secondStrings.length;
            String[] stringsThirdToRead = new String[length];

            while (i < firstStrings[i].length() && j < secondStrings[i].length()) {

                if (firstStrings[i].length() <= secondStrings[j].length()) {

                    stringsThirdToRead[k] = firstStrings[i];
                    if (i < firstStrings.length - 1) {
                        i++;
                    }


                } else {

                    stringsThirdToRead[k] = secondStrings[j];
                    if (j < secondStrings.length - 1) {
                        j++;
                    }

                }
                if (k < (length - 1)) {
                    k++;
                }

            }
            while (i < firstStrings[i].length()) {

                stringsThirdToRead[k] = firstStrings[i];

                i++;
                k++;

            }

            while (j < secondStrings[j].length()) {

                stringsThirdToRead[k] = secondStrings[j];

                j++;
                k++;

            }

            return stringsThirdToRead;
        }
    }
