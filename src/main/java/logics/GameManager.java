package logics;

import board.Board;
import config.AppConfiguration;
import db.DataBaseHelper;
import gui.cmd.Command;
import gui.render.RenderService;
import player.AIPlayer;
import player.AbstractPlayer;
import player.HumanPlayer;
import player.PlayerType;
import reader.Reader;
import save.GameSaver;

import java.sql.SQLException;

public class GameManager {

    private RenderService rnd;
    private DataBaseHelper dbHelper;
    private Reader rdr;
    private AppConfiguration cfg;


    public GameManager(RenderService rnd, DataBaseHelper dbHelper, Reader rdr, AppConfiguration cfg, GameSaver gameSaver) {
        this.rnd = rnd;
        this.dbHelper = dbHelper;
        this.rdr = rdr;
        this.cfg = cfg;
    }

    public void startGame() throws SQLException {
        boolean isWork = true;
        while (isWork) {
            rnd.renderHelp(Command.getHelp());
            switch (rdr.readLine()) {
                case Command.START:
                    playing(getPlayer(cfg.FIRST_SYMBOL, PlayerType.HUMAN),
                            getPlayer(cfg.SECOND_SYMBOL, PlayerType.HUMAN));
                    break;
                case Command.START_C:
                    playing(getPlayer(cfg.FIRST_SYMBOL, PlayerType.HUMAN),
                            getPlayer(cfg.SECOND_SYMBOL, PlayerType.AI));
                    break;
                case Command.EXIT:
                    isWork = false;
                    rnd.renderMessage("Bye!");
                    break;
                case Command.SAVE:
                    rnd.renderMessage("You can save the game state only during playing!");
                    break;
                case Command.RESTORE:
                    break;
                default:
                    rnd.renderMessage("Incorrect move!");
            }
        }
    }

    private Game playing(AbstractPlayer firstPlr, AbstractPlayer secondPlr) throws SQLException {
        Board board = new Board();
        Game game = new Game(firstPlr, secondPlr, rnd, board);
        AbstractPlayer winner = game.play(true);

        addToDb(winner);
        printWinner(winner);

        return game;
    }

    private void printWinner(AbstractPlayer winner) {
        if (winner != null)
            rnd.renderMessage(winner.getName() + " has won!\n");
        else rnd.renderMessage("Nobody has won!\n");
    }

    private AbstractPlayer getPlayer(char symbol, PlayerType type) {
        if (type.equals(PlayerType.AI))
            return new AIPlayer("Computer Player", symbol);
        else
            return new HumanPlayer(inputPlayerName(), symbol, rdr);
    }

    private String inputPlayerName() {
        rnd.renderMessage("Name: ");
        return rdr.readLine();
    }

    private void addToDb(AbstractPlayer winner) throws SQLException {
        if (winner != null) {
            String winnerName = winner.getName();
            if (dbHelper.isPlayerExists(winnerName))
                dbHelper.updatePlayer(winnerName,
                        dbHelper.selectPlayerWinCount(winnerName) + 1);
            else dbHelper.insertPlayer(winnerName, 1);
        }
    }


}
