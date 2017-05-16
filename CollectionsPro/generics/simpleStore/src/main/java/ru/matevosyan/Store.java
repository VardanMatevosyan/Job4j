package ru.matevosyan;

/**
 * Created by Admin on 16.05.2017.
 */
public interface Store<T extends Base> {

    void add(T value);

    void update(String id, T second);

    void delete(String id);

    T get(String id);
}
