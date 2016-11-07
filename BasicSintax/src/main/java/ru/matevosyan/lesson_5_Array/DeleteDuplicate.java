package ru.matevosyan.lesson_5_Array;
import java.lang.*;

public class DeleteDuplicate{

	public String[] deleteArrDuplicate (String mas[]) {
		
		//copy uniqe elements

		int counter = 0;

		String[] copyArray = new String[mas.length];

		for (int i = 0; i < mas.length; i++) {

				
			if (!(containsStringinArray(copyArray, mas[i]))) {
					
				copyArray[i] = mas[i];
				
				counter++;

			}

		}
		
		//delete duplicate elements

		String[] uniqeStringArray = new String[counter];

		int uniqeArrayPosition = 0;
		
		for (int k = 0; k < copyArray.length; k++) {
		
			if (copyArray[k] != null) {
			
				uniqeStringArray[uniqeArrayPosition] = copyArray[k];
				
				uniqeArrayPosition++;
			}
		
		}
		
	return uniqeStringArray;
	
	}

	public boolean containsStringinArray (String[] arr, String x){
			
			for (int i = 0; i < arr.length; i++) {
			
				if(arr[i]!=null){

					if (arr[i]==x) {
					
						return true;
					
					}
				
				}
				
			}

		return false;
			
		}

}