package ru.matevosyan;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ReferenceBook class.
 * Created on 23.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ReferenceBook<K, V> implements Iterable<ReferenceBook.Node<K, V>> {

    private Node<K, V>[] table;
    private static final int CONTAINER_CAPACITY = 16;
    private int size = 0;

    /**
     * Constructor with default capacity.
     */

    public ReferenceBook() {
        this(CONTAINER_CAPACITY);
    }

    /**
     * Constructor with initCapacity.
     */

    @SuppressWarnings("unchecked")
    public ReferenceBook(int initCapacity) {
        this.table = new Node[initCapacity];
    }

    /**
     * Insert method use to add object with key and value to a reference-book.
     * @param key is the object key.
     * @param value is the object value.
     * @return true if insertion id done well, else return false.
     */

    boolean insert(K key, V value) {
        int cell;
        int len = this.table.length;

        if (this.table[cell = hash(key) & (len - 1)] == null) {
            this.table[cell] = new Node<>(key, value);
            size++;
            return true;
        } else {
            return false;
        }

    }

    /**
     * get method use to get an object from the reference-book.
     * @param key is the object key.
     * @return the object value.
     */

    V get(K key){
        Node<K, V> node;
        int len = this.table.length;
        if (key != null && (this.table[hash(key) & (len - 1)] != null)) {
            node = this.table[hash(key) & (len - 1)];
            return node.getValue();
        } else {
            return null;
        }
    }

    /**
     * delete method use to delete object from the reference-book.
     * @param key is the object key.
     * @return true if object was deleted.
     */

    boolean delete(K key) {
        int len = this.table.length;
        if (key != null && (this.table[hash(key) & (len - 1)] != null)) {
            this.table[hash(key) & (len - 1)] = null;
            size--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * gerSize use to get reference-book size.
     * @return size.
     */

    int getSize() {
        return size;
    }

    /**
     * hash function use get the number of buckets, where the object is gonna live.
     * @param key is the object key.
     * @return number of buckets.
     */

    private static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * iterator use to iterate by reference-book.
     * @return iterator of {@link ReferenceBook.Node}
     */

    @Override
    public Iterator<ReferenceBook.Node<K, V>> iterator() {
        return new Iterator<ReferenceBook.Node<K, V>>() {
            int j = 0;
            @Override
            public boolean hasNext() {
                boolean has = false;

                for(int i = this.j; i < table.length; i++) {
                    if (table[i] != null) {
                        this.j = i;
                        has = true;
                        break;
                    }
                }
                return size < table.length & has;
            }

            @Override
            public ReferenceBook.Node<K, V> next() throws NoSuchElementException {
                if (hasNext()) {
                    return table[j++];
                } else {
                    throw new NoSuchElementException("No such element");
                }

            }
        };
    }

    /**
     * Node use to hold object key and value together.
     * @param <K0> is the first parameter type, that gonna be object key.
     * @param <V0> is the second parameter type, that gonna be object value.
     */

     static class Node<K0, V0> {
        final K0 key;
        V0 value;

        public Node(K0 key, V0 value) {
            this.key = key;
            this.value = value;

        }

        public final K0 getKey() {
            return key;
        }

        public final V0 getValue() {
            return value;
        }

    }



}