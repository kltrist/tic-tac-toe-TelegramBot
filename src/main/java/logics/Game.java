package logics;

import gui.render.RenderService;
import board.Board;
import player.AbstractPlayer;

import java.io.IOException;
import java.io.Serializable;


public class Game implements Serializable {

    private AbstractPlayer firstPl;
    private AbstractPlayer secondPl;
    private RenderService rn;
    private Board board;
    private boolean isInterrupted;

    Game(AbstractPlayer firstPl, AbstractPlayer secondPl, RenderService rn, Board board) {
        if (firstPl.getSymbol() == secondPl.getSymbol())
            throw new IllegalArgumentException("You cannot set the same symbols!");
        this.firstPl = firstPl;
        this.secondPl = secondPl;
        this.board = board;
        this.rn = rn;
    }

    private boolean makeMove(int moveIndex, char symbol) {
        return board.setCell(symbol, moveIndex);
    }

    private boolean isAvailable() {
        return !board.getAvailableCellIndexes().isEmpty();

    }

    private boolean isWin(AbstractPlayer player) {
        char value = player.getSymbol();
        return ((board.getCell(0) == board.getCell(1) && board.getCell(1) == board.getCell(2) && board.getCell(2) == value) ||
                (board.getCell(3) == board.getCell(4) && board.getCell(4) == board.getCell(5) && board.getCell(5) == value) ||
                (board.getCell(6) == board.getCell(7) && board.getCell(7) == board.getCell(8) && board.getCell(8) == value) ||
                (board.getCell(0) == board.getCell(4) && board.getCell(4) == board.getCell(8) && board.getCell(8) == value) ||
                (board.getCell(2) == board.getCell(4) && board.getCell(4) == board.getCell(6) && board.getCell(6) == value) ||
                (board.getCell(0) == board.getCell(3) && board.getCell(3) == board.getCell(6) && board.getCell(6) == value) ||
                (board.getCell(1) == board.getCell(4) && board.getCell(4) == board.getCell(7) && board.getCell(7) == value) ||
                (board.getCell(2) == board.getCell(5) && board.getCell(5) == board.getCell(8) && board.getCell(8) == value));


    }

    AbstractPlayer play(boolean is1stPlayerMove) {
        AbstractPlayer winner = null;
        isInterrupted = false;
        int moveSwitcher = is1stPlayerMove ? 0 : 1;

        while (isAvailable() && !isInterrupted) {
            rn.renderBoard(board);
            AbstractPlayer plr = moveSwitcher == 0 ? firstPl : secondPl;
            makeMove(plr.move(), plr.getSymbol());
            if (isWin(plr)) {
                winner = plr;
                break;
            } else if (!isAvailable()) {
                break;
            }
            moveSwitcher = (moveSwitcher == 0) ? 1 : 0;
        }
        rn.renderBoard(board);
        return winner;
    }


}


