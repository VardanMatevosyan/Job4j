package ru.matevosyan.lesson_3_IfCondition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
//import static org.hamcrest.Matchers.closeTo;

public class MaxTest {

	@Test
	public void whenAddTwoPointsThenReturnPointsDistance() {
		
		//assign
		double distanceFromAToB = 10.0;
		double distanceFromBToC = 4.0;
		double distanceFromCToA = 7.0;
		
		Max maxSide = new Max();

		//act 1.4142135623730951

		double result = maxSide.max(distanceFromAToB, distanceFromBToC, distanceFromCToA);
		assertThat(result, is(10.0) );
		

	}
	
}