package ru.matevosyan;



public class Max {

	public double max (double distanceFromAToB, double distanceFromBToC, double distanceFromAToC){


		if (distanceFromAToB > distanceFromBToC && distanceFromAToB > distanceFromAToC){
		
			return distanceFromAToB;

		} 

		else if (distanceFromBToC > distanceFromAToB && distanceFromBToC > distanceFromAToC){

			return distanceFromBToC;

		}
		else {
			
			return distanceFromAToC;
			
		}		

	}
}