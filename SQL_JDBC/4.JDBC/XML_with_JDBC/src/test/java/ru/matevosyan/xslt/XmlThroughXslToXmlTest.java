package ru.matevosyan.xslt;

import org.junit.Test;
import ru.matevosyan.services.Settings;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Testing XmlThroughXslToXml class which is transform first xml file to second xml file.
 * Created on 07.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */
public class XmlThroughXslToXmlTest {
    /**
     * transform xml file using xsl file as template and crate this xml file into user dir package.
     * @throws URISyntaxException throw when URI syntax is wrong.
     */

    @Test
    public void whenTransformXmlFileThanCheckNewCreatedXmlFile() throws URISyntaxException {
        //assign
        Settings settings = Settings.getSettingInstance();
        String name = settings.getValue("test.xslRelPath");
        Path path = Paths.get(ClassLoader.getSystemResource(name).toURI());
        String pathToXmlFile = String.format("%s%s%s", System.getProperty("user.dir"),
                System.getProperty("file.separator"), settings.getValue("test.createXmlPathFileJAXB"));
        String outputXml = String.format("%s%s%s", System.getProperty("user.dir"),
                System.getProperty("file.separator"), settings.getValue("test.xmlFileOutput"));
        //act
        XmlThroughXslToXml xmlThroughXslToXml = new XmlThroughXslToXml(path.normalize().toString(), pathToXmlFile, outputXml);
        xmlThroughXslToXml.transformation();
    }

}