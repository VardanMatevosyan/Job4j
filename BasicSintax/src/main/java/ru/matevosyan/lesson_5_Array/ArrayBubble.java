package ru.matevosyan.lesson_5_Array;

public class ArrayBubble{

	public int[] sortArr(int mas[]){

			
		for(int i = 0; i < mas.length; i++){

			for(int j = mas.length-1; j > i; j--){
		
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