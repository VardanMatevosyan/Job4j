package ru.matevosyan;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * DepartmentTest class for testing ascending and descending sort.
 * Created on 11.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DepartmentTest {

    /**
     * whenPassDepartmentSerThanCheckSortedDepartment() was created to test ascend sorting for {@link Department#name}.
     */

    @Test
    public void whenPassDepartmentSerThanCheckSortedDepartment() {

        //assign
        TreeSet<String> depCode = new TreeSet<>();
        List<String> codeExpected = new ArrayList<>();
        Department department = new Department(depCode);
        int i = 0;

        //act
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

        depCode.addAll(department.checkDepartmentCode());

        //assert
        for (String actualDepartmentCode : department.getName()) {
            assertThat(actualDepartmentCode, is(codeExpected.get(i++)));
        }
    }

    /**
     * whenPassDepartmentSerThanCheckDescendingSortedDepartment() was created to test descend sorting.
     * for {@link Department#name}.
     */

    @Test
    public void whenPassDepartmentSerThanCheckDescendingSortedDepartment() {
        //assign
        TreeSet<String> depCode = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        List<String> codeExpected = new ArrayList<>();
        Department department = new Department(depCode);

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

        depCode.addAll(department.checkDepartmentCode());
        //assert

        for (String actualDepartmentCode : depCode) {
            assertThat(actualDepartmentCode, is(codeExpected.get(i++)));
        }
    }
}