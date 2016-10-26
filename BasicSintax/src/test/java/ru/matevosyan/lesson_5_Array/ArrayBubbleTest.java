package ru.matevosyan.lesson_5_Array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class ArrayBubbleTest {
		
	@Test
	public void whenSortNThanGetResult() {
		
		//assign
		ArrayBubble arr = new ArrayBubble();
		int[] masToBubbleSort = {1,5,3,4,2};
		int[] mas = {1,2,3,4,5};
		
		//act
		assertArrayEquals(arr.sort(masToBubbleSort), mas);

	}
}