package actions.print_events;

import actions.ActionManager;
import actions.ChangeMenuAction;
import menu.MenuType;

public class GoBackAction extends ChangeMenuAction {
    public GoBackAction(MenuType changeMenu, ActionManager actionManager) {
        super(changeMenu, actionManager);
    }

    @Override
    public String getName() {
        return "Retour";
    }
}
