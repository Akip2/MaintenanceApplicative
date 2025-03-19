import actions.AccountCreationAction;
import actions.LoginAction;
import menu.LoggedMenu;
import menu.MenuAction;
import menu.NotLoggedMenu;
import user.AuthManager;
import user.UserManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ActionManager {
    private final MenuAction notLoggedMenu;
    private MenuAction loggedMenu;

    private final AuthManager authManager;
    private final Scanner scanner = new Scanner(System.in);

    public ActionManager(UserManager userManager, AuthManager authManager) {
        this.authManager = authManager;

        notLoggedMenu = new NotLoggedMenu(Arrays.asList(
                new LoginAction(userManager, authManager),
                new AccountCreationAction(userManager)
        ));

        loggedMenu = new LoggedMenu(new ArrayList<>(Arrays.asList(

        )), authManager);
    }

    public void askChoice() {
        MenuAction currentMenu;

        if(this.authManager.isLoggedIn()) {
            currentMenu = this.loggedMenu;
        } else {
            currentMenu = this.notLoggedMenu;
        }

        System.out.println(currentMenu);
        System.out.println("Choix : ");

        int choiceId = scanner.nextInt();
        currentMenu.executeAction(choiceId - 1);
    }
}
