package ru.matevosyan;

import org.junit.Test;

import java.util.TreeSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Admin on 07.05.2017.
 */
public class DepartmentTest {
    @Test
            public void whenPassDepartmentSerThanCheckSortedDepartment() {
        TreeSet<String> depCode = new TreeSet<>();
        Department department = new Department(depCode);

        depCode.add("K1\\SK1");
        depCode.add("K1\\SK2");
        depCode.add("K1\\SK1\\SSK1");
        depCode.add("K1\\SK1\\SSK2");
        depCode.add("K2\\SK1\\SSK1");
        depCode.add("K2\\SK1\\SSK2");
        depCode.add("K2");

        assertThat(department.getName().first(), is("K1\\SK1"));
        System.out.printf("%s", department.getName());
    }
}