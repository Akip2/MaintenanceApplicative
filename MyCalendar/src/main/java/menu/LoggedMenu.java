package menu;

import actions.Action;
import user.AuthManager;

import java.util.List;

public class LoggedMenu extends MenuAction {
    private AuthManager authManager;
    public LoggedMenu(List<Action> actions, AuthManager authManager) {
        super(actions);
        this.authManager = authManager;
    }

    @Override
    public String getIntro() {
        return "\nBonjour, %s\n=== Menu Gestionnaire d'Événements ===\n".formatted(authManager.getLoggedInUser());
    }
}