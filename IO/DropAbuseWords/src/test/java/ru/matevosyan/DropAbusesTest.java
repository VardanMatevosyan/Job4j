package ru.matevosyan;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    /**
     * Method whenAddFourWordAndTwoOfThisWordIsAbuseWordThanDeleteTwoAbuseWord.
     * check when input stream hold 4 words and 2 of words is abuse.
     * @throws IOException input or output exception.
     */

    @Test
    public void whenAddFourWordAndTwoOfThisWordIsAbuseWordThanDeleteTwoAbuseWord() throws IOException {

        //assign
        String[] abuses = {"abuseWord1", "abuseWord2"};
        String inputString = "abuseWord1 goodWord1 abuseWord2 goodWord2";

        //transforming inputString to array of bytes
        byte[] inputByte = inputString.getBytes();

        try (InputStream inputStream = new ByteArrayInputStream(inputByte);
             OutputStream outputStream = new ByteArrayOutputStream()) {

            //assign
            DropAbuses abusesWord = new DropAbuses();

            //act
            abusesWord.dropAbuses(inputStream, outputStream, abuses);

            //assertion
            assertThat(outputStream.toString(), is("goodWord1 goodWord2"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}