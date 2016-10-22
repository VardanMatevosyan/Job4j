package ru.matevosyan.lesson_3_IfCondition;



public class Triangle {

	public Point a;
	public Point b;
	public Point c;

	public Triangle(Point a, Point b, Point c) {

		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double area() {

		//calculate the triangle area
		
		double distanceFromAToB = a.distanceTo(b);
		double distanceFromBToC = b.distanceTo(c);
		double distanceFromAToC = a.distanceTo(c);

		double p = (distanceFromAToB + distanceFromBToC + distanceFromAToC)/2;
		double triangleSquare = Math.sqrt(p * (p - distanceFromAToB) * (p - distanceFromBToC) * (p - distanceFromAToC)); 
	 
		if (distanceFromAToB > (distanceFromBToC + distanceFromAToC) || distanceFromBToC > (distanceFromAToC + distanceFromAToB)
		|| distanceFromAToC > (distanceFromAToB + distanceFromBToC ) ) {


			throw new ArithmeticException("Does not exist");

		} 
		
		return triangleSquare;

		
	}
}