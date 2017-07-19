package ru.matevosyan;

/**
 * SimpleTree for Tree class.
 * @param <E> generic type which are comparable.
 */

public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return true if added.
     */
    boolean add(E parent, E child);
}
