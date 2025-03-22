package app.actions.print_events;

import app.actions.ActionManager;
import app.actions.ChangeMenuAction;

public class GoBackAction extends ChangeMenuAction {
    public GoBackAction(int changeMenu, ActionManager actionManager) {
        super(changeMenu, actionManager);
    }

    @Override
    public String getName() {
        return "Retour";
    }
}
