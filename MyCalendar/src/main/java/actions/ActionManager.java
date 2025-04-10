package actions;

import actions.logged.*;
import actions.not_logged.AccountCreationAction;
import actions.not_logged.LoginAction;
import actions.print_events.*;
import calendar.CalendarManager;
import menu.*;
import user.AuthManager;
import user.UserManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ActionManager {
    private final List<MenuAction> menus;
    private MenuType menuType;
    private boolean running;

    private final Scanner scanner = new Scanner(System.in);

    public ActionManager(UserManager userManager, AuthManager authManager, CalendarManager calendar) {
        running = true;

        menus = new ArrayList<>();
        menuType = MenuType.NOT_LOGGED;

        MenuAction notLoggedMenu = new NotLoggedMenu(Arrays.asList(
                new LoginAction(userManager, authManager, this),
                new AccountCreationAction(userManager)
        ));
        menus.add(notLoggedMenu);

        MenuAction loggedMenu = new LoggedMenu(new ArrayList<>(Arrays.asList(
                new SeeEventsAction(MenuType.PRINT_EVENTS, this),
                new AddPersonalEventAction(authManager, calendar),
                new AddMeetingEventAction(authManager, calendar, userManager),
                new AddPeriodicEventAction(authManager, calendar),
                new AddFormationEventAction(authManager, calendar, userManager),
                new DeleteEventAction(calendar),
                new DisconnectAction(authManager, this)
        )), authManager);
        menus.add(loggedMenu);

        MenuAction printMenu = new PrintEventsMenu(new ArrayList<>(Arrays.asList(
                new PrintAllEventsAction(calendar),
                new PrintEventsMonthAction(calendar),
                new PrintEventsWeekAction(calendar),
                new PrintEventsDayAction(calendar),
                new GoBackAction(MenuType.LOGGED, this)
        )), this);
        menus.add(printMenu);
    }

    public void setMenuIndex(MenuType menuType) {
        this.menuType = menuType;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean continues() {
        return running;
    }

    public void askChoice() {
        MenuAction currentMenu = this.menus.get(menuType.ordinal());

        System.out.println(currentMenu);
        System.out.print("Choix : ");

        int choiceId = scanner.nextInt();
        currentMenu.executeAction(choiceId - 1);
    }
}
