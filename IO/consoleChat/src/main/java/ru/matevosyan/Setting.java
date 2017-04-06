package ru.matevosyan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Admin on 05.04.2017.
 */
public class Setting {
    private final Properties properties = new Properties();

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

    public void load(InputStream in) {
        try {
            this.properties.load(in);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
