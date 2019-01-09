package ru.matevosyan;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.net.URISyntaxException;
import java.net.URL;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Test two threads {@link ru.matevosyan.StopThread.CountChar} and {@link ru.matevosyan.StopThread.Timer}.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 06.08.2017
 */

public class StopThreadTest {

    /**
     * Start the program and check output.
     * @throws InterruptedException if thread is interrupted.
     * @throws IOException when stream get input or output exception.
     * @throws URISyntaxException when get file URI and get syntax exception.
     */

    @Test
    public void whenStartToStopThreadAndPassRunnableToTimerThanCheckResultsOnTheConsole() throws InterruptedException,
            IOException, URISyntaxException {
        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        List<String> bigBook = new ArrayList<>();
        String path = setting.getValue("text.txt");
        URL urlToRead = classLoader.getResource(path);

        assert urlToRead != null;
        File textFile = Paths.get(urlToRead.toURI()).toFile();
        try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(textFile),
                "UTF-8"))) {
            while (bufReader.ready()) {
                bigBook.add(bufReader.readLine());
            }
        } catch (IOException ioe) {
            ioe.getMessage();
        }

        StopThread stopThread = new StopThread();

//        when time passing to the timer is to big.
        StopThread.CountChar countChar = stopThread.new CountChar(bigBook.toString());
        Thread timer = stopThread.new Timer(20000000, countChar);


        countChar.start();
        timer.start();

        countChar.join();
        timer.join();

//        another testing moment when time passing to the timer is to small.
        StopThread stopThread2 = new StopThread();
        StopThread.CountChar countChar2 = stopThread2.new CountChar(bigBook.toString());
        Thread timer2 = stopThread2.new Timer(20, countChar2);

        countChar2.start();
        timer2.start();

        countChar2.join();
        timer2.join();
    }

}