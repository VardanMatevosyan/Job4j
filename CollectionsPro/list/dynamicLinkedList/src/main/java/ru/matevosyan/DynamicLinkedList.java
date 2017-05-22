package ru.matevosyan;

/**
 * DynamicLinkedList class.
 * Created on 21.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DynamicLinkedList<E> implements IDynamicLinkedList<E>{

    private Object[] container;
    private Node<E> last;
    private int size = 0;
    private Node<E> first;

    /**
     * Constructor.
     */

    public DynamicLinkedList() {

    }

    /**
     * Create to add value to an array.
     * @param value is value that type which you declare in generics.
     * @throws ArrayIndexOutOfBoundsException if index is bigger than actual.
     */

    @Override
    public void add(E value) throws ArrayIndexOutOfBoundsException {
        linkLast(value);
    }


    /**
     * Create to get the value from an array.
     * @param index position value in an array.
     * @return value that type that was declare in generic diamonds.
     * @throws IllegalArgumentException if is not correct index size.
     * @throws ArrayIndexOutOfBoundsException if index is bigger than actual.
     */

    @Override
    public E get(int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return node(index).item;
    }

    private Node<E> node(int index) {
        Node<E> entryNode;
        if(index < (size >> 1)) {
            entryNode = first;
            for (int i = 0; i < index; i++) {
                entryNode = entryNode.next;
            }
        } else {
            entryNode = last;
            for (int i = size - 1; i > index; i--) {
                entryNode = entryNode.next;
            }
        }
        return entryNode;
    }

    /**
     * Check array size and rise it when an array length is equal or bigger that default size.
     * Or if it is the last index that going to be hold by passing value.
     * @param elementValue is value.
     */

    private void linkLast(E elementValue) {
        Node<E> lastNode = last;
        Node<E> newNode = new Node<>(lastNode, elementValue, null);
        last = newNode;
        if (!(lastNode == null)) {
            lastNode.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }


    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

//    /**
//     * Override iterator method from interface Iterable.
//     * @return an instance of Iterator type.
//     */
//
//    @Override
//    public Iterator<E> iterator() {
//
//        return new Iterator<E>() {
//
//            int count = 0;
//            @Override
//            public boolean hasNext() {
//                return count < container.length - (container.length - index);
//            }
//
//            @Override
//            @SuppressWarnings("unchecked")
//            public E next() {
//                return (E) container[count++];
//            }
//        };
//    }
}