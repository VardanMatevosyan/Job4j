package ru.matevosyan.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.dataBase.ConnectionDB;
import ru.matevosyan.dataBase.Utils;
import ru.matevosyan.model.Vacancy;
import ru.matevosyan.parser.VacancyParser;

import java.util.ArrayList;

/**
 * StartVacancyParser class for parsing sql.ru web page.
 * created on 26.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class StartVacancyParser {
    private final VacancyParser vacancyParser;
    private final Utils utilsDB;
    private static final Logger LOG = LoggerFactory.getLogger(StartVacancyParser.class.getName());

    public StartVacancyParser() {
        this.utilsDB = new Utils();
        new ConnectionDB().connectToDB();
        this.utilsDB.createDBTable(ConnectionDB.getConnection());
        this.vacancyParser = new VacancyParser();

    }

    public void parsingSite() {
        this.vacancyParser.parsingVacancy();
        ArrayList<Vacancy> listOfVacancy = this.vacancyParser.getListOfVacancy();

        for (Vacancy vacancy : listOfVacancy) {
            this.utilsDB.insertValueToDB(vacancy.getTittle(), vacancy.getAuthor(), vacancy.getCreate_date());
        }
    }
}
