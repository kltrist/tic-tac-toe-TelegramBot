package bot;

import config.AppConfiguration;
import db.DataBaseHelper;
import gui.render.TBotRender;
import logics.GameManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reader.TBotReader;
import save.GameSaver;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class TickTackToeBot extends TelegramLongPollingBot implements TelegramBot, Serializable {

    private Long chatId;
    private String message;
    private DataBaseHelper dbHelper;
    private AppConfiguration cfg;
    private GameSaver gameSaver;

    public TickTackToeBot(DataBaseHelper dbHelper, AppConfiguration cfg, GameSaver gameSaver) {
        this.dbHelper = dbHelper;
        this.cfg = cfg;
        this.gameSaver = gameSaver;
    }

    public void sendMsg(String text) {
        try {
            execute(new SendMessage(chatId, text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            chatId = update.getMessage().getChatId();
            GameManager telegramGameManager = new GameManager(new TBotRender(this), dbHelper, new TBotReader(this), cfg, gameSaver);
            new Thread(() -> {
                try {
                    telegramGameManager.startGame();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).start();

        } else if (update.hasMessage())
            message = update.getMessage().getText();
    }

    @Override
    public String getBotUsername() {
        return "Goncharuk_bot";
    }

    @Override
    public String getBotToken() {
        return "717548745:AAF-PQrqKNW8JVLRWSLxIquW7HGBL2RWNB8";
    }

    public String getMessage() {
        while (message == null) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String msg = message;
        message = null;
        return msg;

    }


}
