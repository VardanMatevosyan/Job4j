package ru.matevosyan.parser;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class DateTransformation format date.
 * Created on 28.01.2018
 * @author Matevosya Vardan.
 * @version 1.0
 */

public class DateTransformation {
    private final SimpleDateFormat format;
    private static final long ONE_DAY_IN_MILLIS = 8_6400_000L;

    /**
     * DateTransformation constructor.
     */

    public DateTransformation() {
        format = new SimpleDateFormat("dd MMM yy, HH:mm");
    }

    /**
     * Transform date in String to date in Timestamp.
     * @param date date.
     * @return transform date.
     */

    public Timestamp transform(final String date) {
        long convertedDate = 0;
        if (date.contains("сегодня")) {
            return this.transformTodayDate(date);
        }
        if (date.contains("вчера")) {
            return this.transformYesterdayDate(date);
        }
        try {
            convertedDate = format.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(convertedDate);
    }

    /**
     * Transform passing date to date date value in Timestamp.
     * @param date passing date.
     * @return today date.
     */

    private Timestamp transformTodayDate(final String date) {
        final String[] timeFromDate = this.getTimeFromDate(date);
        Calendar today = new GregorianCalendar();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),
                Integer.parseInt(timeFromDate[0]), Integer.parseInt(timeFromDate[1]));
        return new Timestamp(today.getTimeInMillis());
    }

    /**
     * Transform passing date to yesterday date value in Timestamp.
     * @param date passing date.
     * @return today date.
     */

    private Timestamp transformYesterdayDate(final String date) {
        return new Timestamp(this.transformTodayDate(date).getTime() - ONE_DAY_IN_MILLIS);
    }

    /**
     * Transform passing date value to array with time value.
     * @param date passing date.
     * @return array with hour and seconds.
     */

    private String[] getTimeFromDate(final String date) {
        return date.split(" ")[1].split(":");
    }


}
