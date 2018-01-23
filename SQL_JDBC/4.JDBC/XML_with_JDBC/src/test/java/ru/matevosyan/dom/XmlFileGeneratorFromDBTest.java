package ru.matevosyan.dom;

import org.junit.Test;
import ru.matevosyan.services.Settings;
import ru.matevosyan.store.JDBCConsoleApp;

import java.sql.Connection;

/**
 * Test for XmlFileGeneratorFromDB class.
 * Created on 06.01.2018
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class XmlFileGeneratorFromDBTest {

    /**
     * Create the xml file and check real xml file in the user dir.
     */

    @Test
    public void whenGetXMLCreatorThanCheckRealXMLFileOnHDD() {
        //assign
        Settings settings = Settings.getSettingInstance();
        String pathToFileXml = String.format("%s%s%s", System.getProperty("user.dir"),
                System.getProperty("file.separator"), settings.getValue("test.fileNameToXML"));
        JDBCConsoleApp consoleApp = new JDBCConsoleApp();
        Connection connection = consoleApp.getDataBaseConnection();
        XmlFileGeneratorFromDB xmlCreator = new XmlFileGeneratorFromDB(connection);
        //act
        xmlCreator.createXMLFile(pathToFileXml);
        //close connection
        consoleApp.closeConnectToDB();
    }

}