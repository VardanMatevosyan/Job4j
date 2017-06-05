package ru.matevosyan;

/**
 * FastSimpleSet class.
 * Created on 04.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class FastSimpleSet<E> extends DynamicSimpleSet<E> {

    /**
     * Constructor invoke parent constructor.
     */

    public FastSimpleSet() {
        super();
    }

    /**
     * Override method {@link DynamicSimpleSet#checkDuplicate(Object)} and use binary search.
     * @param value value that gonna be compare with element in an array.
     * @return true if container has duplicates.
     */

    @Override
    public boolean checkDuplicate(E value) {

        int left = 0;
        int size = 0;

        for (Object o : getContainer()) {
            if (o != null) {
                size++;
            }
        }

        int right = size - 1;

        boolean theSame = false;

            while (left <= right) {
                int mid = left + ((right - left) / 2);

                int hashObject = getContainer()[mid].hashCode();

                if (hashObject == value.hashCode()) {
                    theSame = true;
                } else if (hashObject < value.hashCode()) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        return theSame;
    }

    /**
     * Override sortByHash and invoke new quick sort method.
     */

    @Override
    public void sortByHash() {
        int left = 0;
        int right =  getSize() - 1;
        quickSort(getContainer(), left , right);
    }

    /**
     * Crate quickSort to sort value in the container.
     * @param container object holder.
     * @param left index from the left side of pivot.
     * @param right index from the right side of pivot.
     */

    private void quickSort(Object[] container, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivot = container[left + ((right - left) / 2)].hashCode();
        int index = partition(container, left, right, pivot);
        quickSort(container, left, index - 1);
        quickSort(container, index, right);

    }

    /**
     * Create to define which element gonna be swap.
     * @param container object holder.
     * @param left index from the left side of pivot.
     * @param right index from the right side of pivot.
     * @param pivot the value of the middle of container.
     * @return left.
     */

    private int partition(Object[] container, int left, int right, int pivot) {
        while (left <= right) {
            while (container[left].hashCode() < pivot) {
                left++;
            }

            while (container[right].hashCode() > pivot) {
                right--;
            }

            if (left <= right) {
                swap(container, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    /**
     * Create to swap the value.
     * @param container object holder.
     * @param left index from the left side of pivot.
     * @param right index from the right side of pivot.
     */

    private void swap(Object[] container, int left, int right) {
        Object temp = container[left];
        container[left] = container[right];
        container[right] = temp;
    }


}
