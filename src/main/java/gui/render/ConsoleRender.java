package gui.render;

import board.Board;

import java.util.List;

public class ConsoleRender implements RenderService {
    @Override
    public void renderMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void renderException(Exception e) {
        System.err.println(e.getMessage());
    }

    @Override
    public void renderBoard(Board board) {
        renderMessage(board.toString());

    }

    @Override
    public void renderHelp(String help) {
        System.out.println(help);
    }
}
