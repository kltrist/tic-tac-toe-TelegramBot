package bot;

import config.AppConfiguration;
import db.DataBaseHelper;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import save.GameSaver;


public class Initializr {

    public static TelegramBot getRegisteredBot(DataBaseHelper dbHelper, AppConfiguration cfg,GameSaver gameSaver) {
        TelegramBot bot = null;

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(bot = new TickTackToeBot(dbHelper,cfg, gameSaver));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return bot;
    }
}
