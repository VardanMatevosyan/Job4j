package ru.matevosyan.startTimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.start.StartVacancyParser;

import java.sql.Timestamp;
import java.util.TimerTask;

/**
 * Created by Admin on 05.02.2018.
 */
public class StartTimeController extends TimerTask {
    private static Timestamp turnOnParsing;
    private StartVacancyParser startVacancyParser;
    private static final Logger LOG = LoggerFactory.getLogger(StartTimeController.class.getName());

    public StartTimeController() {
        startVacancyParser = new StartVacancyParser();
    }

    public static Timestamp getTurnOnParsing() {
        return turnOnParsing;
    }

    @Override
    public void run() {
        turnOnParsing = new Timestamp(System.currentTimeMillis());
        LOG.debug("Get the last time parsing", turnOnParsing.toString());
        startVacancyParser.parsingSite();
    }
}
