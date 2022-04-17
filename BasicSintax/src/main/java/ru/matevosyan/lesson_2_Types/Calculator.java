package ru.matevosyan.lesson_2_Types;

public class Calculator {

	private double result;

	public double add(double first, double second){
		this.result = first + second;
		return this.result;
				
	}

	public double substruct(double first, double second){
		this.result = first - second;
		return this.result;
	}

	public double div(double first, double second){
		this.result = first / second;
		return this.result;
		
	}

	public double multiple(double first, double second){
		this.result = first * second;
		return this.result;
						
	}
}
