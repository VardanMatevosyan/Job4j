package ru.matevosyan.start;

import java.util.Scanner;

/**
 * Class ConsoleInput implements interface Input.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class ConsoleInput implements Input{

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {

        System.out.print(question);
        return scanner.nextLine();

    }
}
