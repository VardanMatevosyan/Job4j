package ru.matevosyan.xml_work;

import org.junit.Test;
import ru.matevosyan.services.Settings;

/**
 * Testing XmlThroughXslToXml class which is transform first xml file to second xml file.
 * Created on 07.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */
public class XmlThroughXslToXmlTest {
    /**
     * transform xml file using xsl file as template and crate this xml file into user dir package.
     */

    @Test
    public void whenTransformXmlFileThanCheckNewCreatedXmlFile() {
        //assign
        Settings settings = Settings.getSettingInstance();
        String xslPath = settings.getValue("app.xslPath");
        String pathToXmlFile = String.format("%s%s%s", System.getProperty("user.dir")
                , System.getProperty("file.separator"), settings.getValue("app.fileNameToXML"));
        String outputXml = String.format("%s%s%s", System.getProperty("user.dir")
                , System.getProperty("file.separator"), settings.getValue("app.xmlFileOutput"));
        //act
        XmlThroughXslToXml xmlThroughXslToXml = new XmlThroughXslToXml(xslPath, pathToXmlFile, outputXml);
        xmlThroughXslToXml.transformation();
    }

}