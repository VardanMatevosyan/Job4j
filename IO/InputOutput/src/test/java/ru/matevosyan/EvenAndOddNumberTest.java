package ru.matevosyan;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created for testing EvenAndOddNumber class
 * Created on 09.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class EvenAndOddNumberTest {

    /**
     * whenNumberIsEvenThanReturnTrue test situation when number is even.
     * @throws IOException input and output exception.
     */

    @Test
    public void whenNumberIsEvenThanReturnTrue() throws IOException {
        byte[] number = {1, 4, 3};
        boolean actualCheckEvenAndOddNumber;

        InputStream inputStream = new ByteArrayInputStream(number);

        EvenAndOddNumber evenAndOddNumber = new EvenAndOddNumber();

        actualCheckEvenAndOddNumber = evenAndOddNumber.isNumber(inputStream);

        assertThat(actualCheckEvenAndOddNumber, is(true));
    }

    /**
     * whenNumberIsEvenThanReturnTrue test situation when number is odd.
     * @throws IOException input and output exception.
     */

    @Test
    public void whenNumberIsOddThanReturnFalse() throws IOException {
        byte[] number = {1, 1, 3};
        boolean actualCheckEvenAndOddNumber;

        InputStream inputStream = new ByteArrayInputStream(number);

        EvenAndOddNumber evenAndOddNumber = new EvenAndOddNumber();

        actualCheckEvenAndOddNumber = evenAndOddNumber.isNumber(inputStream);

        assertThat(actualCheckEvenAndOddNumber, is(false));

    }

}