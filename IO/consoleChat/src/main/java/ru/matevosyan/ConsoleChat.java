package ru.matevosyan;

import java.io.*;
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
    private String string = "";
    private File file;

    public ConsoleChat(final File file) {
        this.file = file;
    }

    public void readUserData() {

        String readUser;
        String exit = "Закончить";
        String sameStuff = "Продолжить";
        String stop = "Стоп";

        do {

                readUser = scanner.nextLine();
            if (!readUser.equals(exit) && !readUser.equals(stop)) {

                if (readUser.equals(sameStuff)) {
                    readUser = scanner.nextLine();
                }

                readFile();

            }

            if (readUser.equals(stop)) {
                boolean exitFromWhile = false;
                while (!(readUser.equals(sameStuff))) {
                    if (!(readUser.equals(exit))) {
                        readUser = scanner.nextLine();

                        if (readUser.equals(exit)) {
                            readUser = sameStuff;
                            exitFromWhile = true;
                        }
                    }

                }
                if (exitFromWhile) {
                    readUser = exit;
                }

            }
        } while (!readUser.equals(exit));
        writeFile(this.file);
    }

    public void readFile() {
        try (BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file))) {

            byte[] buffer = new byte[fin.available()];
            fin.read(buffer, 0, fin.available());
            this.string = new String(buffer, "windows-1251");

            System.out.printf(string);
            System.out.println();


        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

    public void writeFile(File file) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String pathSource = "C:\\Users\\Admin\\Desktop\\Abbat.txt";
        File sourceFile = new File(pathSource);

        ConsoleChat consoleChat = new ConsoleChat(sourceFile);
        consoleChat.readUserData();

    }
}
