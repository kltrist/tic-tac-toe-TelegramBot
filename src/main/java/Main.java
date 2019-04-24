
import bot.Initializr;
import bot.*;
import config.AppConfiguration;
import config.ConfigSerializer;
import db.DataBaseHelper;
import gui.render.ConsoleRender;
import gui.render.TBotRender;
import logics.GameManager;
import reader.ConsoleReader;
import reader.TBotReader;
import save.GameSaver;

public class Main {
    public static void main(String[] args) throws Exception {
 
        ConfigSerializer.serialize(new AppConfiguration(), "C:\\Users\\overd\\Desktop\\tictactoe - demo\\src\\main\\resources\\AppConfiguration.json");
        AppConfiguration cfg = AppConfiguration.getConfiguration("C:\\Users\\overd\\Desktop\\tictactoe - demo\\src\\main\\resources\\AppConfiguration.json");
        DataBaseHelper dbHelper = new DataBaseHelper(cfg);
        GameSaver gameSaver = new GameSaver(cfg);
         TelegramBot bot = Initializr.getRegisteredBot(dbHelper, cfg, gameSaver);

        GameManager consoleManager = new GameManager(new ConsoleRender(), dbHelper, new ConsoleReader(), cfg, gameSaver);
        consoleManager.startGame();

    }


}
