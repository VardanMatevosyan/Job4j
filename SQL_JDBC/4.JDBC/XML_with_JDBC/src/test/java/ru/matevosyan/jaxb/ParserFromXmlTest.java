package ru.matevosyan.jaxb;

import org.junit.Test;
import ru.matevosyan.services.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * ParserFromXmlTest created for testing ParserFromXml class.
 * Created on 22.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class ParserFromXmlTest {
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final String PATH_TO_XML_FILE = String.format("%s%s%s", System.getProperty("user.dir"),
            System.getProperty("file.separator"), SETTINGS.getValue("test.xmlFileOutput"));

    /**
     * Parsing the xml file using JAXB and get sum from all attributes named field value is fifteen.
     */

    @Test
    public void whenThan() {
        int expectedSum = 15;
        ParserFromXml parserFromXml = new ParserFromXml(PATH_TO_XML_FILE);

        int sum = parserFromXml.getSum();

        System.out.printf("Sum is %s", sum);
        assertThat(sum, is(expectedSum));
    }

}