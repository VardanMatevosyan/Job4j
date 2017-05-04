package ru.matevosyan;

/**
 * EvenNumber class.
 * Created on 03.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class NaturalNumber implements NaturalNumberIterator {

    private int index = 0;
    private final int[] array;

    /**
     * EvenNumber constructor.
     * @param array an array.
     */

    public NaturalNumber (int[] array) {
        this.array = array;
    }

    /**
     * Override the next() method to iterate each element from an array.
     * @return even numbers from an array.
     */

    @Override
    public int next() {
//
//        for (int i = index++; i < array.length && index <= array.length; i++) {
////            int num = array[i];
//            if ((array[i] > 1) && !(array[i] % 2 == 0) || (array[i] == 2)) {
//                value = i;
//                break;
//            } else {
//                index++;
//            }
//        }

        int result = 0;

            if (isSimpleOrNot() && this.array.length > this.index) {
                result = this.array[this.index];
this.index++;
            }

        return result;

    }

    /**
     * Override hasNext() method to check if eny of elements are in array to read, looking to next() pointer.
     * @return true if there are any elements to read, else return false.
     */

    @Override
    public boolean hasNext() {
        return this.array.length > this.index && (isSimpleOrNot()|| this.array.length > this.index);
    }

    private boolean isSimpleOrNot() {
        boolean isSimple = true;

        if (this.array[this.index] == 1) {
            this.index++;
            isSimple = false;

        }

        for (int i = 2; i <= Math.sqrt(this.array[this.index]); i++) {


            if (this.array[this.index] % i == 0) {
//                this.index++;
                isSimple = false;
                break;
            } else  {
                this.index++;
            }

        }

        return isSimple;
    }
}
