package app.actions.logged;

import app.actions.Action;
import app.actions.ActionManager;
import app.menu.MenuType;
import app.user.AuthManager;

import java.util.Scanner;

public class DisconnectAction implements Action {
    ActionManager actionManager;
    AuthManager authManager;

    public DisconnectAction(AuthManager authManager, ActionManager actionManager) {
        this.actionManager = actionManager;
        this.authManager = authManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Déconnexion ! Voulez-vous continuer ? (oui/non)");

        authManager.disconnect();
        actionManager.setMenuIndex(MenuType.NOT_LOGGED);
        actionManager.setRunning(scanner.nextLine().trim().equalsIgnoreCase("oui"));
    }

    @Override
    public String getName() {
        return "Se deconnecter";
    }
}
