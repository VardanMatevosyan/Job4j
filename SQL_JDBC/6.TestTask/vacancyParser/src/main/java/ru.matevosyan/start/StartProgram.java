package ru.matevosyan.start;

import ru.matevosyan.startTimer.StartTimeController;

import java.util.Timer;

/**
 * StartProgram class starting all program in the fixed schedule time.
 * created on 05.02.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */
public class StartProgram {
    private final StartTimeController startTimeController;
    private final Timer timer;
    private final long timeToRebut;
    private static final long TIME_TO_REBUT = 86400000L;

    /**
     * Constructor for StartProgram class.
     * @param timeController assign time for each execution parsing site for new data.
     */

    public StartProgram(long timeController) {
        if (timeController == 0) {
            this.timeToRebut = TIME_TO_REBUT;
        } else {
            this.timeToRebut = timeController;
        }
        this.startTimeController = new StartTimeController();
        this.timer = new Timer(true);
    }

    /**
     * schedule starting program each {@link StartProgram#timeToRebut} times to invoke parsing site.
     */
    public void scheduleStart() {
        this.timer.scheduleAtFixedRate(this.startTimeController, 0, this.timeToRebut);

    }
}
