package ru.matevosyan;

import java.io.*;
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
    private File outPutFile;

    private String LN = System.getProperty("line.separator");

    public ConsoleChat(final File file, final File outFile) {
        this.file = file;
        this.outPutFile = outFile;
    }

    public void readUserData() {

        String exit = "EXIT";
        String sameStuff = "CONTINUE";
        String stop = "STOP";

        String readUser;

        do {

                readUser = scanner.nextLine();
            if (!(readUser.equals(exit)) && !(readUser.equals(stop))) {

                if (readUser.equals(sameStuff)) {
                    readUser = scanner.nextLine();
                }

                readFile(readUser);

            }

            if (readUser.equals(stop)) {
                boolean exitFromWhile = false;
                while (!(readUser.equals(sameStuff))) {
                    writeFile(this.outPutFile, readUser);
                    if (!(readUser.equals(exit))) {
                        readUser = scanner.nextLine();
                        writeFile(this.outPutFile, readUser);
                        if (readUser.equals(exit)) {
                            readUser = sameStuff;
                            exitFromWhile = true;
                            writeFile(this.outPutFile, readUser);
                        }
                    }

                }

                if (exitFromWhile) {
                    readUser = exit;
                }

            }
        } while (!readUser.equals(exit));
        writeFile(this.outPutFile, readUser);
    }

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

    public void writeFile(File file, String ... readAndAnswerUser) {

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

//    public static void main(String[] args) {
//        String pathSource = "C:\\Users\\Admin\\Desktop\\Abbat.txt";
//        String outPutPathSource = "C:\\Users\\Admin\\Desktop\\AbbatCreated.txt";
//
//        File sourceFile = new File(pathSource);
//        File outPutPathSourceFile = new File(outPutPathSource);
//
//        ConsoleChat consoleChat = new ConsoleChat(sourceFile, outPutPathSourceFile);
//        consoleChat.readUserData();
//
//
//    }
}
