package player;

import board.Board;

public class AIPlayer extends AbstractPlayer {

    public AIPlayer(String name, char value) {
        this.name = name;
        this.symbol = value;
    }

    @Override
    public int move() {
        int max = Board.MAX_INDEX;
        int min = Board.MIN_INDEX;
        max -= min;

       return  ((int) (Math.random() * ++max) + min);


    }

}
