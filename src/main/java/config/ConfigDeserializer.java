package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ConfigDeserializer {
    public static AppConfiguration deserialize(String filepath) {
        AppConfiguration config = null;
        try {
            File file = new File(filepath);
            FileInputStream f = new FileInputStream(file);
            ObjectInputStream o = new ObjectInputStream(f);
            config = (AppConfiguration) o.readObject();

            o.close();
            f.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
