package app.actions.logged;

import app.actions.ActionManager;
import app.actions.ChangeMenuAction;
import app.menu.MenuType;

public class SeeEventsAction extends ChangeMenuAction {
    public SeeEventsAction(MenuType changeMenu, ActionManager actionManager) {
        super(changeMenu, actionManager);
    }

    @Override
    public String getName() {
        return "Voir les événements";
    }
}
