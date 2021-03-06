package ru.matevosyan;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ReferenceBook class.
 * Created on 23.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <K> key.
 * @param <V> value.
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
     * @param initCapacity initializing array capasity.
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
        int len = this.table.length;
        int cell = hash(key) & (len - 1);
        if (this.table[cell] == null) {
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
     * @return V - the object value.
     */

    V get(K key) {
        Node<K, V> node;
        int len = this.table.length;
        int cell = hash(key) & (len - 1);
        if (key != null && (this.table[cell] != null)) {
            node = this.table[cell];
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
        int cell = hash(key) & (len - 1);
        if (key != null && (this.table[cell] != null)) {
            this.table[cell] = null;
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
        if (key != null) {
            int h = key.hashCode();
            return (h) ^ (h >>> 16);
        } else {
            return 0;
        }
    }

    /**
     * iterator use to iterate by reference-book.
     * @return iterator of {@link ReferenceBook.Node}
     */

    @Override
    public Iterator<ReferenceBook.Node<K, V>> iterator() {
        return new Iterator<ReferenceBook.Node<K, V>>() {
            private int j = 0;
            @Override
            public boolean hasNext() {
                boolean has = false;
                for (int i = this.j; i < table.length; i++) {
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
        private final K0 key;
        private V0 value;

        /**
         * Constructor.
         * @param key is key.
         * @param value is value.
         */
        Node(K0 key, V0 value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Getter for key.
         * @return k0 - key.
         */
        public final K0 getKey() {
            return key;
        }

        /**
         * Getter for value.
         * @return V0 - value.
         */
        public final V0 getValue() {
            return value;
        }

    }
}
