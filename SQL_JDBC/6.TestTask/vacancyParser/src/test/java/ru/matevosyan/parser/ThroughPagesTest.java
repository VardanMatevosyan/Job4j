package ru.matevosyan.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
    public void when_throughPages_has_elements_than_return_true() {
        ThroughPages throughPages = new ThroughPages();
        boolean hasNext = throughPages.hasNext();
        assertThat(hasNext, is(true));
    }

    /**
     * Check if through has more elements than get all elements and check that list of elements is not empty.
     */
    @Test
    public void  when_throughPages_has_elements__get_elements_and_than_check_is_not_empty() {
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
    public void  when_throughPages_get_last_elements___than_check_the_results() {
        ThroughPages throughPages = new ThroughPages();
        List<Element> listOFElement = new ArrayList<>();
        DateTransformation transformation = new DateTransformation();
        throughPages.next();
        throughPages.next();
        throughPages.next();
        throughPages.next();
        Elements elements = throughPages.next();
        listOFElement.addAll(elements);

        Element element = listOFElement.get(listOFElement.size() - 1);
        String tittle = element.select(TITLE_SELECTOR).text();
        String author = element.select(AUTHOR_SELECTOR).text();
        Timestamp create_date = transformation.transform(element.select(DATE_SELECTOR).text());
        Calendar calendar = new GregorianCalendar();
        calendar.set(2018, 0, 13, 17, 38, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp expectedDate = new Timestamp(calendar.getTimeInMillis());
        assertThat(tittle, is("Системный аналитик, Москва, Дмитровская, зп 80-120 netto [new]"));
        assertThat(author, is("rus_sun"));
        assertThat(create_date, is(expectedDate));
    }

}