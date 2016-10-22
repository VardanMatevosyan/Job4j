package ru.matevosyan.lesson_2_Types;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {

	@Test
	public void whenAddTwoNumbersThenReturnResultOfTwoNumbers() {
		//assign
		Calculator calc = new Calculator();
		//act
		double result = calc.add(10.0,2.0);
		assertThat(result, is(12.0));
		

	}
	
	@Test
	public void whenSubTwoNumbersThenReturnResultOfTwoNumbers() {
		//assign
		Calculator calc = new Calculator();
		//act
		double result = calc.substruct(10.0,2.0);
		assertThat(result, is(8.0));
	

	}

	@Test
	public void whenDivTwoNumbersThenReturnResultOfTwoNumbers() {
		//assign
		Calculator calc = new Calculator();
		//act
		double result = calc.div(10.0,2.0);
		assertThat(result, is(5.0));
		

	}

	@Test
	public void whenMulTwoNumbersThenReturnResultOfTwoNumbers() {
		//assign
		Calculator calc = new Calculator();
		//act
		double result = calc.multiple(10.0,2.0);
		assertThat(result, is(20.0));
		

	} 

}