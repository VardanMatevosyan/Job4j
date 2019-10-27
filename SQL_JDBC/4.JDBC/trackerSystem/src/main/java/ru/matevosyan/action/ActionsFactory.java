package ru.matevosyan.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActionsFactory {
    public ActionsFactory() { }

    /**
     * Instance variable for saving all user action.
     * And use it for run specific class, in dependence users selection action.
     */
    private static List<UserAction> userAction = new ArrayList<>();

    private static void fillAllActions(){
        userAction = UserActionLoaderHandler.userActions;
    }

    public static List<UserAction> getUserAction() {
        fillAllActions();
        userAction.sort(Comparator.comparingInt(UserAction::key));
        return userAction;
    }

    private static void addAction(BaseAction action) {
        userAction.add(action);
    }
}
