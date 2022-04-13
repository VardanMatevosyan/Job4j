package ru.matevosyan.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.dataBase.ConnectionDB;
import ru.matevosyan.dataBase.Utils;
import ru.matevosyan.model.Vacancy;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * OnePageParserTest to test one page parser.
 * @author Matevosyan Vardan.
 * Created on 18.02.2018
 * @version 1.0
 */
public class OnePageParserTest {
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final ConnectionDB CONNECTION_DB = new ConnectionDB();
    private static final Utils UTILS = new Utils();

    /**
     * setup database connection and create table.
     */
    @BeforeClass
    public static void setUp() {
        connect();
        createTable();
    }

    /**
     * delete table from the database and close connection to the database.
     */
    @AfterClass
    public static void tearDown() {
        deleteTable();
        close();
    }

    /**
     * when html has Java mansion in the title than get parsed vacancy.
     */
    @Test
    @Ignore
    public void whenAddVacancyHtmlWithTodayDateThanGetAllParsedInfoVacancyWithThisDate() {

        OnePageParser onePageParser = new OnePageParser();
        ArrayList<Vacancy> vacancies = new ArrayList<>();
       List<String> page = new ArrayList<>();
        page.add("<tr>"
                + "<td class=\"postslisttopic\"><a href=\"page\">Java developer 150000</a></td>"
                + "<td class=\"altCol\"><a href=\"page\">danir</a></td>"
                + "<td></td>"
                + "<td></td>"
                + "<td></td>"
                + "<td>сегодня, 21:26</td>"
                + "</tr>");

        Element firstElement = new Element("tr");
        Element element = null;
        for (String aPage : page) {
            element = firstElement.append(aPage);
        }
        assert element != null;
        Elements allTags = new Elements(element.getAllElements());
        List<Element> subListOfElements = allTags.subList(allTags.toString().indexOf("tr") - 1, allTags.toString().indexOf("tr"));
        Elements elements = new Elements(subListOfElements);
        LocalDateTime expected = LocalDateTime.now().withHour(21).withMinute(26).withSecond(0).withNano(0);
        vacancies = onePageParser.pageParser(elements);

        assertThat(vacancies.get(0).getAuthor(), is("danir"));
        assertThat(vacancies.get(0).getTittle(), is("Java developer 150000"));
        assertThat(vacancies.get(0).getCreateDate().toLocalDateTime(), is(expected));
        assertThat(vacancies.size(), is(1));
    }

    /**
     * when html hasn't Java mansion in the title than get empty vacancy list.
     */
    @Test
    @Ignore
    public void whenDddVacancyHtmlWithJavaScriptVacancyThanGetEmptyVacancyList() {
        OnePageParser onePageParser = new OnePageParser();
        List<String> page = new ArrayList<>();
        page.add("<tr>"
                        + "<td class=\"postslisttopic\"><a href=\"page\">JavaScript developer 120000</a></td>"
                        + "<td class=\"altCol\"><a href=\"page\">Villa</a></td>"
                        + "<td></td>"
                        + "<td></td>"
                        + "<td></td>"
                        + "<td>вчера, 20:20</td>"
                        + "</tr>");

        Element firstElement = new Element("tr");
        Element element = null;
        for (String aPage : page) {
            element = firstElement.append(aPage);
        }
        assert element != null;
        Elements allTags = new Elements(element.getAllElements());
        List<Element> subListOfElements = allTags.subList(allTags.toString().indexOf("tr") - 1,
                allTags.toString().indexOf("tr"));
        Elements elements = new Elements(subListOfElements);


        ArrayList<Vacancy> vacancies = onePageParser.pageParser(elements);

        assertThat(vacancies.isEmpty(), is(true));
        assertThat(vacancies.size(), is(0));
    }

    /**
     * connect to the database.
     */
    private static void connect() {
        CONNECTION_DB.connectToDB();
    }

    /**
     * create table vacancy in the database.
     */
    private static void createTable() {
        UTILS.createDBTable(ConnectionDB.getConnection());
    }

    /**
     * delete table from the database.
     */
    private static void deleteTable() {
        try (Statement statement = ConnectionDB.getConnection().createStatement()) {
            statement.execute(SETTINGS.getValue("sql.deleteTable"));
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    /**
     * close connection to the database.
     */
    private static void close() {
        try {
            ConnectionDB.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
