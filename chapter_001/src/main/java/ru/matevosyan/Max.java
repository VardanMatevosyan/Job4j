package ru.matevosyan;

public class Max {

	public double max (double ... nums){

	double maximum = 0;

	for(double x : nums){

		if(x >= nums[0] ){
 
			maximum = x;
			
		}

	}

	return maximum;

	}
}