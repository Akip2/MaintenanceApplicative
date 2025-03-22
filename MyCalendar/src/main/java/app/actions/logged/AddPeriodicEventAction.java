package app.actions.logged;

import app.calendar.CalendarManager;
import app.event.EventType;
import app.user.AuthManager;

public class AddPeriodicEventAction extends AddEventAction {
    public AddPeriodicEventAction(AuthManager authManager, CalendarManager calendar) {
        super(EventType.PERIODIC, calendar, authManager);
    }

    @Override
    public String getName() {
        return "Ajouter un évènement périodique";
    }
}
