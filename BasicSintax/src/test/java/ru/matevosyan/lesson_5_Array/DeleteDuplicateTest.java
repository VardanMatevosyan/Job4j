package ru.matevosyan.lesson_5_Array;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class DeleteDuplicateTest {

	@Test
	public void whenDuplicateWasFindThenDeleteIt(){

		//assign
		String[] mas = {"First","Find","Fox","First","Fox","S","S","M","M"};
		String[] masrotate = {"First","Find","Fox","S", "M"};

		DeleteDuplicate arr = new DeleteDuplicate();
		String[] ar = arr.deleteArrDuplicate(mas);
		
		//act
		assertThat(ar, is(masrotate));

	}

}