package ru.matevosyan.stax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * StAXXmlFileParser class parsing xml file using StAX and getting sum from all attributes value named field.
 * Created on 21.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class StAXXmlFileParser {
    private final String inputXml;
    private static final Logger LOG = LoggerFactory.getLogger(StAXXmlFileParser.class.getName());
    private static final String ATTR_FIELD = "field";

    /**
     * Constructor for StAXXmlFileParser.
     * @param pathToTheFile path to the xml file.
     */

    public StAXXmlFileParser(String pathToTheFile) {
        this.inputXml = pathToTheFile;
    }

    /**
     * Get arithmetical sum parsing field value from xml file.
     * @return sum of all field values.
     */

    public int getSum() {
        int sum = 0;
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        try {
            XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(new InputStreamReader(
                    new FileInputStream(new File(this.inputXml))));

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    QName qName = QName.valueOf(ATTR_FIELD);

                    if (startElement.getAttributes().hasNext()) {
                        Attribute attributeByName = startElement.getAttributeByName(qName);
                        if (attributeByName != null) {
                            sum += Integer.parseInt(attributeByName.getValue());
                        }
                    }

                }

            }
            LOG.debug("Get sum {}", sum);
        } catch (XMLStreamException xmlStreamEx) {
            LOG.warn("Problem with getting event reader", xmlStreamEx);
        } catch (FileNotFoundException fnfEx) {
            LOG.warn("File was not found", fnfEx);
        }

        return sum;
    }

}
