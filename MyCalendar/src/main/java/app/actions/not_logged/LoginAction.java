package app.actions.not_logged;

import app.actions.Action;
import app.actions.ActionManager;
import app.user.AuthManager;
import app.user.UserManager;

import java.util.Scanner;

public class LoginAction implements Action {
    private final UserManager userManager;
    private final AuthManager authManager;
    private final ActionManager actionManager;

    public LoginAction(UserManager userManager, AuthManager authManager, ActionManager actionManager) {
        this.userManager = userManager;
        this.authManager = authManager;
        this.actionManager = actionManager;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nom d'utilisateur: ");
        String name = scanner.nextLine();

        if (userManager.userExists(name)) {
            System.out.print("Mot de passe: ");
            String pass = scanner.nextLine();

            if (!authManager.authenticate(name, pass)) {
                System.out.println("Mot de passe invalide.");
            } else {
                actionManager.setMenuIndex(ActionManager.LOGGED_MENU);
            }
        } else {
            System.out.println("Utilisateur inexistant.");
        }
    }

    public String getName() {
        return "Se connecter";
    }
}
