package ru.matevosyan.test;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static junit.framework.Assert.assertEquals;


public class ControlTest {
		
	@Test
	public void whenFindSubThanGetResult() {
		
		//assign
		Control line = new Control();
		String s = "Array";
		String sub = "Ar";
		String result = line.subString(s,sub);
				
		//act
		assertThat(result, is(sub));
	}
}