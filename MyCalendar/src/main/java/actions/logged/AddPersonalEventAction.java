package actions.logged;

import calendar.CalendarManager;
import event.EventType;
import user.AuthManager;

public class AddPersonalEventAction extends AddEventAction {
    public AddPersonalEventAction(AuthManager authManager, CalendarManager calendar) {
        super(EventType.PERSONAL, calendar, authManager);
    }

    @Override
    public String getName() {
        return "Ajouter un rendez-vous perso";
    }
}
