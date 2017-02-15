package ru.matevosyan;


import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created for testing DropAbuses.
 * Created on 14.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DropAbusesTest {

    @Test
    public void whenAddFourWordAndTwoOfThisWordIsAbuseWordThanDeleteTwoAbuseWord() throws IOException {
//        ByteOutputStream byteOutputStream = new ByteOutputStream();
//        System.setOut(new PrintStream(byteOutputStream));

        String[] abuses = {"abuseWord1", "abuseWord2"};
        String inputString = "abuseWord1 goodWord1 abuseWord2 goodWord2";
        byte[] inputByte = inputString.getBytes();

        InputStream inputStream = new ByteArrayInputStream(inputByte);
        OutputStream outputStream = new ByteOutputStream();


        DropAbuses abusesWord = new DropAbuses();

        abusesWord.dropAbuses(inputStream, outputStream, abuses);

//        String[] expectedString = {"goodWord goodWord2"};
//        OutputStream output = new ByteOutputStream();
//        BufferedWriter expectedBufferedWriter = new BufferedWriter( new OutputStreamWriter(output));
//            for (String anExpectedString : expectedString) {
//                if (anExpectedString != null) {
//                    expectedBufferedWriter.write(anExpectedString);
//                }
//            }
//        expectedBufferedWriter.flush();
//        expectedBufferedWriter.close();
        outputStream.close();
        inputStream.close();

        assertThat(outputStream.toString(), is("goodWord1 goodWord2"));
    }

}