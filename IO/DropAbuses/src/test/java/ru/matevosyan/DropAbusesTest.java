package ru.matevosyan;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;

import java.io.*;

/**
 * Created for testing DropAbuses.
 * Created on 14.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DropAbusesTest {

    @Test
    public void whenAddFourWordAndTwoOfThisWordIsAbuseWordThanDeleteTwoAbuseWord() {

        String[] abuses = {"abuseWord1", "abuseWord2"};
        String inputString = "abuseWord1 goodWord abuseWord2 goodWord2";
        byte[] inputByte = inputString.getBytes();

        InputStream inputStream = new ByteArrayInputStream(inputByte);
        OutputStream outputStream = new ByteOutputStream();

    }

}