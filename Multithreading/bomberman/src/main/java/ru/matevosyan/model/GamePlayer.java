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

//    private int startCellX;
//    private int startCellY;


    //    private final CellOfGameBoard boardCell;

    public int getStartCellX() {
        return random.nextInt(board.getBoardSize());
    }

    public int getStartCellY() {
        return random.nextInt(board.getBoardSize());
    }

    public GameBoard getBoard() {
        return board;
    }

    public GamePlayer(final String name, final LogicOfTheGame logic, final GameBoard board) {



        this.name = name;
        this.logic = logic;
//        this.currentCellOfGameBoard = new CellOfGameBoard(this.logic.getStartCellX(),
//                this.logic.getStartCellY(), Directions.NONE);
        this.board = board;
//        this.startCellX = random.nextInt(this.board.getBoardSize());
//        this.startCellY = random.nextInt(this.board.getBoardSize());
        this.currentCellOfGameBoard = new CellOfGameBoard(getStartCellX(), getStartCellY(), Directions.NONE);
//        this.startCellX = this.currentCellOfGameBoard.getX();
//        this.startCellY = this.currentCellOfGameBoard.getY();
//        boardCell = new CellOfGameBoard(logic.getStartCellX(), logic.getStartCellY(), Directions.NONE);

        this.logic.takeCell(this.currentCellOfGameBoard);
    }

    public abstract String getName();

    public LogicOfTheGame getLogic() {
        return logic;
    }

    public CellOfGameBoard getCurrentCellOfGameBoard() {
        return currentCellOfGameBoard;
    }

    public void setCellOfGameBoard(CellOfGameBoard cellOfGameBoard) {
        this.currentCellOfGameBoard = cellOfGameBoard;
    }

//    public void takeCell() {
//        synchronized(this.logic.getStartCell()) {
//            this.board.getGameBoard()[this.getLogic().getStartCellX()][this.getLogic().getStartCellY()].lock();
//        }
//    }

//    public boolean moveTo(CellOfGameBoard cellOfGameBoard) {
//
//        boolean getLockAndGetIn = false;
//        Directions direction = cellOfGameBoard.getDirection();
//        int x = 0;
//        int y = 0;
//        int xChanged = 0;
//        int yChanged = 0;
//        boolean getLock;
//
//        if (direction == Directions.LEFT) {
//            xChanged = x - 1;
//        } else if (direction == Directions.RIGHT) {
//            xChanged = x + 1;
//        } else if (direction == Directions.UP) {
//            yChanged = y - 1;
//        } else if (direction == Directions.DOWN) {
//            yChanged = y + 1;
//        }
//        while (!getLockAndGetIn) {
//        if (!this.getLogic().checkBorderGame(xChanged, yChanged)) {
//            Directions newOppositeDirection = this.logic.randomDirection();
//            CellOfGameBoard newCellOfGameBoard = new CellOfGameBoard(x, y, newOppositeDirection);
//            this.moveTo(newCellOfGameBoard);
//        } else {
//
//            try {
//                if (this.board.getGameBoard()[xChanged][yChanged].isLocked()) {
//                    Directions newOppositeDirection = this.logic.randomDirection();
//                    CellOfGameBoard newCellOfGameBoard = new CellOfGameBoard(x, y, newOppositeDirection);
//                    this.moveTo(newCellOfGameBoard);
//                } else {
//                    synchronized (this.logic.getStartCell()) {
////                    synchronized (this.board.getGameBoard()[x][y]) {
//                        getLock = this.board.getGameBoard()[xChanged][yChanged].tryLock(500, TimeUnit.MICROSECONDS);
//                        System.out.println("Get lock" + xChanged + " " + yChanged);
//                        if (getLock) {
//                            CellOfGameBoard tempCellOfGameBoardForUnlock = this.currentCellOfGameBoard;
//                            x = tempCellOfGameBoardForUnlock.getX();
//                            y = tempCellOfGameBoardForUnlock.getY();
//
//                            this.currentCellOfGameBoard = new CellOfGameBoard(xChanged, yChanged, Directions.NONE);
//                            System.out.println("want unlock " + x + " " + y);
//
//
//                                this.board.getGameBoard()[x][y].unlock();
//
//                            getLockAndGetIn = true;
//                        } else {
//                            Directions newOppositeDirection = this.logic.randomDirection();
//                            CellOfGameBoard newCellOfGameBoard = new CellOfGameBoard(x, y, newOppositeDirection);
//                            this.moveTo(newCellOfGameBoard);
//                        }
////                    }
//                    }
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//        return getLockAndGetIn;
//    }

}
