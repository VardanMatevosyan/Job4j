package ru.matevosyan.lesson_5_Array;

public class ArrayRotate {

	private int MaxSize = 4;
	private int lastPosition = MaxSize - 1;
	private int level = 0;
	private int totLevel = MaxSize / 2;
	
	public int[][] rotateMatrix(int value[][]){
	
		while (level < totLevel) {
				
			for (int i = level; i < lastPosition; i++) {

					
				//this.swap(value[level][i], value[i][lastPosition]);
				//this.swap(value[level][i], value[lastPosition][lastPosition - i + level]);
				//this.swap(value[level][i], value[lastPosition - i + level][level]);

				this.swap(value, level, i, i, lastPosition);
				this.swap(value, level, i, lastPosition, lastPosition - i + level);
				this.swap(value, level, i , lastPosition - i + level, level);
											
			}

			level++;
			lastPosition--;
		}
		
		return value;
	
	}

	//public void swap(int i, int j){

		//int current = i;
		//i = j;
		//j = current;
	
	//}
	public void swap(int arr[][], int i, int j, int a, int b){

		int current = arr[i][j];
		arr[i][j] = arr[a][b];
		arr[a][b] = current;
	
	}

	
	

}