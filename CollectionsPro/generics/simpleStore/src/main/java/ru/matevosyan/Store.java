package ru.matevosyan;

/**
 * interface Store<T extends Base>.
 * Created on 16.05.2017.
 * @author Matevosyan Vardan.
 * @version 1.0.
 */

public interface Store<T extends Base> {

    /**
     * Method add() added value to {@link SimpleArray#object}.
     * @param value is all Base class child value.
     */

    void add(T value);

    /**
     * Method update(String id, T second) update value in {@link SimpleArray#object}.
     * @param id is values id in {@link SimpleArray#object}.
     * @param second is all Base class child value that going to be added instead of value with id that passing.
     * to this method.
     */

    void update(String id, T second);

    /**
     * Method delete(String id) delete value from {@link SimpleArray#object}.
     * @param id is values id in {@link SimpleArray#object} that going to be deleted.
     * @return true if value wad deleted.
     */

    boolean delete(String id);

    /**
     * Method get(String id) return value from {@link SimpleArray#object}.
     * @param id is values id in {@link SimpleArray#object} that going to be return.
     * @return is value from {@link SimpleArray#object}.
     */

    T get(String id);
}
