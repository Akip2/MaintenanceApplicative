import actions.ActionManager;
import calendar.CalendarManager;
import user.AuthManager;
import user.User;
import user.UserManager;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();

        UserManager userManager = new UserManager();
        userManager.addUser(new User("Pierre", "KiRouhl"));
        userManager.addUser(new User("Roger", "Chat"));

        AuthManager authManager = new AuthManager(userManager);

        ActionManager actionManager = new ActionManager(userManager, authManager, calendar);

        while (actionManager.continues()) {
            actionManager.askChoice();
        }
    }
}