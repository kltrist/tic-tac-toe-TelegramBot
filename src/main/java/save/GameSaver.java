package save;

import config.AppConfiguration;
import logics.Game;

import java.io.*;

public class GameSaver implements Serializable {

    private AppConfiguration cfg;

    public GameSaver(AppConfiguration cfg) {
        this.cfg = cfg;
    }

    public void saveBotGame(Game game) {
        serialize(game, cfg.BOT_GAME_STATE_FILEPATH);
    }

    public void saveConsoleGame(Game game) {
        serialize(game, cfg.CONSOLE_GAME_STATE_FILEPATH);
    }

    public void restoreBotGame(){
        deserialize(cfg.BOT_GAME_STATE_FILEPATH);
    }

    public void restoreConsoleGame(){
        deserialize(cfg.CONSOLE_GAME_STATE_FILEPATH);
    }

    private void serialize(Game game, String filepath) {
        try (FileOutputStream fileOutput = new FileOutputStream(filepath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput)) {

            objectOutputStream.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Game deserialize(String filepath) {
        Game game = null;
        try (FileInputStream fileOutput = new FileInputStream(filepath);
             ObjectInputStream objectOutputStream = new ObjectInputStream(fileOutput)) {

            game = (Game) objectOutputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }

}
