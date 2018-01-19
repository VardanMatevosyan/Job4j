package ru.matevosyan.action;

import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;

/**
 * Interface UserAction.
 * Created on 08.01.2017.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public abstract class BaseAction implements UserAction {

    /**
     * Key use to return userAction keys.
     */

    private int key;

    /**
     * Name use to return menu points.
     */

    private String name;

    /**
     * BaseAction constructor to assign the variable value.
     * @param key use to assign userAction key to return it.
     * @param name use to assign userAction menu point to return it.
     */

    public BaseAction(int key, String name) {
        this.name = name;
        this.key = key;
    }

    @Override
    public int key() {
        return this.key;
    }

    /**
     * Abstract method which is implements in MenuTracker class.
     * @param input it is input state
     * @param tracker it is the class that we can use for work with items
     */

    public abstract void execute(Input input, Tracker tracker);

    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }

}
