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


        try (RandomAccessFile toRead = new RandomAccessFile(source, "r");
                RandomAccessFile toWrite = new RandomAccessFile(dist, "rw")) {
            int StringSize = (int) toRead.length() / 2;

            String[] stringsFirstToRead = new String[StringSize];
            String[] stringsSecondToRead = new String[StringSize];
            String[] stringsThirdSortedToRead = new String[20];
            while (toRead.getFilePointer() < 20L) {
                for (int b = 0; b < stringsFirstToRead.length; b++) {
                    stringsFirstToRead[b] = toRead.readLine();
                    if (b >= 9) {
                        for (int c = 0; c < stringsSecondToRead.length; c++) {
                            stringsSecondToRead[c] = toRead.readLine();
                        }
                    }
                }

            }

            stringsThirdSortedToRead = sortArray(sortOneArray(stringsFirstToRead), sortOneArray(stringsSecondToRead));
           for (int i = 0; i < stringsThirdSortedToRead.length; i++) {
                String aStringsThirdSortedToRead = stringsThirdSortedToRead[i];

                toWrite.write(aStringsThirdSortedToRead.getBytes());
               String s = System.getProperty("line.separator");
                toWrite.write(s.getBytes());
            }


            } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }


    }

    public String[] sortArray(String[] firstStrings, String[] secondStrings) {

            int j = 0;
            int i = 0;
            int k = 0;
        int count = firstStrings.length + secondStrings.length;
        int countForI = 0;
        int countForJ = 0;
            int length = firstStrings.length + secondStrings.length;
            String[] stringsThirdToRead = new String[length];

            while (count != 0) {

                    if (firstStrings[i].length() <= secondStrings[j].length()) {


                        if (countForI != firstStrings.length) {
                            stringsThirdToRead[k] = firstStrings[i];
                            countForI++;
                            if (i < firstStrings.length - 1) {
                                i++;
                            }

                        } else {
                            if (countForJ != firstStrings.length) {
                                stringsThirdToRead[k] = secondStrings[j];
                                countForJ++;
                            }
                            if (j < secondStrings.length - 1) {
                                j++;

                            }
                        }

                    } else {

                        if (j < secondStrings.length) {
                            stringsThirdToRead[k] = secondStrings[j];
                        }

                        if (j < secondStrings.length - 1) {
                            j++;

                        }
                    }

                if (k < (length - 1)) {
                    k++;
                }

                count--;
            }

            return stringsThirdToRead;
        }

        public String[] sortOneArray(String[] array) {
            for (int out = 0; out < array.length; out++) {
                for(int in = array.length - 1; in > out; in--) {


                    if (array[in].length() < array[in - 1].length()) {
                        String tmp = array[in];
                        array[in] = array[in - 1];
                        array[in - 1] = tmp;

                    }

                }
            }
            return array;
        }
    }
