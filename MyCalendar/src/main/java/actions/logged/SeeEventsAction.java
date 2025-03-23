package actions.logged;

import actions.ActionManager;
import actions.ChangeMenuAction;
import menu.MenuType;

public class SeeEventsAction extends ChangeMenuAction {
    public SeeEventsAction(MenuType changeMenu, ActionManager actionManager) {
        super(changeMenu, actionManager);
    }

    @Override
    public String getName() {
        return "Voir les événements";
    }
}
