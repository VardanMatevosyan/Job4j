package ru.matevosyan.start;

import ru.matevosyan.startTimer.StartTimeController;

import java.util.Timer;

/**
 * Created by Admin on 05.02.2018.
 */
public class StartProgram {
    private final StartTimeController startTimeController;
    private final Timer timer;
    private final long timeToRebut;
    private static final long TIME_TO_REBUT = 86400000L;

    public StartProgram(long timeController) {
        if (timeController == 0) {
            this.timeToRebut = TIME_TO_REBUT;
        } else {
            this.timeToRebut = timeController;
        }
        this.startTimeController = new StartTimeController();
        this.timer = new Timer(true);
    }

    public void scheduleStart() {
        this.timer.scheduleAtFixedRate(this.startTimeController, 0, this.timeToRebut);

    }
}
