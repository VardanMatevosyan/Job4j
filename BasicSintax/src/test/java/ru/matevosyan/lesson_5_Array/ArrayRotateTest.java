package ru.matevosyan.lesson_5_Array;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class ArrayRotateTest {

	@Test
	public void whenArrayIsAssignThenRotateIt(){

		//assign
		int[][] mas = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int[][] masrotate = {{13,9,5,1},{14,10,6,2},{15,11,7,3},{16,12,8,4}};
		ArrayRotate arr = new ArrayRotate();
		int[][] ar = arr.rotateMatrix(mas);
		
		//act
		assertThat(ar, is(masrotate));

	


	}

}