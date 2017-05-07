package ru.matevosyan;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * User class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Department implements Comparable<Department> {

    private final TreeSet<String> name;

    /**
     * Department class constructor.
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
}
