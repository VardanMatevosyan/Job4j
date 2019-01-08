package ru.matevosyan;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;

/**
 * OrderBookManipulation class for manipulate with data from  XML file.
 * Created on 06.08.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class OrderBookManipulation {
    private final String path;
    private Map<Integer, Order> bid = new HashMap<>();
    private Map<Integer, Order> ask = new HashMap<>();
    private OrderSAXHandler handler = new OrderSAXHandler();

    /**
     * OrderBookManipulation constructor.
     * @param path to the XML file.
     */

    public OrderBookManipulation(final String path) {
        this.path = path;
    }

    /**
     * Parsing XML file and invoke transformToBIDAndASK and output methods.
     * @throws ParserConfigurationException throw if problem with XML parsing.
     * @throws IOException throw if problem to get to the XML file or reed file.
     */

    public void manipulation() throws ParserConfigurationException, IOException {
        try {
            File filePath = new File(this.path);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(filePath, handler);

            transformToBIDAndASK();
            outPut(bid, ask);

        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * fill data from one map to two another bid and ask maps.
     */

    public void transformToBIDAndASK() {
        for (Map.Entry<Integer, Order> entry : handler.getOrderBookMap().entrySet()) {
            if (entry.getValue().getOperation().equals("BUY")) {
                bid.put(entry.getKey(), entry.getValue());
            }
            if (entry.getValue().getOperation().equals("SELL")) {
                ask.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Fill two treeSet set with comparator to sort data and output bid and ask data to console.
     * Bid ladder is sorted from the highest bid price at the top to lowest price at the bottom.
     * Ask ladder – visa versa, sorted from lowest at the top to highest price at the bottom.
     *
     *  BID           ASK
     * 10@100.1  – 11@100.2.
     * 4@100.0   – 14@100.21.
     * 98@99.98  – 14@100.23.
     *  -----    – 12@101.00
     *
     * @param bid is consist with buy ladder
     * @param ask is consist with sell ladder.
     */

    public void outPut(Map<Integer, Order> bid, Map<Integer, Order> ask) {
        Set<Map.Entry<Integer, Order>> bidSetEntry = bid.entrySet();
        Set<Map.Entry<Integer, Order>> askSetEntry = ask.entrySet();

        TreeSet<Order> ordersSetBid = new TreeSet<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o2.getPrice().compareTo(o1.getPrice()) == 0) {
                    return 1;
                }
                return o2.getPrice().compareTo(o1.getPrice());
            }
        });

        TreeSet<Order> ordersSetAsk = new TreeSet<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getPrice().compareTo(o2.getPrice()) == 0) {
                    return 1;
                }
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
        Iterator<Map.Entry<Integer, Order>> itrBidSet = bidSetEntry.iterator();
        Iterator<Map.Entry<Integer, Order>> itrAskSet = askSetEntry.iterator();

        while (itrBidSet.hasNext()) {
            ordersSetBid.add(itrBidSet.next().getValue());
        }
        while (itrAskSet.hasNext()) {
            ordersSetAsk.add(itrAskSet.next().getValue());
        }
        shiftVolume(ordersSetBid, ordersSetAsk);
        Iterator<Order> itrBidSetOrder = ordersSetBid.iterator();
        Iterator<Order> itrAskSetOrder = ordersSetAsk.iterator();

        while (itrAskSetOrder.hasNext() || itrBidSetOrder.hasNext()) {
            StringBuilder stringBuilderBid = new StringBuilder();
            StringBuilder stringBuilderAsk = new StringBuilder();
            String bidVolume;
            String askVolume;
            double bidPrice;
            double askPrice;

            if (itrBidSetOrder.hasNext()) {
                Order ordBid = itrBidSetOrder.next();
                bidVolume =  String.valueOf(ordBid.getVolume());
                bidPrice = ordBid.getPrice();
                stringBuilderBid.append(bidVolume).append("@").append(bidPrice);
            } else {
                stringBuilderBid.append("  ---  ");
            }

            if (itrAskSetOrder.hasNext()) {
                Order ordAsk = itrAskSetOrder.next();
                askVolume =  String.valueOf(ordAsk.getVolume());
                askPrice = ordAsk.getPrice();
                stringBuilderAsk.append(askVolume).append("@").append(askPrice);
            } else {
                stringBuilderAsk.append("  ---  ");
            }

            System.out.printf("%-10s  %s\n", stringBuilderBid, stringBuilderAsk);
        }

    }

    /**
     * When new bid/ask order is added into order book you should check opposite book side for matching.
     * Checking always starts from the top of opposite side. To get execution (matching) price of counter orders.
     * must overlap (bid>=ask). For example:
     *
     * bid order at 4.4 matches ask order at 4.3
     * ask order at 3.7 matches bid order at 3.8
     * opposite side orders with same price matches as well.
     *
     *
     * Matching is a process of execution two counter orders.
     * When orders are matched they current volume is decreased by minimum current volumes of these orders:.
     * volume_executed=min(ask_order,bid_order).  So when there is a match you need to adjust orders quantities.
     * There could be three cases:.
     * Order existing in order book fully filled by incoming order à remove existing.
     * ,adjust quantity of incoming order and add it.
     * Incoming order fully filled by existing order à adjust quantity of existing order.
     * Full quantity match à Remove existing order.
     * @param bid is set of sorted sell ladder.
     * @param ask is set of sorted buy ladder.
     */

    public void shiftVolume(Set<Order> bid, Set<Order> ask) {
        int volumeResBid;
        int volumeResAsk;
        Iterator<Order> itrBid = bid.iterator();
        Iterator<Order> itrAsk = ask.iterator();
        Order bidOrder = itrBid.next();
        Order askOrder = itrAsk.next();
        double bidPrice = bidOrder.getPrice();
        double askPrice = askOrder.getPrice();
        int bidVolume = bidOrder.getVolume();
        int askVolume = askOrder.getVolume();

        while (itrBid.hasNext() && itrAsk.hasNext() && bidPrice >= askPrice) {
            while (bidPrice >= askPrice) {
                if (askPrice > bidPrice) {
                    break;
                }
                volumeResBid = bidVolume - askVolume;
                volumeResAsk = askVolume - bidVolume;

                if (volumeResBid <= 0) {
                    itrBid.remove();
                    if (itrBid.hasNext()) {
                        Order nextBidOrder =  itrBid.next();
                        bidPrice = nextBidOrder.getPrice();
                        bidVolume = nextBidOrder.getVolume();
                        askVolume = volumeResAsk;
                    break;
                    }
                }

                if (volumeResAsk <= 0) {
                    itrAsk.remove();
                    if (itrAsk.hasNext()) {
                        Order nextAskOrder =  itrAsk.next();
                        askPrice = nextAskOrder.getPrice();
                        askVolume = nextAskOrder.getVolume();
                        bidVolume = volumeResBid;

                    break;
                    }
                }
            }
        }
    }
}
