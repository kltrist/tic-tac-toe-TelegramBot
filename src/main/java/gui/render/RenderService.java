package gui.render;

import board.Board;

import java.io.Serializable;
import java.util.List;

public interface RenderService  extends Serializable{

     void renderMessage(String message);

    void renderException(Exception e);

    void renderBoard(Board board);

    void renderHelp(String help);

}
