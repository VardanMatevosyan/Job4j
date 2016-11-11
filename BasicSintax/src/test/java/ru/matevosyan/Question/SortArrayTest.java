package ru.matevosyan;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static junit.framework.Assert.assertEquals;


public class SortArrayTest {
		
	@Test
	public void whenFindTwoArrayThanSortThirdArray() {
		
		//assign
		SortArray mas = new SortArray();
		int[] arrFirst = {0,1,2,4,6};
		int[] arrSecond = {1,3,5,7,8};
		int[] thirdArr = {0,1,1,2,3,4,5,6,7,8};
		int[] thirdArrayResult = mas.sort(arrFirst, arrSecond);
				
		//act
		assertThat(thirdArrayResult, is(thirdArr));
	}
}