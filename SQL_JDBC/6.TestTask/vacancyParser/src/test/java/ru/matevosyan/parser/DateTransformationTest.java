package ru.matevosyan.parser;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    public void when_transform_today_date_than_get_today_date() {
        DateTransformation transformer = new DateTransformation();
        String date = "сегодня, 21:26";
        Calendar calendar = new GregorianCalendar();
        calendar.set(2018, 1, 18, 21, 26, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp expectedDate = new Timestamp(calendar.getTimeInMillis());

        Timestamp transform = transformer.transform(date);

        assertThat(transform, is(expectedDate));
    }

    /**
     * Yesterday date testing case.
     * Transform to pattern like "dd MMM yy, HH:mm".
     */

    @Test
    public void when_transform_yesterday_date_than_get_yesterday_date() {
        DateTransformation transformer = new DateTransformation();
        String date = "вчера, 21:26";
        Calendar calendar = new GregorianCalendar();
        calendar.set(2018, 1, 17, 21, 26, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp expectedDate = new Timestamp(calendar.getTimeInMillis());

        Timestamp transform = transformer.transform(date);

        assertThat(transform, is(expectedDate));
    }

    /**
     * Specific like "16 фев 18, 21:26" testing case.
     * Transform to pattern like "dd MMM yy, HH:mm".
     */

    @Test
    public void when_transform_determine_date_than_get_determine_date() {
        DateTransformation transformer = new DateTransformation();
        String date = "16 фев 18, 21:26";
        Calendar calendar = new GregorianCalendar();
        calendar.set(2018, 1, 16, 21, 26, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp expectedDate = new Timestamp(calendar.getTimeInMillis());

        Timestamp transform = transformer.transform(date);

        assertThat(transform, is(expectedDate));
    }


}