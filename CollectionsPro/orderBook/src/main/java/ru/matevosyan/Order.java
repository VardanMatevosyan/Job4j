package ru.matevosyan;


/**
 * OrderBook class.
 * Created on 06.08.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Order {

    private final String book;
    private final String operation;
    private final Integer volume;
    private final Double price;
    private final Integer orderId;

    /**
     * Override method {@link Object#equals(Object)} to logical correctness.
     * @param o is passing Order object to assignment.
     * @return true if order are equals.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order other = (Order) o;

        return this.orderId.equals(other.orderId);
    }

    /**
     * Override {@link Object#hashCode()}
     * @return int value - Object hashCode.
     */

    @Override
    public int hashCode() {
        int result = volume.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + orderId.hashCode();
        return result;
    }

    /**
     * Order class constructor.
     * @param book order book.
     * @param operation that can be "BUY" or "SELL".
     * @param volume represents the order rate.
     * @param price order price.
     * @param orderId is order id.
     */

    public Order(String book, String operation, Integer volume, Double price, Integer orderId) {
        this.book = book;
        this.operation = operation;
        this.volume = volume;
        this.price = price;
        this.orderId = orderId;
    }

    /**
     * Getter for order book.
     * @return order book.
     */

    public String getBook() {
        return book;
    }

    /**
     * Getter for order operation.
     * @return buy or sell.
     */

    public String getOperation() {
        return operation;
    }

    /**
     * Getter for volume.
     * @return order volume value.
     */

    public Integer getVolume() {
        return volume;
    }

    /**
     * Getter for order price.
     * @return order price value.
     */

    public Double getPrice() {
        return price;
    }

    /**
     * Getter for order id.
     * @return order id.
     */

    public Integer getOrderId() {
        return orderId;
    }
}