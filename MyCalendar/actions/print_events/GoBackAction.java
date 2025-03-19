package actions.print_events;

import actions.ActionManager;
import actions.ChangeMenuAction;

public class GoBackAction extends ChangeMenuAction {
    public GoBackAction(int changeMenu, ActionManager actionManager) {
        super(changeMenu, actionManager);
    }

    @Override
    public String getName() {
        return "Retour";
    }
}
