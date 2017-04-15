package ru.matevosyan;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * Created ConsoleChat for chating.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ConsoleChat {

    private Scanner scanner = new Scanner(System.in);
    private List<String> string = new ArrayList<>();
    private File file;
    private String outPutFile;

    private static final String LN = System.getProperty("line.separator");

    private static final String EXIT = "EXIT";
    private static final String SAMESTUFF = "CONTINUE";
    private static final String STOP = "STOP";

    /**
     * Constructor fo console chat.
     * @param fileName file that using to take a word from it and send to user.
     * @param outFileName file use to writing console chat.
     */

    public ConsoleChat(final File fileName, final String outFileName) {

        this.file = fileName;
        this.outPutFile = outFileName;

    }

    /**
     * major method using to read answer from user, after go to a file and read from file a random word to send back.
     * And when user tale it answer from file he also can answer again while he send "Bye" and at the end all dialog
     * write to the file.
     */

    public void manageUserData() {

        String readUser;

        do {

                readUser = scanner.nextLine();
            if (!(EXIT.equals(readUser)) && !(STOP.equals(readUser))) {

                if (SAMESTUFF.equals(readUser)) {
                    readUser = scanner.nextLine();
                }

                readFile(readUser);

            }

            if (STOP.equals(readUser)) {
                boolean exitFromWhile = false;
                while (!(SAMESTUFF.equals(readUser))) {
                    writeFile(this.outPutFile, readUser);
                    if (!(EXIT.equals(readUser))) {
                        readUser = scanner.nextLine();
                        writeFile(this.outPutFile, readUser);
                        if (EXIT.equals(readUser)) {
                            readUser = SAMESTUFF;
                            exitFromWhile = true;
                            writeFile(this.outPutFile, readUser);
                        }
                    }
                }

                if (exitFromWhile) {
                    readUser = EXIT;
                }

            }
        } while (!EXIT.equals(readUser));
        writeFile(this.outPutFile, readUser);
    }

    /**
     * Use to read answer from file and print to user, after send to {@link ConsoleChat#writeFile(String, String...)}.
     * @param readUser using to send user answer and send to {@link ConsoleChat#writeFile(String, String...)}
     */

    public void readFile(String readUser) {
        try (BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {

           while (fin.ready()) {
                this.string.add(fin.readLine());
           }

            String answer = this.string.get((int) (Math.random() * this.string.size()));

            System.out.printf(answer);
            System.out.println();

            writeFile(this.outPutFile, readUser, answer);

        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

    /**
     * Use to write to the file everything in the console chat.
     * @param file use to be writen.
     * @param readAndAnswerUser all stuff to write to the file.
     */

    public void writeFile(String file, String ... readAndAnswerUser) {

        try (FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter pWriter = new PrintWriter(new BufferedWriter(fileWriter), true)) {
            ArrayList<String> writeString = new ArrayList<>();
            Collections.addAll(writeString, readAndAnswerUser);

            int size = writeString.size();
            String string;
            if (size == 2) {
                string = String.format("User: %s%sBot: %s%s%s", writeString.get(0), LN, writeString.get(1), LN, LN);
            } else {
                string = String.format("User: %s%s", writeString.get(0), LN);
            }

            pWriter.write(string);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
