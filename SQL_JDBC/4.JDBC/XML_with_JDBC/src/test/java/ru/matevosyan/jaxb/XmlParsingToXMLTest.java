package ru.matevosyan.jaxb;

import org.junit.Ignore;
import org.junit.Test;
import ru.matevosyan.services.Settings;

/**
 * xmlParsingToXMLTest using to test XmlParsingToXML class.
 * Created on 19.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class XmlParsingToXMLTest {

    private static final Settings SETTINGS = Settings.getSettingInstance();

    /**
     * convert from database to xml file.
     */

    @Ignore
    @Test
    public void whenCreateXmlFileFromDatabaseThanCheckTheResult() {
        String pathToCreation = String.format("%s%s%s", System.getProperty("user.dir"),
                System.getProperty("file.separator"), SETTINGS.getValue("test.createXmlPathFileJAXB"));

        XmlParsingToXML xmlParsingToXML = new XmlParsingToXML(pathToCreation);
        xmlParsingToXML.convertToXML();
    }
}