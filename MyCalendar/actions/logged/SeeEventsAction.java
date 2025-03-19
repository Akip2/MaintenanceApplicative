package actions.logged;

import actions.ActionManager;
import actions.ChangeMenuAction;

public class SeeEventsAction extends ChangeMenuAction {
    public SeeEventsAction(int changeMenu, ActionManager actionManager) {
        super(changeMenu, actionManager);
    }

    @Override
    public String getName() {
        return "Voir les événements";
    }
}
