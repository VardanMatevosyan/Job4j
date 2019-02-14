package ru.matevosyan.model;

import ru.matevosyan.constants.Directions;
import ru.matevosyan.control.LogicOfTheGame;

import java.util.Random;

/**
 * Created by Admin on 03.12.2017.
 */
public abstract class GamePlayer implements Runnable {
    private final String name;
    private final LogicOfTheGame logic;
    private CellOfGameBoard currentCellOfGameBoard;
    private final GameBoard board;
    private Random random = new Random();

    /**
     * Get start cell X.
     * @return X value.
     */
    public int getStartCellX() {
        return random.nextInt(board.getBoardSize());
    }

    /**
     * Get start cell Y.
     * @return Y value.
     */
    public int getStartCellY() {
        return random.nextInt(board.getBoardSize());
    }

    /**
     * Get board.
     * @return board.
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * Constructor.
     * @param name player name.
     * @param logic logic.
     * @param board board.
     */
    public GamePlayer(final String name, final LogicOfTheGame logic, final GameBoard board) {
        this.name = name;
        this.logic = logic;
        this.board = board;
        this.currentCellOfGameBoard = new CellOfGameBoard(getStartCellX(), getStartCellY(), Directions.NONE);
    }

    /**
     * get Name.
     * @return name.
     */
    public abstract String getName();

    /**
     * Get game logic.
     * @return game logic.
     */
    public LogicOfTheGame getLogic() {
        return logic;
    }

    /**
     * Get current cell.
     * @return current cell.
     */
    public CellOfGameBoard getCurrentCellOfGameBoard() {
        return currentCellOfGameBoard;
    }

    /**
     * set cell of game board.
     * @param cellOfGameBoard  CellOfGameBoard.
     */
    public void setCellOfGameBoard(CellOfGameBoard cellOfGameBoard) {
        this.currentCellOfGameBoard = cellOfGameBoard;
    }

}
