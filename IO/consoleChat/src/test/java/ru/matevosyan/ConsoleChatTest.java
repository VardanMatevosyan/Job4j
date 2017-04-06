package ru.matevosyan;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

//    private ClassLoader classLoader = getClass().getClassLoader();
//    private static final String pathSource = "sourceFile.txt";
//    private static final String outPutPathSource = "outPutPathSourceFile.txt";
//    private File sourceFile = new File(classLoader.getResource(pathSource).getFile());
//    private File outPutPathSourceFile = new File(classLoader.getResource(outPutPathSource).getFile());
    private static final String stop = "STOP";
    private static final String cont = "CONTINUE";
    private static final String exit = "EXIT";


    /**
     * testing files size.
     */

    @Test
    public void whenGetSettingPropertiesThenReturnNameOfFiles() throws IOException {

        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        String readFile = setting.getValue("pathSource.txt");
        String writeFile = setting.getValue("outPutPathSource.txt");

        assertThat(readFile , is("pathSource.txt"));
        assertThat(writeFile , is("outPutPathSource.txt"));
    }

    @Test
    public void whenGetThenReturn() throws IOException {

        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        String readFileName = setting.getValue("pathSource.txt");
        String writeFileName = setting.getValue("outPutPathSource.txt");

        File readFile = new File(readFileName);
        File writeFile = new File(writeFileName);

        String s = System.getProperty("line.separator");
        String consoleInput = String.format("%s%s%s", "Hello", s, "EXIT");

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(arrayInputStream);

        ConsoleChat consoleChat = new ConsoleChat(readFile, writeFile);
        consoleChat.readUserData();

        assertThat(readFile , is("pathSource.txt"));
        assertThat(writeFile , is("outPutPathSource.txt"));
    }
}