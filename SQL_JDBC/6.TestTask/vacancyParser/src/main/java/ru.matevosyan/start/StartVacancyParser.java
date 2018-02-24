package ru.matevosyan.start;

import ru.matevosyan.dataBase.ConnectionDB;
import ru.matevosyan.dataBase.Utils;
import ru.matevosyan.parser.VacancyParser;

/**
 * StartVacancyParser class for parsing sql.ru web page.
 * created on 26.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class StartVacancyParser {
    private final VacancyParser vacancyParser;
    private final Utils utilsDB;

    /**
     * Constructor for StartVacancyParser.
     */
    public StartVacancyParser() {
        this.utilsDB = new Utils();
        new ConnectionDB().connectToDB();
        this.utilsDB.createDBTable(ConnectionDB.getConnection());
        this.vacancyParser = new VacancyParser();

    }

    /**
     * starting parsing vacancy from site use VacancyParser class for using in {@link StartVacancyParser}.
     */
    public void parsingSite() {
        this.vacancyParser.parsingVacancy();
    }
}
