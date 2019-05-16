package player;

import lombok.Data;
import reader.Reader;
import java.io.Serializable;


@Data
public abstract class AbstractPlayer implements Serializable {

    int id;
    String name;
    char symbol;
    Reader reader;
    public abstract int move();


}