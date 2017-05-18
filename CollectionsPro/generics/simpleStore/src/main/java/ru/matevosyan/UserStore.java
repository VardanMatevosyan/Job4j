package ru.matevosyan;

/**
 * UserStore class to store the user.
 * Created on 16.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class UserStore extends MajorStore<User> {

    /**
     * Constructor to invoke parent constructor.
     * @param userSimpleArray SimpleArray storage.
     */

    public UserStore(SimpleArray<Base> userSimpleArray) {
        super(userSimpleArray);
    }
}
