import app.actions.ActionManager;
import app.calendar.CalendarManager;
import app.user.AuthManager;
import app.user.UserManager;

public static void main() {
    CalendarManager calendar = new CalendarManager();

    UserManager userManager = new UserManager();
    AuthManager authManager = new AuthManager(userManager);

    ActionManager actionManager = new ActionManager(userManager, authManager, calendar);

    while (actionManager.continues()) {
        actionManager.askChoice();
    }
}