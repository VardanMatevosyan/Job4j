package ru.matevosyan;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created SortFile for testing sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ConsoleChatTest {


    private static File sourceFile;
    private static File outPutPathSourceFile;

    /**
     * execute all common variable before start testing class methods, that use anywhere in the class.
     * @throws IOException when path to a file is invalid.
     */

    @BeforeClass
    public static void executeSameVariable() throws IOException {

        String pathSource = "C:\\Users\\Admin\\Desktop\\Abbat.txt";
        sourceFile = new File(pathSource);

        String outPutPathSource = "C:\\Users\\Admin\\Desktop\\AbbatCreated.txt";
        outPutPathSourceFile = new File(outPutPathSource);

    }

    /**
     * testing files size.
     */

    @Test
    public void whenWriteThenGet() {
        ConsoleChat consoleChat = new ConsoleChat(sourceFile, outPutPathSourceFile);
        consoleChat.readUserData();
    }

}