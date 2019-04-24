package reader;

import bot.TelegramBot;


public class TBotReader implements Reader   {
    TelegramBot bot;

    public TBotReader(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public String readLine() {
        return bot.getMessage();
    }


}
