package ru.matevosyan;

import org.junit.Ignore;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * OrderSAXHandlerTest to test OrderSAXHandler class for parsing XML file.
 * Created on 06.08.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class OrderSAXHandlerTest {
    private static final String PATH = "C:\\orders.xml";

    /**
     * Run the hole process manipulation and check if deleted order is not exist in the final shifted container.
     * @throws IOException throw if problem with contact to file.
     * @throws ParserConfigurationException throw if problem with XML parsing.
     */

    @Ignore
    @Test
    public void whenParseDataThanCheckIfTheyExistDeletedOrderId() throws IOException, ParserConfigurationException {
        OrderBookManipulation orderBookManipulation = new OrderBookManipulation(PATH);

        OrderSAXHandler orderSAXHandler = new OrderSAXHandler();
        Map<Integer, Order> orderSaxMap = orderSAXHandler.getOrderBookMap();
        Integer deletedOrderId = 104;

        orderBookManipulation.manipulation();
        boolean actual = orderSaxMap.containsKey(deletedOrderId);

        assertThat(actual, is(false));
    }
}