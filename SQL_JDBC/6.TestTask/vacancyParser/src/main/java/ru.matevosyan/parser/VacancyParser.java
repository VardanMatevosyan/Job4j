package ru.matevosyan.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.dataBase.Utils;
import ru.matevosyan.model.Vacancy;
import ru.matevosyan.startTimer.StartTimeController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * VacancyParser class for parsing sql.ru web page.
 * created on 26.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class VacancyParser {
    private final ThroughPages throughPages;
    private final Utils utils;
    private OnePageParser onePageParser = new OnePageParser();
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private boolean isFirstTimeExecuted = false;
    private static final Logger LOG = LoggerFactory.getLogger(VacancyParser.class.getName());

    /**
     * Constructor for {@link VacancyParser}
     */
    public VacancyParser() {
        this.throughPages = new ThroughPages();
        this.utils = new Utils();
        this.isFirstTimeExecuted = true;
    }

    /**
     * parsing data from site.
     */
    public void parsingVacancy() {
        this.throughPages.setCount(1);
        if (this.isFirstTimeExecuted ) {
            this.parsingForYear();
            this.isFirstTimeExecuted = false;
        }
        else {
            this.parsingForNewVacancy();
        }
    }

    /**
     * parsing first page.
     */
    private void firstPageParser() {
        try {
            Document document = Jsoup.connect(SETTINGS.getValue("sql.WebPage")).get();
            String cssSelect = SETTINGS.getValue("css.selectorForOfferLinks");
            Elements elements = document.select(cssSelect);

            List<Vacancy> vacancyElementsList = onePageParser.pageParser(elements);
            if (!vacancyElementsList.isEmpty()) {
                for (Vacancy vacancy : vacancyElementsList) {
                    this.utils.insertValueToDB(vacancy.getTittle(), vacancy.getAuthor(), vacancy.getCreate_date());
                }
            }
        } catch (IOException sqlEx) {
            LOG.warn("Problem with getting document from web page", sqlEx);
        }
    }

    /**
     * parsing all vacancy from the the year staring with the first day of year.
     */
    private void parsAllVacancyForYear() {
        boolean getLastDayOfPrevYear = false;
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR) - 1;
        calendar.set(year, 11, 31, 23, 59, 59);
        Timestamp lastDayOfPreviousYear = new Timestamp(calendar.getTimeInMillis());
        while (throughPages.hasNext()) {
            Elements pageElements = throughPages.next();
            List<Vacancy> vacancyList = onePageParser.pageParser(pageElements);
            for (Vacancy vacancy : vacancyList) {
                if (vacancy.getCreate_date().after(lastDayOfPreviousYear)) {
                    this.utils.insertValueToDB(vacancy.getTittle(), vacancy.getAuthor(), vacancy.getCreate_date());
                } else {
                    getLastDayOfPrevYear = true;
                    break;
                }
            }
            if (getLastDayOfPrevYear) {
                break;
            }
        }
    }

    /**
     * parsing new vacancy.
     */
    private void parsingForNewVacancy() {

        try {
            Document document = Jsoup.connect(SETTINGS.getValue("sql.WebPage")).get();
            String cssSelect = SETTINGS.getValue("css.selectorForOfferLinks");
            Elements elements = document.select(cssSelect);
            List<Vacancy> vacancyElementsList = onePageParser.pageParser(elements);
            if (!vacancyElementsList.isEmpty()) {
                List<Vacancy> list = vacancyElementsList.stream()
                        .filter(vacancy -> vacancy.getCreate_date().after(StartTimeController.getTurnOnParsing()))
                        .collect(Collectors.toList());
                if (!list.isEmpty()) {
                    for (Vacancy vacancy : list) {
                        this.utils.insertValueToDB(vacancy.getTittle(), vacancy.getAuthor(), vacancy.getCreate_date());
                    }
                }

            }
        } catch (IOException sqlEx) {
            LOG.warn("Problem with getting document from web page", sqlEx);
        }
    }

    /**
     * parsing only for year and start with the first day of year.
     */
    private void parsingForYear() {
        this.firstPageParser();
        this.parsAllVacancyForYear();

    }

}
