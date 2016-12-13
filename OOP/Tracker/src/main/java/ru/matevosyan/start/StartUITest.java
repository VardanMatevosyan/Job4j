package ru.matevosyan.start;

import ru.matevosyan.models.Item;

/**
 * Class StartUITest created for testing.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

//System.getProperty("line.separator");
public class StartUITest {

    private Input input;
    private Tracker tracker = new Tracker();

    public StartUITest(Input input) {
        this.input = input;
    }

    public void initTest() {

            String name = "Task's name";
            String description = "Task's description";
            this.tracker.add(new Item(name, description));

    }
    public static void main(String[] args) {

        /**
         * Instance variable StartUI and call the method init.
         */

        Input input = new StubInput(new String[] {"Create stub Task "});
        new StartUITest(input).initTest();

    }

}
