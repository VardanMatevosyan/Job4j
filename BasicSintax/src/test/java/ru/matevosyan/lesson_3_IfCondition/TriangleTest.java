package ru.matevosyan.lesson_3_IfCondition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.closeTo;

public class TriangleTest {

	@Test
	public void whenAddThreeParametersThenReturnOneArea() {
		//------assign

		Point pointA = new Point(1.0,10.0);
		Point pointB = new Point(2.0,2.0);
		Point pointC = new Point(4.0,4.0);
		
		
		Triangle triag = new Triangle(pointA, pointB, pointC);
		

 


		//------act

		//6.708203932499369  from Point(1,10) to POINT(4,4)
		//8.06225774829855  from Point(1,10) to POINT(2,2)
		//2.8284271247461903  from Point(2,2) to POINT(4,4)

		//result should be 8.999999999999993

		double result = triag.area();
		assertThat(result, is(closeTo(9, 0.000000000000070)));

		
		
	}
	
}