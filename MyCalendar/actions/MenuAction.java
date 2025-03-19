package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAction {
    private final List<Action> actions;

    public MenuAction(List<Action> actions) {
        this.actions = actions;
    }

    public void executeAction(int choiceId) {
        this.actions.get(choiceId).execute();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<actions.size()+1; i++) {
            sb.append("%d - %s\n".formatted(i, actions.get(i-1).getName()));
        }

        return sb.toString();
    }
}
