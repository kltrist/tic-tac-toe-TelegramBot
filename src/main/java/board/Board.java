package board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Dmitry
 */
public class Board implements Serializable {
    private static final char EMPTY_VALUE = '.';
    public static final int MIN_INDEX = 0;
    public static final int MAX_INDEX = 8;

    private char square[];

    public Board() {
        this.square = new char[9];
        ClearSquare();
    }

    public char getCell(int pos) {
        return square[pos];
    }

    public boolean setCell(char value, int pos) {
        if (!isBoundsWithin(pos)) return false;
        if (square[pos] != EMPTY_VALUE)
            return false;
        else {
            square[pos] = value;
            return true;
        }
    }

    boolean isBoundsWithin(int index) {
        return index <= MAX_INDEX && index >= MIN_INDEX;
    }

    public void ClearSquare() {
        Arrays.fill(square, EMPTY_VALUE);
    }

    public ArrayList<Integer> getAvailableCellIndexes() {
        ArrayList<Integer> indexes = new ArrayList<>(9);
        for (int i = 0; i < square.length; i++)
            if (square[i] == EMPTY_VALUE)
                indexes.add(i);
        return indexes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < square.length; i++) {
            if (i % 3 == 0)
                sb.append("\n");
            sb.append(square[i] + "\t");
        }
        return sb.toString();
    }
}