package ru.matevosyan.start;

import ru.matevosyan.control.LogicOfTheGame;
import ru.matevosyan.model.Bomberman;
import ru.matevosyan.model.GameBoard;
import ru.matevosyan.model.GamePlayer;

/**
 * Created by Admin on 03.12.2017.
 */
public class Game {
    private final GamePlayer bomberman;
    private final GameBoard board;
    private LogicOfTheGame logicOfTheGame;
    private final String gameName;

    private static final int BOARD_SIZE = 4;

    public Game(String gameName, String personName) {
        this.gameName = gameName;
        this.board = new GameBoard(BOARD_SIZE);
        this.logicOfTheGame = new LogicOfTheGame(this.board);
        this.bomberman = new Bomberman(personName, logicOfTheGame, this.board);
    }

    public void startAllThreadsOfGame() {
        Thread firstPersonThread = new Thread(new Bomberman("B1", logicOfTheGame, this.board));
        Thread secondPersonThread = new Thread(new Bomberman("B2", logicOfTheGame, this.board));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstPersonThread.start();
        secondPersonThread.start();

        System.out.println("Start");

        try {
            firstPersonThread.join();
            secondPersonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
