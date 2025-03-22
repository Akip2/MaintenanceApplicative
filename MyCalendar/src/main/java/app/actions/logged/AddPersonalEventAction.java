package app.actions.logged;

import app.calendar.CalendarManager;
import app.event.EventType;
import app.user.AuthManager;

public class AddPersonalEventAction extends AddEventAction {
    public AddPersonalEventAction(AuthManager authManager, CalendarManager calendar) {
        super(EventType.PERSONAL, calendar, authManager);
    }

    @Override
    public String getName() {
        return "Ajouter un rendez-vous perso";
    }
}
