package ru.matevosyan.lesson_5_Array;

public class ArrayBubble{

	public int[] sort(int mas[]){

			
		for(int i = 0; i < mas.length-1; i++){

			for(int j = mas.length-1; j > 1; i--){
		
				if(mas[j] < mas[j-1]){

					int current = mas[j];
					mas[j] = mas[j - 1];
					mas[j - 1] = current;
				
				}
			}
		}

	return mas;

	}

}