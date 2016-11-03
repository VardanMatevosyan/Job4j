package ru.matevosyan.lesson_5_Array;

public class DeleteDuplicate{

	public String[] searchDuplicate (String mas[]) {
		

		for (int k = 0; k < mas.length - 1; k++) {

						
			for (int i = k + 1; i < mas.length; i++) {
			
				if (mas[k].equals(mas[i])) {
				
					mas[i] = "null";
		
				}
														
			}

		}

	return mas;

	}

}