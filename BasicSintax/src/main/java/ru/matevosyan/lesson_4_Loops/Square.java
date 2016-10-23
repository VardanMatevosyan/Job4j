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
	
		return (float)(a*Math.pow(x,2)) + (b * x) + c;
	
	}

	public void show(int start, int finish, int step) {
		
		Square s = new Square(1f,2f,3f);

		while(start < finish) {

			System.out.println(s.calculate(start));
			start += step;

		}

	}

}