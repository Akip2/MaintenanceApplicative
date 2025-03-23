package actions.logged;

import calendar.CalendarManager;
import event.EventType;
import user.AuthManager;

public class AddPeriodicEventAction extends AddEventAction {
    public AddPeriodicEventAction(AuthManager authManager, CalendarManager calendar) {
        super(EventType.PERIODIC, calendar, authManager);
    }

    @Override
    public String getName() {
        return "Ajouter un évènement périodique";
    }
}
