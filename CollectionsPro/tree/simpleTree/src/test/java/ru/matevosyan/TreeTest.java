package ru.matevosyan;


import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * TreeTest class for testing Tree class.
 * Created on 19.07.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class TreeTest {

    /**
     * whenAddNodeToTheTreeThanCheckAddedNode created for testing.
     * that value with are added to the tree.
     * is added and look like  hierarchy, like a tree.
     */

    @Test
    public void whenAddNodeToTheTreeThanCheckAddedNode() {
        Tree<String> stringTree = new Tree<>();
        boolean isRootAdded = stringTree.add("mammy", "child");
        stringTree.add("mammy", "moreChild");
        stringTree.add("moreChild", "moreChildChild2");
        stringTree.add("moreChildChild2", "moreChildChild3");
        boolean isAdded = stringTree.add("child", "child2");
        stringTree.add("child2", "child3");
        stringTree.add("child3", "cdhild4");
        boolean isNotAdded = stringTree.add("daddsy", "child2");
        stringTree.add(null, "child2");

        String s = stringTree.getNode().getValue();

        assertThat(s, is("mammy"));
        assertThat(isNotAdded, is(false));
        assertThat(isAdded, is(true));
        assertThat(isRootAdded, is(true));

    }

    /**
     * create whenAddNodeToTheTreeThanCheckAddedNodeWithIterator.
     * for testing return value using iterator method.
     */

    @Test
    public void whenAddNodeToTheTreeThanCheckAddedNodeWithIterator() {
        Tree<String> stringTree = new Tree<>();
        stringTree.add("mammy", "child");
        stringTree.add("mammy", "moreChild");

        Iterator itr = stringTree.iterator();
        String string = "";
        if (itr.hasNext()) {
            string = (String) itr.next();
        }

        assertThat(string, is("mammy"));
    }

    /**
     * create whenAddNodeToTheTreeThanCheckAddedNodeWithIterator for testing.
     * return last value using iterator method.
     */

    @Test
    public void whenAddNodeToTheTreeAndIterateWithIteratorThanCheckAddedNodeWithIterator() {
        Tree<String> stringTree = new Tree<>();
        stringTree.add("mammy", "child");
        stringTree.add("mammy", "moreChild");

        Iterator itr = stringTree.iterator();
        String string = "";
        while (itr.hasNext()) {
            itr.next();
            itr.next();
            string = (String) itr.next();
        }

        assertThat(string, is("moreChild"));
    }

    /**
     * Check if the tree is binary.
     */

    @Test
    public void whenAddNodeToTheTreeThanCheckIfItIsBinary() {
        Tree<String> stringTree = new Tree<>();
        stringTree.add("mammy", "child");
        stringTree.add("mammy", "moreChild");
        stringTree.add("moreChild", "moreChildChild2");
        stringTree.add("moreChildChild2", "moreChildChild3");
        stringTree.add("child", "child2");
        stringTree.add("child2", "child3");
        stringTree.add("child3", "child4");

        boolean isBinary = stringTree.isBinary();
        assertThat(isBinary, is(true));

    }

    /**
     * Check if the tree is not binary.
     */

    @Test
    public void whenAddNodeToTheTreeThanCheckIfItIsNotBinary() {
        Tree<String> stringTree = new Tree<>();
        stringTree.add("moreChild", "moreChildChild2");
        stringTree.add("moreChildChild2", "moreChildChild3");
        stringTree.add("moreChildChild2", "moreChildChild4");
        stringTree.add("moreChildChild2", "moreChildChild5");


        boolean isBinary = stringTree.isBinary();
        assertThat(isBinary, is(false));

    }

    /**
     * test tree if left node from parent tree is less than parent.
     * and right id bigger than the parent.
     */

    @Test
    public void whenAddNodeToTheTreeThanCheckIfItIsBinarySearchTree() {
        Tree<Integer> integerTree = new Tree<>();
        Tree<Integer>.Node<Integer> node = integerTree.new Node<Integer>(10);

        boolean leftBool = node.addToBinarySearchTree(5);
        boolean rightBool = node.addToBinarySearchTree(15);
        boolean lastBool = node.addToBinarySearchTree(8);

        node.inOrderTraversingPrinter();

        assertThat(leftBool, is(true));
        assertThat(rightBool, is(true));
        assertThat(lastBool, is(true));
        assertThat(node.getValue(), is(10));
        assertThat(node.getLeft().getValue(), is(5));
        assertThat(node.getRight().getValue(), is(15));
        assertThat(node.getLeft().getRight().getValue(), is(8));
    }
}