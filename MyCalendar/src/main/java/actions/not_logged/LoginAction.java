package actions.not_logged;

import actions.Action;
import actions.ActionManager;
import menu.MenuType;
import user.AuthManager;
import user.UserManager;

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
                actionManager.setMenuIndex(MenuType.LOGGED);
            }
        } else {
            System.out.println("Utilisateur inexistant.");
        }
    }

    public String getName() {
        return "Se connecter";
    }
}
