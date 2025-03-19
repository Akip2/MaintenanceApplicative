package actions;

import actions.logged.*;
import actions.not_logged.AccountCreationAction;
import actions.not_logged.LoginAction;
import actions.print_events.*;
import calendar.CalendarManager;
import menu.LoggedMenu;
import menu.MenuAction;
import menu.NotLoggedMenu;
import menu.PrintEventsMenu;
import user.AuthManager;
import user.UserManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ActionManager {
    public final static int NOT_LOGGED_MENU = 0;
    public final static int LOGGED_MENU = 1;
    public final static int PRINT_MENU = 2;

    private final List<MenuAction> menus;
    private int menuIndex;
    private boolean running;

    private final Scanner scanner = new Scanner(System.in);

    public ActionManager(UserManager userManager, AuthManager authManager, CalendarManager calendar) {
        running = true;

        menus = new ArrayList<>();
        menuIndex = 0;

        MenuAction notLoggedMenu = new NotLoggedMenu(Arrays.asList(
                new LoginAction(userManager, authManager, this),
                new AccountCreationAction(userManager)
        ));
        menus.add(notLoggedMenu);

        MenuAction loggedMenu = new LoggedMenu(new ArrayList<>(Arrays.asList(
                new SeeEventsAction(PRINT_MENU, this),
                new AddPersonalEventAction(authManager, calendar),
                new AddMeetingEventAction(authManager, calendar, userManager),
                new AddPeriodicEventAction(authManager, calendar),
                new DisconnectAction(authManager, this)
        )), authManager);
        menus.add(loggedMenu);

        MenuAction printMenu = new PrintEventsMenu(new ArrayList<>(Arrays.asList(
                new PrintAllEventsAction(calendar),
                new PrintEventsMonthAction(calendar),
                new PrintEventsWeekAction(calendar),
                new PrintEventsDayAction(calendar),
                new GoBackAction(LOGGED_MENU, this)
        )), this);
        menus.add(printMenu);
    }

    public void setMenuIndex(int menuIndex) {
        this.menuIndex = menuIndex;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean continues() {
        return running;
    }

    public void askChoice() {
        MenuAction currentMenu = this.menus.get(menuIndex);

        System.out.println(currentMenu);
        System.out.print("Choix : ");

        int choiceId = scanner.nextInt();
        currentMenu.executeAction(choiceId - 1);
    }
}
