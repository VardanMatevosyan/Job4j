package ru.matevosyan.lesson_5_Array;

public class DeleteDuplicate{

	public String[] searchDuplicate (String mas[]) {

		int j = 0;		

		for (int k = mas.length-1; k > 0; k--) {
							
			for (int i = 0; i < mas.length - 1; i++) {
			
				if (mas[j].equals(mas[i+1])) {
				
					mas[i+1] = null;

					if (i == mas.length -1) {

						j += 1;

					}
							
				}
													
			}

		}

	return mas;

	}

}