package ru.matevosyan;

import java.util.NoSuchElementException;

/**
 * SimpleArray class.
 * Created on 14.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <T> undefine type.
 */

public class SimpleArray<T> {

    private Object[] object;
    private int index = 0;

    /**
     * SimpleArray constructor.
     * @param size array length.
     */

    public SimpleArray(int size) {
        this.object = new Object[size];
    }

    /**
     * Method add() add value, defined in the instance of SimpleArray class to the array.
     * @param value  that would be added to array.
     */

    public void add(T value) {
        this.object[index++] = value;
    }

    /**
     * Method update change first pass value to second pass value.
     * @param currentValue value that going to be changed.
     * @param updateValue value that going to be added instead of first pass value.
     */

    public void update(T currentValue, T updateValue) {
        for (int i = 0; i < this.object.length; i++) {
            if (this.object[i].equals(currentValue)) {
                this.object[i] = updateValue;
            }
        }
    }

    /**
     * Method delete() created to delete value from the array.
     * @param value that going to be deleted from the array.
     * @return boolean value.
     */

    public boolean delete(T value) {
        boolean isDeleted = false;
        try {
            for (int i = 0; i < this.object.length; i++) {
                if (this.object[i] != null && this.object[i].equals(value)) {
                    int size = this.object.length - i - 1;
                    System.arraycopy(this.object, i + 1, this.object, i, size);
                }
                this.object[this.object.length - 1] = null;
                isDeleted = true;
            }
        } catch (NoSuchElementException nse) {
            nse.getMessage();
        }
        return isDeleted;
    }

    /**
     * Method get() created to get the value from the array passing the index.
     * @param position in array, which value going to get.
     * @throws NullPointerException exception.
     * @return value from index was passed to the method.
     */

    @SuppressWarnings("unchecked")
    public T get(int position) throws NullPointerException {
        return (T) this.object[position];
    }

    /**
     * Array getter.
     * @return object holder.
     */

    public Object[] getObject() {
        return this.object;
    }
}
