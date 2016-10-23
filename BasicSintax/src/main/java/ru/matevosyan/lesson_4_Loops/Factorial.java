package ru.matevosyan.lesson_4_Loops;

public class Factorial{

	public int fact(int n){

		int result = 1;

		if (n==0) {

			return 1;

		}

		for(int i = 1; i <= n; i++ ){

			result = result * i;
 
		}

	return result;

	}

}