package ru.matevosyan.dom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.matevosyan.services.Settings;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * XmlFileParser class parsing xml file and getting sum from all attributes value named field.
 * Created on 08.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class XmlFileParser {
    private Document document;
    private Path pathToXmlFile;
    private static final Logger LOG = LoggerFactory.getLogger(XmlFileParser.class.getName());

    /**
     * Constructor for XmlFileParser class.
     * Format inputXml path.
     * Get new document to to parsing xml file.
     */

    public XmlFileParser() {
        Settings settings = Settings.getSettingInstance();
        String inputXml = String.format("%s%s%s", System.getProperty("user.dir"), System.getProperty("file.separator"),
                settings.getValue("test.xmlFileOutput"));
        this.pathToXmlFile = Paths.get(inputXml);
        this.document = this.getNewDocumentForParsing();
    }

    /**
     * Get new DOM document for parsing it.
     * @return document.
     */

    public Document getNewDocumentForParsing() {
        File xmlFile = this.pathToXmlFile.toFile();
        DocumentBuilderFactory factoryDocBuilder = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = factoryDocBuilder.newDocumentBuilder();
            this.document = docBuilder.parse(xmlFile);
        } catch (ParserConfigurationException pce) {
            LOG.warn("Problem with parser configuration", pce);
        } catch (SAXException saxe) {
            LOG.warn("Problem with parsing xml", saxe);
        } catch (IOException ioe) {
            LOG.warn("Problem with input output during parsing xml", ioe);
        }
        return this.document;
    }

    /**
     * Solve sum patsing field value from xml file.
     * @return sum of all field values.
     */

    public int getSum() {
        this.document.getDocumentElement().normalize();
        NodeList list = this.document.getElementsByTagName("entry");
        int sumOfAllTags = 0;
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap attr = node.getAttributes();
                for (int j = 0; j < attr.getLength(); j++) {
                    Node nodeAttr = attr.item(j);
                    String value = nodeAttr.getFirstChild().getNodeValue();
                    sumOfAllTags += Integer.valueOf(value);
                }

            }
        }
        return sumOfAllTags;
    }

}
