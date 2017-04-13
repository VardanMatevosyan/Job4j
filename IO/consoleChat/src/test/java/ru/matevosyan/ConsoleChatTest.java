package ru.matevosyan;

import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created SortFile for testing sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ConsoleChatTest {

    private static final String STOP = "STOP";
    private static final String CONTINUE = "CONTINUE";
    private static final String EXIT = "EXIT";

    @Test
    public void whenSendingContinueThenAnsweringToSendingMsgWhileExit() throws URISyntaxException, IOException {
        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        String readFileName = setting.getValue("pathSource.txt");
        String writeFileName = setting.getValue("outPutPathSourceFile.txt");
        String write = System.getProperty("java.io.tmpdir")  + File.separator + writeFileName;
        String s = System.getProperty("line.separator");
        System.out.println(write);
        URL resourceToRead = classLoader.getResource(readFileName);

        ByteArrayOutputStream outToConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outToConsole));

        String consoleInput = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                "HelloFromWhenSendingContinueThenAnsweringToSendingMsgWhileExit", s,
                "GoodFromWhenSendingContinueThenAnsweringToSendingMsgWhileExit", s,
                "STOP", s,
                "BlaBla", s,
                "BlaBla", s,
                CONTINUE, s,
                "AllTheBestFromWhenSendingContinueThenAnsweringToSendingMsgWhileExit", s,
                "ByeFromWhenSendingContinueThenAnsweringToSendingMsgWhileExit", s,
                EXIT);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(arrayInputStream);

        assert resourceToRead != null;
        ConsoleChat consoleChat = new ConsoleChat(Paths.get(resourceToRead.toURI()).toFile(), write);
        consoleChat.readUserData();


        int count = 0;
        boolean isTwoLines = false;
        String out = outToConsole.toString();


        String replace = out.replaceAll(s, "S");
        char[] arrayChar = replace.toCharArray();

        for (char anArrayChar : arrayChar) {
            if (anArrayChar == 'S') {
                count++;
                if (count == 4) {
                    isTwoLines = true;
                }
            }
        }

        assertThat(isTwoLines, is(true));
    }

    /**
     * testing files size.
     */

    @Test
    public void whenGetSettingPropertiesThenReturnNameOfFiles() throws IOException, URISyntaxException,
            InterruptedException {

        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        String readFileName = setting.getValue("pathSource.txt");
        String writeFileName = setting.getValue("outPutPathSourceFile.txt");
        String write = System.getProperty("java.io.tmpdir")  + File.separator + writeFileName;
        String s = System.getProperty("line.separator");

        URL resourceToRead = classLoader.getResource(readFileName);

        String consoleInput = String.format("%s%s%s%s%s", "HelloFromWhenGetSettingPropertiesThenReturnNameOfFiles",
                s, "GoodFromWhenGetSettingPropertiesThenReturnNameOfFiles", s,
                "EXIT");

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(arrayInputStream);

        assert resourceToRead != null;
        ConsoleChat consoleChat = new ConsoleChat(Paths.get(resourceToRead.toURI()).toFile(),
                write);
        consoleChat.readUserData();

        assertThat(readFileName , is("pathSource.txt"));
        assertThat(writeFileName , is("outPutPathSourceFile.txt"));

    }

    @Test
    public void whenSendingStopThenStopAnsweringToSendingMsgWhileExit() throws IOException, URISyntaxException, InterruptedException {

        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        String readFileName = setting.getValue("pathSource.txt");
        String writeFileName = setting.getValue("outPutPathSourceFile.txt");
        String write = System.getProperty("java.io.tmpdir")  + File.separator + writeFileName;
        String s = System.getProperty("line.separator");

        URL resourceToRead = classLoader.getResource(readFileName);

        ByteArrayOutputStream outToConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outToConsole));

        String consoleInput = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s", "HelloFromWhenSendingStopThenStopAnswering" +
                "ToSendingMsgWhileExit", s, "Good", s, STOP, s, "BlaBla", s, "BlaBla", s, "BlaBla", s,  EXIT);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(arrayInputStream);

        assert resourceToRead != null;
        ConsoleChat consoleChat = new ConsoleChat(Paths.get(resourceToRead.toURI()).toFile(),
                write);
        consoleChat.readUserData();

        int count = 0;
        boolean isTwoLines = false;
        String out = outToConsole.toString();


            String replace = out.replaceAll(s, "S");
            char[] arrayChar = replace.toCharArray();

        for (char anArrayChar : arrayChar) {
            if (anArrayChar == 'S') {
                count++;
                if (count == 2) {
                    isTwoLines = true;
                }
            }
        }

        assertThat(isTwoLines, is(true));
    }

    @Test
    public void whenSendingExitThenExit() throws IOException, URISyntaxException, InterruptedException {

        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        String readFileName = setting.getValue("pathSource.txt");
        String writeFileName = setting.getValue("outPutPathSourceFile.txt");
        String write = System.getProperty("java.io.tmpdir")  + File.separator + writeFileName;
        String s = System.getProperty("line.separator");

        URL resourceToRead = classLoader.getResource(readFileName);

        ByteArrayOutputStream outToConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outToConsole));

        String consoleInput = String.format("%s%s%s", "HelloFromWhenSendingExitThenExit", s,  EXIT);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(arrayInputStream);

        assert resourceToRead != null;
        ConsoleChat consoleChat = new ConsoleChat(Paths.get(resourceToRead.toURI()).toFile(),
                write);
        consoleChat.readUserData();

        int count = 0;
        boolean isTwoLines = false;
        String out = outToConsole.toString();


        String replace = out.replaceAll(s, "S");
        char[] arrayChar = replace.toCharArray();

        for (char anArrayChar : arrayChar) {
            if (anArrayChar == 'S') {
                count++;
                if (count == 1) {
                    isTwoLines = true;
                }
            }
        }

        assertThat(isTwoLines, is(true));
    }
}