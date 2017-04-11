package ru.matevosyan;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;


/**
 * Created SortFile for testing sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ConsoleChatTest {

//    private ClassLoader classLoader = getClass().getClassLoader();
//    private static final String pathSource = "pathSource.txt";
//    private static final String outPutPathSource = "outPutPathSourceFile.txt";
//    private File sourceFile = new File(classLoader.getResource(pathSource).getFile());
//    private File outPutPathSourceFile = new File(classLoader.getResource(outPutPathSource).getFile());
    private static final String STOP = "STOP";
    private static final String CONTINUE = "CONTINUE";
    private static final String EXIT = "EXIT";

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @AfterClass
    public static void afterAssert() throws URISyntaxException, IOException {
        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        String readFileName = setting.getValue("pathSource.txt");
        String writeFileName = setting.getValue("outPutPathSourceFile.txt");

        String s = System.getProperty("line.separator");

        URL resourceToRead = classLoader.getResource(readFileName);
        URL resourceToWrite = classLoader.getResource(writeFileName);

        String consoleInput = String.format("%s%s%s%s%s", "HelloFromAfterClassAnnotation", s,
                "GoodFromAfterClassAnnotation", s, "EXIT");

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(arrayInputStream);

        assert resourceToRead != null;
        String write = String.valueOf(Paths.get(resourceToRead.toURI()).toString().substring(0,
                Paths.get(resourceToRead.toURI()).toString().lastIndexOf(File.separator)) + File.separator + writeFileName);

        ConsoleChat consoleChat = new ConsoleChat(Paths.get(resourceToRead.toURI()).toFile(), write);
        consoleChat.readUserData();

        assertThat(String.valueOf(resourceToWrite),
                is("file:/C:/project/Vardan-Git-Repository/IO/consoleChat/target/classes/outPutPathSourceFile.txt"));

        assert resourceToWrite != null;
        assertThat(String.valueOf(Paths.get(resourceToWrite.toURI())),
                is("C:\\project\\Vardan-Git-Repository\\IO\\consoleChat\\target\\classes\\outPutPathSourceFile.txt"));

        assertFalse(String.valueOf(Paths.get(resourceToWrite.toURI())).equals("C:\\" +
                "project\\Vardan-Git-Repository\\IO\\consoleChat\\target\\classes\\pathSource.txt"));
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

        String s = System.getProperty("line.separator");

        URL resourceToRead = classLoader.getResource(readFileName);

        String consoleInput = String.format("%s%s%s%s%s", "HelloFromWhenGetSettingPropertiesThenReturnNameOfFiles",
                s, "GoodFromWhenGetSettingPropertiesThenReturnNameOfFiles", s,
                "EXIT");

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(arrayInputStream);

        assert resourceToRead != null;
        String write = String.valueOf(Paths.get(resourceToRead.toURI()).toString().substring(0,
                Paths.get(resourceToRead.toURI()).toString().lastIndexOf("\\")) + "\\" + writeFileName);

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

        String s = System.getProperty("line.separator");

        URL resourceToRead = classLoader.getResource(readFileName);
        URL resourceToWrite = classLoader.getResource(writeFileName);

        ByteArrayOutputStream outToConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outToConsole));

        String consoleInput = String.format("%s%s%s%s%s%s%s%s%s%s%s", "HelloFromWhenSendingStopThenStopAnswering" +
                "ToSendingMsgWhileExit", s, "Good", s, STOP, s, "BlaBla", s, "BlaBla", s,  "EXIT");

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(arrayInputStream);

        assert resourceToRead != null;
        String write = String.valueOf(Paths.get(resourceToRead.toURI()).toString().substring(0,
                Paths.get(resourceToRead.toURI()).toString().lastIndexOf("\\")) + "\\" + writeFileName);

        ConsoleChat consoleChat = new ConsoleChat(Paths.get(resourceToRead.toURI()).toFile(),
                write);
        consoleChat.readUserData();

        int count = 0;
        boolean isTwoLines = false;
        for (int i = 0; i < outToConsole.toString().length(); i++) {

            if (outToConsole.toString().contains(s)) {
                count++;
                if (count == 2) {
                    isTwoLines = true;
                }
            }
        }

        assertThat(outToConsole.toString(), is(true));
    }
}