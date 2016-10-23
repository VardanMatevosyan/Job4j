package ru.matevosyan.lesson_4_Loops;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class FactorialTest {
		
	@Test
	public void whenFactorialNThanGetResultN() {
		
		//assign
		Factorial f = new Factorial();
		int result = f.fact(5);
		
		//act
		assertThat(result, is(120));

	}
}