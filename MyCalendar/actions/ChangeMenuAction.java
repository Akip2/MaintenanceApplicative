package actions;

public abstract class ChangeMenuAction implements Action {
    private final int changeMenu;
    private final ActionManager actionManager;

    public ChangeMenuAction(int changeMenu, ActionManager actionManager) {
        this.changeMenu = changeMenu;
        this.actionManager = actionManager;
    }

    @Override
    public void execute() {
        actionManager.setMenuIndex(changeMenu);
    }
}
