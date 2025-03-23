package actions;

import menu.MenuType;

public abstract class ChangeMenuAction implements Action {
    private final MenuType changeMenu;
    private final ActionManager actionManager;

        public ChangeMenuAction(MenuType changeMenu, ActionManager actionManager) {
        this.changeMenu = changeMenu;
        this.actionManager = actionManager;
    }

    @Override
    public void execute() {
        actionManager.setMenuIndex(changeMenu);
    }
}
