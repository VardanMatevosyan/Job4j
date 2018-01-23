package ru.matevosyan.dom;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * XmlFileParserTest created for testing XmlFileParser class.
 * Created on 08.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class XmlFileParserTest {
    /**
     * Parsing the xml file and get sum from all attributes named field value is fifteen.
     */

    @Test
     public void whenParsingFileThanGetSumFromAllAttributesFieldValueIsFifteen() {
        //assign
        XmlFileParser xmlFileparser = new XmlFileParser();
        int expectedSum = 15;
        //act
        int actualSum = xmlFileparser.getSum();
        //assertion
        assertThat(actualSum, is(expectedSum));
        assertTrue(actualSum == expectedSum);
    }
}