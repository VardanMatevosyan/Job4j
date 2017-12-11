package ru.matevosyan.start;

/**
 *StartGame class for stating bomberman game.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 24.11.2017
 */

public class StartGame {

    public StartGame() {
    }

    public static void main(String[] args) {
        new Game("BombermanGame", "Bomberman").startAllThreadsOfGame();
    }
}
