package ru.matevosyan;

import java.util.TreeSet;

/**
 * Created by Admin on 07.05.2017.
 */
public class DepartmentCode {
    private final TreeSet<String> code;

    public DepartmentCode(final TreeSet<String> code) {
        this.code = code;
    }

    public TreeSet<String> getCode() {
        return code;
    }
}
