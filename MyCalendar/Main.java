import actions.ActionManager;
import calendar.CalendarManager;
import user.AuthManager;
import user.UserManager;

public static void main() {
    CalendarManager calendar = new CalendarManager();

    UserManager userManager = new UserManager();
    AuthManager authManager = new AuthManager(userManager);

    ActionManager actionManager = new ActionManager(userManager, authManager, calendar);

    while (actionManager.continues()) {
        actionManager.askChoice();
    }
}