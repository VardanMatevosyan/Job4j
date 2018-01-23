package ru.matevosyan.stax;

import org.junit.Test;
import ru.matevosyan.services.Settings;

/**
 * StAXXmlFileGeneratorFromDBTest using to test StAXXmlFileGeneratorFromDB class.
 * Created on 19.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class StAXXmlFileGeneratorFromDBTest {

    private static final Settings SETTINGS = Settings.getSettingInstance();

    /**
     * Testing {@link StAXXmlFileGeneratorFromDB#translateDatabaseInfoToXmlFileWithStreams}.
     */

    @Test
    public void whenTranslateDatabaseInfoToXmlFileWithStreamsThanCheckTheCreatedXmlFile() {
        String pathToCreation = String.format("%s%s%s", System.getProperty("user.dir"),
                System.getProperty("file.separator"), SETTINGS.getValue("test.createXmlPathFile"));

        StAXXmlFileGeneratorFromDB saxXmlFileGeneratorFromDB = new StAXXmlFileGeneratorFromDB(pathToCreation);
        saxXmlFileGeneratorFromDB.translateDatabaseInfoToXmlFileWithStreams();

    }

    /**
     * Testing {@link StAXXmlFileGeneratorFromDB#translateDatabaseInfoToXmlFileWithEvents}.
     */

    @Test
    public void whenTranslateDatabaseInfoToXmlFileWithEventsThanCheckTheCreatedXmlFile() {
        String pathToCreation = String.format("%s%s%s", System.getProperty("user.dir"),
                System.getProperty("file.separator"), SETTINGS.getValue("test.createXmlPathFileWithEvent"));

        StAXXmlFileGeneratorFromDB saxXmlFileGeneratorFromDB = new StAXXmlFileGeneratorFromDB(pathToCreation);
        saxXmlFileGeneratorFromDB.translateDatabaseInfoToXmlFileWithEvents();

    }



}