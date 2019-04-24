package save;

import config.AppConfiguration;
import logics.Game;

import java.io.*;


public class GameSaver implements Serializable  {

    private AppConfiguration cfg;

    public GameSaver(AppConfiguration cfg) {
        this.cfg = cfg;
    }

    public void save(Game game) {
        try (FileOutputStream fileOutput = new FileOutputStream(cfg.GAME_STATE_FILEPATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput)) {

            objectOutputStream.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game restore() {
        Game game = null;
        try (FileInputStream fileOutput = new FileInputStream(cfg.GAME_STATE_FILEPATH);
             ObjectInputStream objectOutputStream = new ObjectInputStream(fileOutput)) {

            game = (Game) objectOutputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }

}
