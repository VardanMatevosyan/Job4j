package ru.matevosyan.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.matevosyan.dataBase.Utils;
import ru.matevosyan.model.Vacancy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class OnePageParser parsing single page.
 * Created on 28.01.2018
 * @author Matevosya Vardan.
 * @version 1.0
 */
public class OnePageParser {
    private final DateTransformation transformation;
    private static final String TITLE_SELECTOR = "td.postslisttopic > a";
    private static final String AUTHOR_SELECTOR = "td.altCol a";
    private static final String DATE_SELECTOR = "td:nth-child(6)";

    public OnePageParser() {
        this.transformation = new DateTransformation();
    }

    /**
     * pageParser getting every single page from the elements, pars it and add build Vacancy object to list.
     * @param elements all pages.
     * @return list of vacancy.
     */

    public ArrayList<Vacancy> pageParser(Elements elements) {
        ArrayList<Vacancy> listOfVacancy = new ArrayList<>();
        String tittle;
        String author;
        Timestamp create_date;
        Iterator<Element> itrElement = elements.iterator();

        while (itrElement.hasNext()) {
            Element element = itrElement.next();
            Pattern pattern = Pattern.compile("\\bjava\\b(?!.script).*|\\bjava\\w[ee|se]\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(element.select(TITLE_SELECTOR).text());
            if (matcher.find()) {
                tittle = element.select(TITLE_SELECTOR).text();
                author = element.select(AUTHOR_SELECTOR).text();
                create_date = transformation.transform(element.select(DATE_SELECTOR).text());
                if (!Utils.isTheSameAsInTheDatabase(tittle, author, create_date)) {
                    listOfVacancy.add(new Vacancy(tittle, author, create_date));
                }
            }
        }
        return listOfVacancy;
    }
}
