package ru.matevosyan.startTimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.start.StartVacancyParser;

import java.sql.Timestamp;
import java.util.TimerTask;

/**
 * StartTimeController class is a TimeTask thread to be invoked and save the last time program running.
 * created on 26.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class StartTimeController extends TimerTask {
    private static Timestamp turnOnParsing;
    private StartVacancyParser startVacancyParser;
    private static final Logger LOG = LoggerFactory.getLogger(StartTimeController.class.getName());

    /**
     * Constructor for StartTimeController.
     */
    public StartTimeController() {
        startVacancyParser = new StartVacancyParser();
    }

    /**
     * Getter for last time running the program.
     * @return turnOnParsing.
     */
    public static Timestamp getTurnOnParsing() {
        return turnOnParsing;
    }

    /**
     * Invoke method for parsing site from the {@link StartVacancyParser}.
     * And save last time running program for parsing.
     */
    @Override
    public void run() {
        turnOnParsing = new Timestamp(System.currentTimeMillis());
        LOG.debug("Get the last time parsing", turnOnParsing.toString());
        startVacancyParser.parsingSite();
    }
}
