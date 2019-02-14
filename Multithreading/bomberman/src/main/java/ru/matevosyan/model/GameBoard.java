package ru.matevosyan.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * GameBoard class which is represent board for bomberman game.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 24.11.2017
 */

public class GameBoard {
    private final ReentrantLock[][] gameBoard;
    private final int boardSize;

    /**
     * Constructor.
     * @param boardSize board size.
     */
    public GameBoard(final int boardSize) {
        this.gameBoard = new ReentrantLock[boardSize][boardSize];
        this.boardSize = boardSize;
        this.initBorderGame();
    }

    /**
     * initialize game board.
     */
    private void initBorderGame() {
        for (int i = 0; i < this.gameBoard.length; i++) {
            for (int j = 0; j < this.gameBoard.length; j++) {
                this.gameBoard[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Get board size.
     * @return board size.
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * get game board.
     * @return board.
     */
    public ReentrantLock[][] getGameBoard() {
        return gameBoard;
    }


}
