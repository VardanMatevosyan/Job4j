package ru.matevosyan.lesson_5_Array;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class DeleteDuplicateTest {

	@Test
	public void whenDuplicateWasFindThenDeleteIt(){

		//assign
		String[] mas = {"First","Find","Fox","First","Fox","S","S"};
		String[] masrotate = {"First","Find","Fox","null","null","S","null"};

		DeleteDuplicate arr = new DeleteDuplicate();
		String[] ar = arr.searchDuplicate(mas);
		
		//act
		assertThat(ar, is(masrotate));

	}

}