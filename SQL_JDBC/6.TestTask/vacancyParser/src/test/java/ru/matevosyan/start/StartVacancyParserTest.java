package ru.matevosyan.start;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * VacancyParserTest to test parsing site elements and compare the values that should be inserted to database.
 * @author Matevosyan Vardan.
 * Created on 23.02.2018
 * @version 1.0
 */

@RunWith(MockitoJUnitRunner.class)
public class StartVacancyParserTest {
    private static final long TIME_TO_REBUT = 6000L;

    /**
     * Start the program.
     */
    @Ignore
    @Test
    public void notTestJustUseForRunProgramForSeeWhatIsProgramActuallyDo() {
        new StartProgram(TIME_TO_REBUT).scheduleStart();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}