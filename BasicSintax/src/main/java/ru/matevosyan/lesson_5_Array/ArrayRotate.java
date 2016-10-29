package ru.matevosyan.lesson_5_Array;

public class ArrayRotate {

	
	public int[][] rotateMatrix(int value[][]){


	int MaxSize = 4;
	int lastPosition = MaxSize - 1;
	int level = 0;
	int totLevel = MaxSize / 2;
	
		while (level < totLevel) {
				
			for (int i = level; i < lastPosition; i++) {

				this.swap(value, level, i, i, lastPosition);
				this.swap(value, level, i, lastPosition, lastPosition - i + level);
				this.swap(value, level, i , lastPosition - i + level, level);
											
			}

			level++;
			lastPosition--;
		}
		
		return value;
	
	}

	public void swap(int arr[][], int i, int j, int a, int b){

		int current = arr[i][j];
		arr[i][j] = arr[a][b];
		arr[a][b] = current;
	
	}

	
	

}