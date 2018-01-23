package ru.matevosyan.jaxb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.entity.FromXml.Entries;
import ru.matevosyan.entity.FromXml.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

/**
 * ParserFromXml class parsing xml file and getting sum from all attributes value named field.
 * Created on 22.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */
public class ParserFromXml {
    private static final Logger LOG = LoggerFactory.getLogger(ParserFromXml.class.getName());
    private final String pathToTheXmlFile;

    /**
     * ParserFromXml constructor.
     * @param pathToTheXmlFile pth to the xml file.
     */

    public ParserFromXml(String pathToTheXmlFile) {
        this.pathToTheXmlFile = pathToTheXmlFile;
    }

    /**
     * Get arithmetical sum parsing field value from xml file using JAXB.
     * @return sum of all field values.
     */

    public int getSum() {
        int sum = 0;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Object unmarshalClass = unmarshaller.unmarshal(new File(this.pathToTheXmlFile));

            Entries entries = null;
            if (unmarshalClass.getClass().getName().equals(Entries.class.getName())) {
                entries = (Entries) unmarshalClass;
            }

            assert entries != null;
            ArrayList<Entry> entryList = entries.getEntry();
            for (Entry entry : entryList) {
                sum += Integer.parseInt(entry.getField());
            }

        } catch (JAXBException jaxbEx) {
            LOG.warn("Problem with getting new jaxb instance", jaxbEx);
        }


        return sum;
    }
}
