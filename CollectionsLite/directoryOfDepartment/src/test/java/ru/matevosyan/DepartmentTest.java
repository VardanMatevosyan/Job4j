package ru.matevosyan;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Admin on 07.05.2017.
 */
public class DepartmentTest {
    @Test
    public void whenPassDepartmentSerThanCheckSortedDepartment() {

        //assign
        TreeSet<String> depCode = new TreeSet<>();
        Department department = new Department(depCode);

//        TreeSet<String> code = new TreeSet<>();
//        DepartmentCode departmentCode = new DepartmentCode(code);

        List<String> codeExpected = new ArrayList<>();
        Iterator iterator = department.getName().iterator();
        int i = 0;

        //act
//        code.add("K1");
//        code.add("K2");

        depCode.add("K1\\SK1");
        depCode.add("K1\\SK2");
        depCode.add("K1\\SK1\\SSK1");
        depCode.add("K1\\SK1\\SSK2");
        depCode.add("K2\\SK1\\SSK1");
        depCode.add("K2\\SK1\\SSK2");
        depCode.add("K2");

        codeExpected.add("K1");
        codeExpected.add("K1\\SK1");
        codeExpected.add("K1\\SK1\\SSK1");
        codeExpected.add("K1\\SK1\\SSK2");
        codeExpected.add("K1\\SK2");
        codeExpected.add("K2");
        codeExpected.add("K2\\SK1\\SSK1");
        codeExpected.add("K2\\SK1\\SSK2");

//        depCode.addAll(departmentCode.getCode());

        //assert
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(codeExpected.get(i++)));
        }
    }

    @Test
    public void whenPassDepartmentSerThanCheckDescendingSortedDepartment() {
        //assign
        TreeSet<String> depCode = new TreeSet<>();
        Department department = new Department(depCode);

        List<String> codeExpected = new ArrayList<>();
        Iterator iterator = department.getName().iterator();
        int i = 0;

        //act

        depCode.add("K1\\SK1");
        depCode.add("K1\\SK2");
        depCode.add("K1\\SK1\\SSK1");
        depCode.add("K1\\SK1\\SSK2");
        depCode.add("K2\\SK1\\SSK1");
        depCode.add("K2\\SK1\\SSK2");
        depCode.add("K2");


        codeExpected.add("K2\\SK1\\SSK2");
        codeExpected.add("K2\\SK1\\SSK1");
        codeExpected.add("K2");
        codeExpected.add("K1\\SK2");
        codeExpected.add("K1\\SK1\\SSK2");
        codeExpected.add("K1\\SK1\\SSK1");
        codeExpected.add("K1\\SK1");
        codeExpected.add("K1");



        //assert
        while (iterator.hasNext()) {
            department.descendingSort();
            assertThat(iterator.next(), is(codeExpected.get(i++)));
        }
    }
}