package app.actions.logged;

import app.actions.ActionManager;
import app.actions.ChangeMenuAction;

public class SeeEventsAction extends ChangeMenuAction {
    public SeeEventsAction(int changeMenu, ActionManager actionManager) {
        super(changeMenu, actionManager);
    }

    @Override
    public String getName() {
        return "Voir les événements";
    }
}
