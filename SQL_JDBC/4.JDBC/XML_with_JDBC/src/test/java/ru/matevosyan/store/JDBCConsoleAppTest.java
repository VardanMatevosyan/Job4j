package ru.matevosyan.store;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * testing JDBCConsoleApp class.
 * Created on 02.01.2018.
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class JDBCConsoleAppTest {

    /**
     * Create table and insert N int values.
     */

    @Ignore
    @Test
    public void whenCreateTableAndInsertMillionValuesOrFiveValuesToDBThanItIsLessThanFiveMinutes() {
        long start = System.currentTimeMillis();
        int nMilion = 1_000_000;
        int n = 5;
        JDBCConsoleApp consoleApp = new JDBCConsoleApp();
        DBOperation dbOperation = new DBOperation(consoleApp.getDataBaseConnection());
        if (dbOperation.createDBTable()) {
            dbOperation.insertValueToDB(n);
        }
        long end = start - System.currentTimeMillis();
        consoleApp.closeConnectToDB();

        assertTrue((end * 1000) < 300);
    }

}