package config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ConfigSerializer {

    public static void serialize(AppConfiguration cfg,String filepath) {
        try {
            FileOutputStream f = new FileOutputStream(filepath, false);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(cfg);
            o.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
