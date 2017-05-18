package ru.matevosyan;

/**
 * Created by Admin on 18.05.2017.
 */
public interface IDynamicArray<E> {
    void add(E value);

    E get(int index);
}
