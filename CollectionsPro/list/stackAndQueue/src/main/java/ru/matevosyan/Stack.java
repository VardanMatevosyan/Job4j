package ru.matevosyan;

import java.util.NoSuchElementException;

/**
 * Stack class.
 * Created on 23.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Stack<E> implements IStack<E> {

    private Node<E> top;
    private int size = 0;

    /**
     * Constructor.
     */

    public Stack () {

    }

    /**
     * Create to add value to a list.
     * @param value is value that type which you declare in generics.
     */

    @Override
    public E push(E value) {
        addElement(value);
        return value;
    }


    /**
     * Remove value from top passing index.
     * @return value that type specify in generic.
     */

    @Override
    public E pop() {
        E element;
        Node<E> becomeTop;
        if (size == 0) {
            throw new NoSuchElementException("No such element");
        } else {
            element = peek();

            becomeTop = top.next;
            top = becomeTop;


        }

        return element;
    }


    /**
     * Create to get the last value from list.
     * @return value that type that was declare in generic diamonds.
     * @throws IllegalArgumentException if is not correct index size.
     * @throws ArrayIndexOutOfBoundsException if index is bigger than actual.
     */

    @Override
    public E peek() throws IllegalArgumentException {

        if (size == 0) {
            throw new NoSuchElementException("Noo such element");
        } else if (size < 0) {
            throw new IllegalArgumentException("illegal argument");
        }

        return top.item;
    }

    /**
     * linkLast assign LinkedList Node to the last Node in the list and if it is the first value assign as first too.
     * @param elementValue is value.
     */

    private void addElement(E elementValue) {
        Node<E> topNode = top;
        Node<E> newNode = new Node<>(elementValue);
        top = newNode;

        if (topNode != null) {
            newNode.next = topNode;
        }

        size++;
    }

    /**
     * Class Node describe Stack as LinkedList entry.
     * @param <E> parameter that defined when create an instance of a class.
     */

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
    }

}