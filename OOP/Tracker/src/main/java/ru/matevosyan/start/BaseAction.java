package ru.matevosyan.start;

/**
 * Interface UserAction.
 * Created on 08.01.2017.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public abstract class BaseAction implements UserAction {

    private int key;
    private String name;

    public BaseAction(int key, String name) {
        this.name = name;
        this.key = key;
    }

    @Override
    public int key() {
        return this.key;
    }

    public abstract void execute(Input input, Tracker tracker);

    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }

}
