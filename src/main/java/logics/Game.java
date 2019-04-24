package logics;

import gui.cmd.Command;
import gui.render.RenderService;
import board.Board;
import player.AbstractPlayer;
import save.GameSaver;

import java.io.Serializable;


public class Game implements Serializable {

    private AbstractPlayer firstPl;
    private AbstractPlayer secondPl;
    private RenderService rn;
    private Board board;
    private GameSaver gameSaver;
    private boolean isInterrupted;

    Game(AbstractPlayer firstPl, AbstractPlayer secondPl, RenderService rn, Board board, GameSaver gameSaver) {
        if (firstPl.getSymbol() == secondPl.getSymbol())
            throw new IllegalArgumentException("You cannot set the same symbols!");
        this.firstPl = firstPl;
        this.secondPl = secondPl;
        this.board = board;
        this.rn = rn;
        this.gameSaver = gameSaver;
    }

    private boolean makeMove(String input, char symbol) {
        boolean isMove = true;
        if (input.equals(Command.SAVE)) {
            isInterrupted = true;
            gameSaver.save(this);
        } else if (input.equals(Command.EXIT)) {
            isInterrupted = true;
        } else {
            try {
                int moveIndex = Integer.parseInt(input) - 1;
                isMove = board.setCell(symbol, moveIndex);
            } catch (NumberFormatException e) {
                rn.renderMessage("Incorrect input!");
                isMove = false;
            }
        }
        return isMove;
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
            while (!makeMove(plr.input(), plr.getSymbol())) ;
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


