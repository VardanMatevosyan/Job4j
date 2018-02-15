package ru.matevosyan.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.dataBase.ConnectionDB;
import ru.matevosyan.dataBase.Utils;
import ru.matevosyan.model.Vacancy;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 29.01.2018.
 */
public class OnePageParser {
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final Logger LOG = LoggerFactory.getLogger(VacancyParser.class.getName());
    private final Connection connectionDB;
    private final DateTransformation transformation = new DateTransformation();

    public OnePageParser() {
        this.connectionDB = ConnectionDB.getConnection();
    }

    public ArrayList<Vacancy> pageParser(Elements elements) {
        ArrayList<Vacancy> listOfVacancy = new ArrayList<>();
        String tittle;
        String author;
        Timestamp create_date;
        Iterator<Element> itrElement = elements.iterator();

        while (itrElement.hasNext()) {
            Element element = itrElement.next();
            Pattern pattern = Pattern.compile("\\bjava\\b(?!.script).*|\\bjava\\w[ee|se]\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(element.select("td.postslisttopic > a").text());
            if (matcher.find()) {
                tittle = element.select("td.postslisttopic > a").text();
                author = element.select("td.altCol a").text();
                create_date = transformation.transform(element.select("td:nth-child(6)").text());
                if (!Utils.isTheSameAsInTheDatabase(tittle, author, create_date)) {
                    listOfVacancy.add(new Vacancy(tittle, author, create_date));
                }
            }
        }
        return listOfVacancy;
    }
}
