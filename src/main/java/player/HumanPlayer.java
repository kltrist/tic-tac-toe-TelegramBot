package player;


import reader.Reader;


public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name, char value, Reader r) {
        this.name = name;
        this.symbol = value;
        this.reader = r;
    }

    @Override
    public int move() {
        boolean isValid = false;
        int move=0;
        while (!isValid)
            try {
                move = Integer.parseInt(reader.readLine())-1;
                isValid = true;
            } catch (NumberFormatException ignored) {
            }
        return move;
    }
}
