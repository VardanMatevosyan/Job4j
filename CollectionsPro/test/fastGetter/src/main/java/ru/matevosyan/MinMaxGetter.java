package ru.matevosyan;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * MinMaxGetter class.
 * Created on 12.08.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <K> key.
 * @param <V> value.
 */

public class MinMaxGetter<K extends Integer, V extends Integer> extends HashMap {
    private Map<K, V> map = new HashMap<>();
    private Deque<K> stackOfMinValuesKey = new ArrayDeque<>();
    private Deque<K> stackOfMaxValuesKey = new ArrayDeque<>();
    private Deque<K> mainStack = new ArrayDeque<>();
    private K key;

    /**
     * Constructor.
     */

    public MinMaxGetter() {
    }

    /**
     * Add key and value to the map and add smaller values key to the stackOfMaxValuesKey.
     * and bigger values key to the stackOfMinValuesKey.
     * @param k is key.
     * @param v is value.
     */

    public void add(K k, V v) {
        this.map.put(k, v);

        if (this.stackOfMinValuesKey.isEmpty()) {
            this.stackOfMinValuesKey.push(k);
        } else if (this.map.get(this.stackOfMinValuesKey.peek()).compareTo(v) == 0
                || this.map.get(this.stackOfMinValuesKey.peek()).compareTo(v) > 0) {
            this.stackOfMinValuesKey.push(k);
        }

        if (this.stackOfMaxValuesKey.isEmpty()) {
            this.stackOfMaxValuesKey.push(k);
            this.key = k;
        } else if (this.map.get(this.stackOfMaxValuesKey.peek()).compareTo(v) == 0
                || this.map.get(this.stackOfMaxValuesKey.peek()).compareTo(v) < 0) {
            if (this.stackOfMaxValuesKey.size() == 1 && this.stackOfMaxValuesKey.peek().compareTo(this.key) == 0) {
                this.stackOfMaxValuesKey.pop();
            }
            this.stackOfMaxValuesKey.push(k);

        }

        this.mainStack.push(k);

    }

    /**
     * get minimum values key.
     * @return K key.
     * @throws NoSuchElementException throw id stack is empty.
     */

    public K getMinValuesKey() throws NoSuchElementException {

        if (this.stackOfMinValuesKey.isEmpty()) {
            throw new NoSuchElementException("No element");
        }
        return this.stackOfMinValuesKey.peek();
    }

    /**
     * get minimum value from the map.
     * @return K key.
     * @throws NoSuchElementException throw id stack is empty.
     */

    public V getMinValue() throws NoSuchElementException {
        if (this.stackOfMinValuesKey.isEmpty()) {
            throw new NoSuchElementException("No element");
        }
        return this.map.get(this.stackOfMinValuesKey.peek());
    }

    /**
     * get maximum values key.
     * @return K key.
     * @throws NoSuchElementException throw id stack is empty.
     */

    public K getMaxValuesKey() throws NoSuchElementException {
        if (this.stackOfMaxValuesKey.isEmpty()) {
            throw new NoSuchElementException("No element");
        }
        return this.stackOfMaxValuesKey.peek();
    }

    /**
     * get maximum value from the map.
     * @return K key.
     * @throws NoSuchElementException throw id stack is empty.
     */

    public V getMaxValue() throws NoSuchElementException {
        if (this.stackOfMaxValuesKey.isEmpty()) {
            throw new NoSuchElementException("No element");
        }
        return this.map.get(this.stackOfMaxValuesKey.peek());
    }

    /**
     * delete last added value from the map and the stack.
     * @return K pop out key.
     * @throws NoSuchElementException throw id stack is empty.
     */

    public K popKey() throws NoSuchElementException {

        if (this.mainStack.isEmpty()) {
            throw new NoSuchElementException("No element");
        }

        K keyValue = this.mainStack.pop();

        if (getMinValuesKey().compareTo(keyValue) == 0) {
            this.stackOfMinValuesKey.pop();
            this.map.remove(keyValue);
        }

        if (getMaxValuesKey().compareTo(keyValue) == 0) {
            this.stackOfMaxValuesKey.pop();
            this.map.remove(keyValue);
        }
        return keyValue;
    }

    /**
     * return true if the map contains key.
     * @param key is passing key.
     * @return true if contains key, else false.
     */

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    /**
     * return true if the map contains value.
     * @param value is passing value.
     * @return true if contains value, else false.
     */

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }
}