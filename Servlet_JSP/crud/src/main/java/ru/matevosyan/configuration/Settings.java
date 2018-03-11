package ru.matevosyan.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created to get value from property file.
 * Created on 27.02.2018.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Settings {
    private static final Settings SETTING_INSTANCE = new Settings();
    private final Properties properties = new Properties();
    private static final Logger LOG = LoggerFactory.getLogger(Settings.class.getName());

    /**
     * Constructor load all settings from property file databaseConfig.properties to fileInputStream and passing.
     * to load method, which call from properties object and load all from config file that we ca after use it.
     * to get our own setting define in the file properties and use another methods.
     * use to load input stream.
     */
    private Settings() {
        URL urlToConfigFile = this.getClass().getClassLoader().getResource("databaseConfig.properties");
        String fileProperties = "";

        try {
            if (urlToConfigFile != null) {
                fileProperties = urlToConfigFile.getFile();
            }
            this.properties.load(new FileInputStream(fileProperties));
        } catch (IOException e) {
            LOG.warn("Error wasn't loaded with properties like {}", this.properties, e);
        }
    }

    /**
     * Getter SETTING_INSTANCE ones built object for hole project.
     * @return SETTING_INSTANCE.
     */
    public static Settings getSettingInstance() {
        return SETTING_INSTANCE;
    }

    /**
     * Return value from key.
     * @param key passing key to get value. Key must be the same.
     * @return key from property file.
     */
    public String getValue(String key) {
        return this.properties.getProperty(key);
    }
}
