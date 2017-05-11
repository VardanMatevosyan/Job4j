package ru.matevosyan;

import java.util.*;

/**
 * Department class to sort treeSet.
 * Created on 11.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Department implements Comparable<Department> {

    private TreeSet<String> name;

    /**
     * Department class constructor.
     * @param name Department codes name.
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
        Iterator iterator = department.getName().iterator();
        int check = -1;

        while (iterator.hasNext()) {
            check = this.name.iterator().next().compareTo(String.valueOf(iterator.next()));
        }
        return check;
    }

    /**
     * Getter for name.
     * @return name set of department code.
     */

    public TreeSet<String> getName() {
        return name;
    }

    /**
     * checkDepartmentCode() was created for add key value on the top of hierarchy.
     * @return ArrayList<String>.
     */

    public ArrayList<String> checkDepartmentCode() {
        Iterator<String> iteratorSetString = name.iterator();
        String departmentName;

        ArrayList<String> list = new ArrayList<>();
        while (iteratorSetString.hasNext()) {
            departmentName = iteratorSetString.next();
            if (departmentName.contains("\\")) {
                String departmentCode = departmentName.substring(0, departmentName.indexOf("\\"));
                if (!this.name.contains(departmentCode) ) {
                    list.add(departmentCode);
                }
            }
        }
        return list;
    }

}
