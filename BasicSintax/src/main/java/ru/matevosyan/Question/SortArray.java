package ru.matevosyan;

public class SortArray{

	public int[] sort(int[] arrFirst, int[] arrSecond){

		int j = 0;
		int i = 0;
		int k = 0;

		int length = arrFirst.length + arrSecond.length;
		int[] thirdArray = new int[length];
			
		while(i < arrFirst.length && j < arrSecond.length){
			
			if(arrFirst[i] <= arrSecond[j]){

				thirdArray[k] = arrFirst[i];

				i++;

			} else {

				thirdArray[k] = arrSecond[j];

				j++;

			}
		k++;
 		
		}

		while(i < arrFirst.length) {
		
			thirdArray[k] = arrFirst[i];

			i++;
			k++;

		}

		while(j < arrSecond.length) {
		
			thirdArray[k] = arrSecond[j];

			j++;
			k++;

		}




	return thirdArray;

	}

}