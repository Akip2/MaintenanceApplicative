package app.menu;

import app.actions.Action;

import java.text.MessageFormat;
import java.util.List;

public abstract class MenuAction {
    private final List<Action> actions;

    public MenuAction(List<Action> actions) {
        this.actions = actions;
    }

    public void executeAction(int choiceId) {
        this.actions.get(choiceId).execute();
    }

    public abstract String getIntro();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(MessageFormat.format("{0}\n", getIntro()));
        for(int i=1; i<actions.size()+1; i++) {
            sb.append("%d - %s\n".formatted(i, actions.get(i-1).getName()));
        }

        return sb.toString();
    }
}
