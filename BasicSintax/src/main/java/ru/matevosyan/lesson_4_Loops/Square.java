package ru.matevosyan.lesson_4_Loops;

public class Square{

	private float a;
	private float b;
	private float c;
	
	public Square (float a, float b, float c) {
		
	this.a = a;
	this.b = b;
	this.c = c;	
		
	}

	public  float calculate(int x) {
	
		return (float)(this.a*Math.pow(x,2)) + (this.b * x) + this.c;
	
	}

	public void show(int start, int finish, int step) {
		
		while(start < finish) {

			System.out.println(this.calculate(start));
			start += step;

		}

	}

}