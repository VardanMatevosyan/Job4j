package ru.matevosyan;

/**
 * Created BiggerRepeatValue fo finding bigger repeating number in array.
 * Created on 25.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BiggerRepeatValue {

    /**
     * Create to find bigger repeating value.
     * @param intNumbers array with which we find bigger number
     * @return <code>maxCount</code>
     */

    public int findRepeatValues(int[] intNumbers) {
        int countFor = 0;
        int maxCount = 0;
        int number = 0;

        for (int i = 0; i < intNumbers.length - 1; i++) {

            if (intNumbers[i] == intNumbers[i + 1]) {
                countFor++;
                if (maxCount < countFor) {
                    maxCount = countFor;
                    number = intNumbers[i];
                }

            } else {
                countFor = 0;
            }
        }
        System.out.printf("Number %d is repeating %d times", number, maxCount);
        return maxCount;
    }
}
