package actions;

import user.AuthManager;
import user.UserManager;

import java.util.Arrays;
import java.util.Scanner;

public class ActionManager {
    private final MenuAction firstMenu;
    private MenuAction secondMenu;

    private final AuthManager authManager;
    private final Scanner scanner = new Scanner(System.in);

    public ActionManager(UserManager userManager, AuthManager authManager) {
        this.authManager = authManager;

        firstMenu = new MenuAction(Arrays.asList(
                new AccountCreationAction(userManager),
                new LoginAction(userManager, authManager)
        ));
    }

    public void askChoice() {
        MenuAction currentMenu = this.authManager.isLoggedIn() ? this.secondMenu : this.firstMenu;

        System.out.println(currentMenu);
        System.out.println("Choix : ");

        int choiceId = scanner.nextInt();
        currentMenu.executeAction(choiceId);
    }
}
