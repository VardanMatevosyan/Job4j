package ru.matevosyan.start;

/**
 *StartGame class for stating bomberman game.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 24.11.2017
 */

public class StartGame {

    /**
     * StartGame Constructor.
     */
    public StartGame() {
    }

    /**
     * Main method.
     * @param args app arguments.
     */
    public static void main(String[] args) {
        new Game("BombermanGame", "Bomberman").startAllThreadsOfGame();
    }
}
