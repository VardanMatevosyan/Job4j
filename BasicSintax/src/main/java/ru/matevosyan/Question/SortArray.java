package ru.matevosyan.Question;

/**
* <p>
* This classe contain sort method.
* </p>
*
* @since 1.8
* @author Matevosyan Vardan
* @version 1.0
*/

public class SortArray {

/**
*sort method that will return third sorted array.
*
* @param arrFirst it is passing first sorted array
* @param arrSecond it is passing second sorted array
* @return tag third sorted array
*/

public final int[] sort(final int[] arrFirst, final int[] arrSecond) {

int j = 0;
int i = 0;
int k = 0;

int length = arrFirst.length + arrSecond.length;
int[] thirdArray = new int[length];

while (i < arrFirst.length && j < arrSecond.length) {

if (arrFirst[i] <= arrSecond[j]) {

thirdArray[k] = arrFirst[i];
i++;

} else {

thirdArray[k] = arrSecond[j];
j++;

}
k++;

}
while (i < arrFirst.length) {

thirdArray[k] = arrFirst[i];

i++;
k++;

}

while (j < arrSecond.length) {

thirdArray[k] = arrSecond[j];

j++;
k++;

}

return thirdArray;
}
}
