package ru.matevosyan;

import java.util.*;

/**
 * User class.
 * Created on 28.04.2017.
 *
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Department implements Comparable<Department> {

    private TreeSet<String> name;

    /**
     * Department class constructor.
     *
     * @param name User name.
     */

    public Department(TreeSet<String> name) {
        this.name = name;
    }

    /**
     * Override compareTo(Department department) method to compare department.
     * @param department is Department object.
     * @return int value.
     */


    @Override
    public int compareTo(Department department) {
        checkDepartmentCode();
        Iterator iterator = department.getName().iterator();
        int check = -1;

        while (iterator.hasNext()) {
            check = this.name.iterator().next().compareTo(String.valueOf(iterator.next()));
        }
        return check;
    }



    public TreeSet<String> getName() {
        return name;
    }

    public void checkDepartmentCode() {
        Iterator<String> iteratorSetString = name.iterator();
        String departmentName;

        while (iteratorSetString.hasNext()) {
            departmentName = iteratorSetString.next();

            String departmentCode = departmentName.substring(0, departmentName.indexOf("\\"));

            if (!this.name.contains(departmentCode)) {
                this.name.add(departmentCode);
            }
        }

    }

    public void descendingSort() {
        checkDepartmentCode();
        this.name = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

    }

}
