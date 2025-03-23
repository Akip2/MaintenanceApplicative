package menu;

import actions.Action;
import actions.ActionManager;

import java.util.List;

public class PrintEventsMenu extends MenuAction {
    private final ActionManager actionManager;

    public PrintEventsMenu(List<Action> actions, ActionManager actionManager) {
        super(actions);
        this.actionManager = actionManager;
    }

    @Override
    public String getIntro() {
        return "\n=== Menu de visualisation d'Événements ===\n";
    }

    @Override
    public void executeAction(int choiceId) {
        super.executeAction(choiceId);
        actionManager.setMenuIndex(MenuType.LOGGED);
    }
}