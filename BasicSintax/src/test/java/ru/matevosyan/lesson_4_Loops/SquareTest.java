package ru.matevosyan.lesson_4_Loops;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.closeTo;

public class SquareTest {
		
	@Test
	public void whenAddABCandXThanGetResultY(){

		//assign
		Square y = new Square(1f,2f,3f);

		//act
		float result = y.calculate(4);
		assertThat(result, is(27f));
	
	}

	// @Test
	// public void whenAddStartFinishandStepandXThanGetResultY(){

		//assign
		//Square y = new Square(1f,2f,3f);
			
		//act
		//float result = y.show(0, 5, 1);
		//assertThat(result, is(27f));

		//if x=0,1,2,3,4------y=3,6,11,18,27

//	}
}


