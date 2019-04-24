package gui.render;

import board.Board;
import bot.TelegramBot;

import java.util.List;

public class TBotRender implements RenderService {

    TelegramBot bot;

    public TBotRender(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void renderMessage(String message) {
        bot.sendMsg(message);
    }

    @Override
    public void renderException(Exception e) {
        bot.sendMsg(e.getMessage());
    }

    @Override
    public void renderBoard(Board board) {
        bot.sendMsg(board.toString());
    }

    @Override
    public void renderHelp(String help) {
        bot.sendMsg(help);
    }
}
