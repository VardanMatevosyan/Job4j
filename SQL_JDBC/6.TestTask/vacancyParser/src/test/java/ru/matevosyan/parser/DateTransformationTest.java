package ru.matevosyan.parser;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * DateTransformationTest to test transformation date.
 * @author Matevosyan Vardan.
 * Created on 17.02.2018
 * @version 1.0
 */
public class DateTransformationTest {

    /**
     * Today date testing case.
     * Transform to pattern like "dd MMM yy, HH:mm".
     */
    @Test
    public void whenTransformTodayDateThanGetTodayDate() {
        DateTransformation transformer = new DateTransformation();
        String date = "сегодня, 21:26";
        LocalDateTime expected = LocalDateTime.now().withHour(21).withMinute(26).withSecond(0).withNano(0);
        Timestamp transform = transformer.transform(date);
        LocalDateTime localDateTime = transform.toLocalDateTime();

        assertThat(localDateTime, is(expected));
    }

    /**
     * Yesterday date testing case.
     * Transform to pattern like "dd MMM yy, HH:mm".
     */

    @Test
    public void whenTransformYesterdayDateThanGetYesterdayDate() {
        DateTransformation transformer = new DateTransformation();
        String date = "вчера, 21:26";
        LocalDateTime expected = LocalDateTime.now().minusDays(1).withHour(21).withMinute(26).withSecond(0).withNano(0);
        Timestamp transform = transformer.transform(date);
        LocalDateTime localDateTime = transform.toLocalDateTime();

        assertThat(localDateTime, is(expected));
    }

    /**
     * Specific like "16 фев 18, 21:26" testing case.
     * Transform to pattern like "dd MMM yy, HH:mm".
     */

    @Test
    public void whenTransformDetermineDateThanGetDetermineDate() {
        DateTransformation transformer = new DateTransformation();
        String date = "16 фев 18, 21:26";
        LocalDateTime expected = LocalDateTime.now().withYear(2018)
                .withDayOfMonth(16).withMonth(2)
                .withHour(21).withMinute(26).withSecond(0).withNano(0);
        Timestamp transform = transformer.transform(date);
        LocalDateTime localDateTime = transform.toLocalDateTime();

        assertThat(localDateTime, is(expected));

    }


}