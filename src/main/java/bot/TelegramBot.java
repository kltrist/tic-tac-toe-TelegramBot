package bot;

import org.telegram.telegrambots.meta.generics.LongPollingBot;

public interface TelegramBot extends LongPollingBot {
    void sendMsg(String text);
    String getMessage();
}
