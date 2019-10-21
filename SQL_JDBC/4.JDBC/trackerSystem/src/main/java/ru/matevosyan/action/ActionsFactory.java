package ru.matevosyan.action;

import java.util.ArrayList;
import java.util.Comparator;

public class ActionsFactory {
    public ActionsFactory() { }

    /**
     * Instance variable for saving all user action.
     * And use it for run specific class, in dependence users selection action.
     */
    private static ArrayList<UserAction> userAction = new ArrayList<>();

    private static void fillAllActions() {
        addAction(new EditItem());
        addAction(new ShowItemComments());
        addAction(new ShowItems());
        addAction(new AddCommentToItem());
        addAction(new DeleteItem());
        addAction(new CloseConnectionToDB());
        addAction(new FindItemByDate());
        addAction(new AddItem());
        addAction(new FindItemById());
        addAction(new FindItemByName());
    }

    public static ArrayList<UserAction> getUserAction() {
        fillAllActions();
        userAction.sort(Comparator.comparingInt(UserAction::key));
        return userAction;
    }

    private static void addAction(BaseAction action) {
        userAction.add(action);
    }
}
