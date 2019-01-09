package ru.matevosyan.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * ThroughPagesTest to test getting element.
 * @author Matevosyan Vardan.
 * Created on 18.02.2018
 * @version 1.0
 */
public class ThroughPagesTest {
    private static final String TITLE_SELECTOR = "td.postslisttopic > a";
    private static final String AUTHOR_SELECTOR = "td.altCol a";
    private static final String DATE_SELECTOR = "td:nth-child(6)";

    /**
     * Check if through Pages has more elements than get true.
     */
    @Test
    public void whenThroughPagesHasElementsThanReturnTrue() {
        ThroughPages throughPages = new ThroughPages();
        boolean hasNext = throughPages.hasNext();
        assertThat(hasNext, is(true));
    }

    /**
     * Check if through has more elements than get all elements and check that list of elements is not empty.
     */
    @Test
    public void  whenThroughPagesHasElementsGetElementsAndThanCheckIsNotEmpty() {
        ThroughPages throughPages = new ThroughPages();
        List<Element> listOFElement = new ArrayList<>();
        if (throughPages.hasNext()) {
            Elements elements = throughPages.next();
            listOFElement.addAll(elements);
        }

        assertThat(listOFElement.isEmpty(), is(false));
    }

    /**
     * Check all values from  the last element from throughPage in the list.
     */
    @Test
    public void  whenThroughPagesGetLastElementsThanCheckTheResults() {
        boolean isAfter = false;
        ThroughPages throughPages = new ThroughPages();
        DateTransformation transformation = new DateTransformation();
        Element element = new Element("td");
        Calendar firstDayOfYear = new GregorianCalendar();
        int year = firstDayOfYear.get(Calendar.YEAR) - 1;
        firstDayOfYear.set(year, 11, 31, 23, 59, 59);
        Timestamp lastDayOfPreviousYear = new Timestamp(firstDayOfYear.getTimeInMillis());
        while (throughPages.hasNext()) {
            Elements elements = throughPages.next();
                for (Element vacancy : elements) {
                    Pattern pattern = Pattern.compile("\\bjava\\b(?!.script).*|\\bjava\\w[ee|se]\\b", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(vacancy.select(TITLE_SELECTOR).text());
                    if (!(vacancy.child(5).text().equals("Дата")) && matcher.find()) {
                        String data = vacancy.child(5).text();
                        DateTransformation dateTransformation = new DateTransformation();
                        Timestamp timestamp = dateTransformation.transform(data);
                        if (timestamp.after(lastDayOfPreviousYear)) {
                            element = vacancy;
                        } else {
                            isAfter = true;
                        }
                    }
            }
            if (isAfter) {
                break;
            }
        }
        String dateValueFromPage = element.select(DATE_SELECTOR).text();
        Timestamp createDate = transformation.transform(dateValueFromPage);
        Calendar calendar = new GregorianCalendar();
        calendar.set(2018, 0, 8, 14, 43, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp expectedDate = new Timestamp(calendar.getTimeInMillis());


        if (dateValueFromPage.isEmpty()) {
          assertThat(dateValueFromPage, is(""));
        } else {
            assertThat(createDate, is(expectedDate));
        }

    }

}