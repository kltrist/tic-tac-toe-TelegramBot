package player;


import reader.Reader;




public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name, char value, Reader r) {
        this.name = name;
        this.symbol = value;
        this.reader = r;
    }

    @Override
    public String input() {
        return reader.readLine();
    }
}
