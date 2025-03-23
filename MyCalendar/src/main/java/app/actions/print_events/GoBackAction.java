package app.actions.print_events;

import app.actions.ActionManager;
import app.actions.ChangeMenuAction;
import app.menu.MenuType;

public class GoBackAction extends ChangeMenuAction {
    public GoBackAction(MenuType changeMenu, ActionManager actionManager) {
        super(changeMenu, actionManager);
    }

    @Override
    public String getName() {
        return "Retour";
    }
}
