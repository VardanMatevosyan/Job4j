package ru.matevosyan.stax;

import org.junit.Test;
import ru.matevosyan.services.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * StAXXmlFileParserTest created for testing StAXXmlFileParser class.
 * Created on 21.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class StAXXmlFileParserTest {

    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final String PATH_TO_XML_FILE = String.format("%s%s%s", System.getProperty("user.dir"),
            System.getProperty("file.separator"), SETTINGS.getValue("test.xmlFileOutput"));

    /**
     * Parsing the xml file like StAX and get sum from all attributes named field value is fifteen.
     */

    @Test
    public void whenThan() {
        //assign
        StAXXmlFileParser xmlFileParser = new StAXXmlFileParser(PATH_TO_XML_FILE);
        int expectedSum = 15;
        //act
        int actualSum = xmlFileParser.getSum();

        System.out.printf("Sum is %s", actualSum);
        //assertion
        assertThat(actualSum, is(expectedSum));
        assertTrue(actualSum == expectedSum);
    }

}