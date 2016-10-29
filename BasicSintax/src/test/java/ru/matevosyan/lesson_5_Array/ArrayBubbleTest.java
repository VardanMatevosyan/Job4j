package ru.matevosyan.lesson_5_Array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static junit.framework.Assert.assertEquals;


public class ArrayBubbleTest {
		
	@Test
	public void whenSortNThanGetResult() {
		
		//assign
		ArrayBubble arr = new ArrayBubble();
		int[] masToBubbleSort = {1,3,2,5,4};
		int[] mass = {1,2,3,4,5};
		int[] masexpected = arr.sortArr(masToBubbleSort);
		
		//act
		assertThat(masexpected, is(mass));
	}
}