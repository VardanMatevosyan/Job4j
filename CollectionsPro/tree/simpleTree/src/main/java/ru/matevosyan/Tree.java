package ru.matevosyan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Tree class.
 * Created on 19.07.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> rootNode;
    private int size = 0;

    /**
     * Get root node.
     * @return root node as Node object.
     */

    public Node<E> getNode() {
        return this.rootNode;
    }

    /**
     * Override iterator for Tree class.
     * @return generic value E.
     */

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> next;
            Iterator iterator = rootNode.children.iterator();
            int countHasNext = size;
            @Override
            public boolean hasNext() {
                return countHasNext > 0;
            }

            @Override
            public E next() {
                countHasNext--;

                if (countHasNext == size - 1) {
                    return rootNode.value;
                } else if (iterator.hasNext()) {
                        Node<E> next = (Node<E>) iterator.next();
                        return next.value;
                }
               return  next.value;
            }
        };
    }

    /**
     * Override add method in SimpleTree interface and it is added child value to parent value in the tree.
     * @param parent parent.
     * @param child child.
     * @return true if value is added, else return false.
     */

    @Override
    public boolean add(E parent, E child) {
        boolean isAdded;

        if (parent == null) {
            return false;
        }

        if (rootNode == null) {
            rootNode = new Node<>(parent);
        }

        ListIterator tmpItr = rootNode.children.listIterator();

        @SuppressWarnings("unchecked")
        Node<E> parentNodee = getParentBode(rootNode.children, tmpItr, parent);
        boolean isEquals = isEquals(rootNode.children, parentNodee);
        Node<E> newNode;
        if (isEquals) {
            newNode = new Node<>(child);

            if (parentNodee.compareTo(parent) == 0) {
                parentNodee.children.add(newNode);
                size++;
            }

            isAdded = true;

        } else  if (rootNode.compareTo(parent) == 0) {
            newNode = new Node<>(child);
            rootNode.children.add(newNode);
            size++;
            isAdded = true;
        } else {
            isAdded = false;
        }
        return isAdded;
    }

    /**
     * getParentBode is method that return parent node from the tree.
     * @param children children list.
     * @param top iterator that check from the root.
     * @param parent search value.
     * @return parent node if it is there or node with null value if there is not any node with that value.
     */

    @SuppressWarnings("unchecked")
    private Node<E> getParentBode(List<Node<E>> children, ListIterator<Node<E>> top, E parent) {

        Node<E> parentNode = new Node<>(parent);
        ListIterator itr = children.listIterator();
        Node<E> f = null;

        while (itr.hasNext() || top.hasNext()) {

            if (!itr.hasNext()) {
                if (top.hasNext()) {
                    f = top.next();
                }
            }

            if (itr.hasNext()) {
                f = (Node<E>) itr.next();
            }

            if (f == null) {
                return new Node<E>(null);
            }

            if (f.value.equals(parentNode.value)) {
                parentNode = f;
                break;
            } else {
                itr = f.children.listIterator();
            }
        }

        return parentNode;
    }

    /**
     * check if parent node value really exist.
     * @param children list of children.
     * @param node parent node.
     * @return true if node value exist in the tree.
     */

    @SuppressWarnings("unchecked")
    private boolean isEquals(List<Node<E>> children, Node<E> node) {

        ListIterator iterator = children.listIterator();
        ListIterator itrRoot = rootNode.children.listIterator();
        Node<E> f = null;
        boolean isEql = false;
        while (iterator.hasNext() || itrRoot.hasNext()) {
            if (!iterator.hasNext()) {
                if (itrRoot.hasNext()) {
                    f = (Node<E>) itrRoot.next();
                }
            }

            if (iterator.hasNext()) {
                f = (Node<E>) iterator.next();
            }

            if (node.value == null) {
                return false;
            }
            if (f != null) {
                if (f.value.equals(node.value)) {
                    isEql = true;
                } else {
                    iterator = f.children.listIterator();
                }
            }

        }

        return isEql;
    }

    /**
     * Check if tree is binary tree or not.
     * @return true if tree is binary, else return false.
     */

    public boolean isBinary() {

        boolean isBinary = false;
        List<Node<E>> list = rootNode.children;
        ListIterator<Node<E>> itrList = list.listIterator();

        while (itrList.hasNext()) {

            if (list.size() > 2) {
                isBinary = false;
                break;
            } else if (list.size() <= 2) {
                isBinary = true;
            }

            if (list.size() > 0) {
                Node<E> node = itrList.next();
                itrList = node.children.listIterator();
                list = node.children;
            }

        }

        return isBinary;
    }



    /**
     * Node class creates as entity.
     * @param <E1> generic value.
     */

    public  class Node<E1> implements Comparable<E1>{
        List<Node<E1>> children;
        E1 value;
        Node<E1> left;
        Node<E1> right;

        public Node(E1 value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        @Override
        public int compareTo(E1 obj) {
            Node<E1> node = new Node<>(obj);
            return this.hashCode() - node.hashCode();
        }

        @Override
        public int hashCode() {
            int code = 17;
            code  = 31 * code + value.hashCode();
            return code;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        /**
         * Create addToBinarySearchTree to add value to the tree.
         * And add as binary search tree.
         * @param data value to add to the binary search tree.
         * @return true if value id added, else return false.
         */

        public boolean addToBinarySearchTree(E1 data) {
            boolean added = false;
            Node<E1> node = new Node<>(data);
            Node<E1> value = new Node<>(this.value);
            if (value.compareTo(data) >= 0) {
                if (this.left == null) {
                    this.left = node;
                    added = true;
                } else {
                    added = true;
                    this.left.addToBinarySearchTree(data);
                }
            } else if (value.compareTo(data) < 0) {
                if (this.right == null) {
                    this.right = node;
                    added = true;
                } else {
                    added = true;
                    this.right.addToBinarySearchTree(data);
                }
            }
            return added;
        }

        //-----------------------------------------------------
        public void inOrderTraversingPrinter() {
            String ln = System.lineSeparator();

            if (left != null) {
                left.inOrderTraversingPrinter();
            }

            System.out.printf("value - %s%s", value, ln);

            if (right != null) {
                right.inOrderTraversingPrinter();
            }
        }
        //-----------------------------------------------------
    }

}
