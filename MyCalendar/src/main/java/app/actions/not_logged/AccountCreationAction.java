package app.actions.not_logged;

import app.actions.Action;
import app.user.User;
import app.user.UserManager;

import java.util.Scanner;

public class AccountCreationAction implements Action {
    private final UserManager userManager;

    public AccountCreationAction(UserManager userManager) {
        this.userManager = userManager;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nom d'utilisateur: ");
        String name = scanner.nextLine();

        if(!userManager.userExists(name)) {
            System.out.print("Mot de passe: ");
            String pass = scanner.nextLine();

            System.out.print("Répéter mot de passe: ");
            if (scanner.nextLine().equals(pass)) {
                User utilisateur = new User(name, pass);
                userManager.addUser(utilisateur);
            } else {
                System.out.println("Les mots de passes ne correspondent pas...");
            }
        } else {
            System.out.println("Utilisateur déjà existant avec ce nom.");
        }
    }

    public String getName() {
        return "Créer un compte";
    }
}
