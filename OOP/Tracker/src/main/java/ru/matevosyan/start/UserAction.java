package ru.matevosyan.start;

/**
 * Created by Admin on 21.12.2016.
 */
public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
