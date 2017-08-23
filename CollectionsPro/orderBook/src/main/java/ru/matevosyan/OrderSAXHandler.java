package ru.matevosyan;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * OrderSAXHandler class for parsing XML file.
 * Created on 06.08.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class OrderSAXHandler extends DefaultHandler {

    private Map<Integer, Order> orderBookMap;

    /**
     * OrderSAXHandler constructor.
     */

    public OrderSAXHandler() {
        this.orderBookMap = new HashMap<>();
    }

    /**
     * Override {@link DefaultHandler#startElement(String, String, String, Attributes)} to starting parsing XML file.
     * @param nameSpaceURI nameSpaceURI.
     * @param localName localName.
     * @param qName is name of the attributes.
     * @param attributes is attributes in the XML file.
     * @throws SAXException throw if problem with parsing.
     */

    @Override
    public void startElement(String nameSpaceURI, String localName, String qName, Attributes attributes) throws SAXException {
        Double price = 0.0;
        Integer volume = 0;
        String bookName = "";
        String operation = "";
        Integer orderId = 0;

        if (qName.equals("AddOrder")) {
            int j = 0;
            while (j < attributes.getLength()) {
                if (attributes.getQName(j).equals("book")) {
                    bookName =  attributes.getValue(j++);
                }
                if (attributes.getQName(j).equals("operation")) {
                    operation =  attributes.getValue(j++);
                }
                if (attributes.getQName(j).equals("price")) {
                 price = Double.parseDouble(attributes.getValue(j++));
                }
                if (attributes.getQName(j).equals("volume")) {
                    volume = Integer.parseInt(attributes.getValue(j++));
                }
                if (attributes.getQName(j).equals("orderId")) {
                    orderId =  Integer.parseInt(attributes.getValue(j++));
                }

                orderBookMap.put(orderId, new Order(bookName, operation, volume, price, orderId));
            }
        } else if (qName.equals("DeleteOrder")) {
            int orderIdForRemoving = 0;
            for (int j = 0; j < attributes.getLength(); j++) {
                if (attributes.getQName(j).equals("orderId")) {
                    orderIdForRemoving = Integer.parseInt(attributes.getValue(j++));
                }
            }
            this.orderBookMap.remove(orderIdForRemoving);
        }

    }

    /**
     * Getter for order book holder.
     * @return order book map.
     */

    public Map<Integer, Order> getOrderBookMap() {
        return orderBookMap;
    }

}
