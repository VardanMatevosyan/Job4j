package ru.matevosyan;

import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Test two threads {@link ru.matevosyan.SimpleThread.CountWord} and {@link ru.matevosyan.SimpleThread.CountSpace}.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 06.08.2017
 */

public class SimpleThreadTest {

    /**
     * whenPassingToCounterWordThreeWordThanGetThree was created to test {@link ru.matevosyan.SimpleThread.CountWord}.
     * that must to count word in a string.
     */

    @Test
    public void whenPassingToCounterWordThreeWordThanGetThree() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SimpleThread simpleThread = new SimpleThread();
        SimpleThread.CountWord countWord = simpleThread.new CountWord("firstWord secondWord thirdWord");
        Thread wordThread = new Thread(countWord);

        wordThread.start();
        try {
            wordThread.join();
        } catch (InterruptedException ie) {
            ie.getMessage();
        }

        assertTrue(out.toString().contains("3"));
        assertThat(countWord.getCountWord(), is(3));
    }

    /**
     * whenPassingToCounterSpaceThreeWordWithTwoSpacesThanGetTwo was  created to test.
     * {@link ru.matevosyan.SimpleThread.CountSpace} that must to count word in a string.
     */

    @Test
    public void whenPassingToCounterSpaceThreeWordWithTwoSpacesThanGetTwo() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SimpleThread simpleThread = new SimpleThread();
        SimpleThread.CountSpace countSpace = simpleThread.new CountSpace("firstWord secondWord thirdWord");
        Thread spaceThread = new Thread(countSpace);


        spaceThread.start();
        try {
            spaceThread.join();
        } catch (InterruptedException ie) {
            ie.getMessage();
        }

        assertTrue(out.toString().contains("2"));
        assertThat(countSpace.getCountSpace(), is(2));


    }

    /**
     * Print 5000 times each thread and see that first comes one thread than second, after while first isAlive.
     * one more time all this stuff is repeated while thread is stop and it is means that thread run in parallel.
     */

    @Test
    public void whenThreadIsWorkingTogetherThanPrintAndCheckIt() {
        SimpleThread simpleThread = new SimpleThread();
        SimpleThread.CountWord countWord = simpleThread.new CountWord("firstWord secondWord thirdWord");
        SimpleThread.CountSpace countSpace = simpleThread.new CountSpace("firstWord secondWord thirdWord");
        Thread wordThread = new Thread(countWord);
        Thread spaceThread = new Thread(countSpace);


            wordThread.start();
            spaceThread.start();

        try {
            spaceThread.join();
        } catch (InterruptedException ie) {
            ie.getMessage();
        }

    }

    /**
     * Start the program and check output.
     * @throws InterruptedException if thread is interrupted.
     * @throws IOException when stream get input or output exception.
     * @throws URISyntaxException when get file URI and get syntax exception.
     */

    @Test
    public void whenStartThanCheckResultsOnTheConsole() throws InterruptedException, IOException, URISyntaxException {
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

        SimpleThread simpleThread = new SimpleThread("CountSpace", bigBook.toString(), 1000L);
        SimpleThread simpleThreadWordCount = new SimpleThread("CountWord", bigBook.toString(), 1000L);
        simpleThread.startThreads();
        simpleThreadWordCount.startThreads();

    }

}