package ru.matevosyan.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.dataBase.ConnectionDB;
import ru.matevosyan.dataBase.Utils;
import ru.matevosyan.model.Vacancy;
import ru.matevosyan.startTimer.StartTimeController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final Logger LOG = LoggerFactory.getLogger(VacancyParser.class.getName());
    private ArrayList<Vacancy> listOfVacancy = new ArrayList<>();
    private final Connection connectionDB;
    private final DateTransformation transformation = new DateTransformation();
    private boolean isFirstTimeExecuted = false;
    private OnePageParser onePageParser = new OnePageParser();
    Vacancy vacancyInTheDB;
    private final ThroughPages throughPages;
    private final Utils utils;
    private static final String PREFERENCES_ID = "isFirstStart";
    private Timestamp startProgramTime = StartTimeController.getTurnOnParsing();
    private static final long ONE_YEAR = 31536000000L;



    public VacancyParser() {
        this.connectionDB = ConnectionDB.getConnection();
        this.throughPages = new ThroughPages(SETTINGS.getValue("page.linksLocation"));
        this.utils = new Utils();
//        this.isFirstTimeExecuted = StartProgram.getPreferences().getBoolean(PREFERENCES_ID, true);
        this.isFirstTimeExecuted = true;
    }


    public void parsingVacancy() {
        this.throughPages.setCount(1);
    if (this.isFirstTimeExecuted ) {
        //parsing unit we get the first day in the year
        this.parsingForYear();
        this.isFirstTimeExecuted = false;
    }
    else {
        //parsing newest in the web site.
        this.parsingForNewVacancy();
    }

    }

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

    private void parsingForYear() {
        this.firstPageParser();
        this.parsAllVacancyForYear();

    }


    /**
     * Getter for list of vacancy.
     * @return list of vacancy.
     */
    public ArrayList<Vacancy> getListOfVacancy() {
        return listOfVacancy;
    }

}
