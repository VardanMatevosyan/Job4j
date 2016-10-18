package ru.matevosyan;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxTest {

	@Test
	public void whenAddTwoPointsThenReturnPointsDistance() {
		
		//assign
		double distanceFromAToB = 10d;
		double distanceFromBToC = 7d;
		double distanceFromCToA = 4d;
		
		Max maxSide = new Max();

		//act 1.4142135623730951

		double result = maxSide.max(distanceFromAToB, distanceFromBToC, distanceFromCToA);
		assertThat(result, is(10d));
		

	}
	
}