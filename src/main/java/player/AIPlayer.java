package player;

import board.Board;

public class AIPlayer extends AbstractPlayer {

    public AIPlayer(String name, char value) {
        this.name = name;
        this.symbol = value;
    }

    @Override
    public String input() {
        int max = Board.MAX_INDEX;
        int min = Board.MIN_INDEX;

        max -= min;
        int moveIndex = ((int) (Math.random() * ++max) + min);

        return String.valueOf(moveIndex);
    }

}
