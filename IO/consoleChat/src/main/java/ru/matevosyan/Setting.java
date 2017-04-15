package ru.matevosyan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created to get value from property file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Setting {
    private final Properties properties = new Properties();

    /**
     * Return value from key.
     * @param key passing key to get value. Key must be the same.
     * @return key from property file.
     */

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

    /**
     * use to load input stream.
     * @param in inputStream
     */

    public void load(InputStream in) {
        try {
            this.properties.load(in);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
