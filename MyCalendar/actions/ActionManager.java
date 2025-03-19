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
                new LoginAction(userManager, authManager),
                new AccountCreationAction(userManager)
        ));
    }

    public void askChoice() {
        MenuAction currentMenu;

        if(this.authManager.isLoggedIn()) {
            currentMenu = this.secondMenu;
        } else {
            System.out.println("  _____         _                   _                __  __");
            System.out.println(" / ____|       | |                 | |              |  \\/  |");
            System.out.println(
                    "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
            System.out.println(
                    "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
            System.out.println(
                    "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
            System.out.println(
                    " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
            System.out.println(
                    "                                                                                   __/ |");
            System.out.println(
                    "                                                                                  |___/");

            currentMenu = this.firstMenu;
        }

        System.out.println(currentMenu);
        System.out.println("Choix : ");

        int choiceId = scanner.nextInt();
        currentMenu.executeAction(choiceId - 1);
    }
}
