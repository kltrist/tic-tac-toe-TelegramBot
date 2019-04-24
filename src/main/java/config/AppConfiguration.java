package config;

import java.io.*;


public class AppConfiguration implements Serializable {
    public String URL = "jdbc:mysql://localhost:3306/tic_tac_toe?serverTimezone=UTC";
    public transient static String PASS = "31704058k";
    public transient static String USER = "root";
    public char FIRST_SYMBOL = 'x';
    public char SECOND_SYMBOL = 'o';
    public String GAME_STATE_FILEPATH = "C:\\Users\\overd\\Desktop\\tictactoe - demo\\src\\main\\resources\\gameState.json";

    public static AppConfiguration getConfiguration(String filepath) {
        File cfgFile = new File(filepath);
        AppConfiguration cfg;
        if (cfgFile.exists() && !cfgFile.isDirectory())
            cfg = ConfigDeserializer.deserialize(cfgFile.getAbsolutePath());
        else
            cfg = new AppConfiguration();
        return cfg;
    }
}
