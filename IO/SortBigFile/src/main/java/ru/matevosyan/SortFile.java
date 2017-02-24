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

    /**
     * array stringsThirdSortedToRead that write to the file in {@link #sort(File, File)} method.
     */

    private String[] stringsThirdSortedToRead;

    /**
     * for get sorted array.
     * @return stringsThirdSortedToRead an array that hold sorted strings by string length.
     */

    public String[] getStringsThirdSortedToRead() {
        return stringsThirdSortedToRead;
    }

    /**
     * get two files sort that passing first and write sorted array to file that passing second.
     * @param source original file that exist and that must be sorted.
     * @param dist is the file that using to write sorted array.
     * @throws IOException that can be throw when RandomAccessFile was created.
     */

    public void sort(File source, File dist) throws IOException {

        try (RandomAccessFile toRead = new RandomAccessFile(source, "r");
             RandomAccessFile toWrite = new RandomAccessFile(dist, "rw")) {
            int stringSize = 0;
            while (toRead.readLine() != null) {
                stringSize++;
            }

            String[] stringsFirstToRead = new String[stringSize / 2];
            String[] stringsSecondToRead;
            if (stringSize % 2 == 0) {
                stringsSecondToRead = new String[stringSize / 2];
            } else {
                stringsSecondToRead = new String[stringSize / 2 + 1];
            }

            toRead.seek(0);
                for (int b = 0; b < stringsFirstToRead.length; b++) {
                    stringsFirstToRead[b] = toRead.readLine();
                    if (b >= stringsFirstToRead.length - 1) {
                        for (int c = 0; c < stringsSecondToRead.length; c++) {
                            stringsSecondToRead[c] = toRead.readLine();
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
            ioe.getMessage();
            }


    }

    /**
     * Sorted two passing sorted array that merge in one sorted array that was filling in {@link #sort(File, File)}.
     * @param firstStrings sorted array that going to be merged with the second passing array.
     * @param secondStrings sorted array that going to be merged with the first passing array.
     * @return merged array {@link #stringsThirdSortedToRead}
     */

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

    /**
     * Method {@link #sortOneArray(String[])} that sorting array.
     * @param array tha passing for sorted by string length.
     * @return array that was sorted.
     */

    public String[] sortOneArray(String[] array) {
            for (int out = 0; out < array.length; out++) {
                for (int in = array.length - 1; in > out; in--) {

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
