package actions.logged;

import actions.Action;
import calendar.CalendarManager;
import event.Event;
import event.EventType;
import event.Events;
import user.AuthManager;

public abstract class AddEventAction implements Action {
    private final AuthManager authManager;
    private final EventType eventType;
    private final CalendarManager calendar;

    public AddEventAction(EventType eventType, CalendarManager calendar, AuthManager authManager) {
        this.authManager = authManager;
        this.eventType = eventType;
        this.calendar = calendar;
    }

    @Override
    public void execute() {
        Event e = createEvent();
        addEvent(e);
    }

    public void addEvent(Event e) {
        calendar.ajouterEvent(e);
        System.out.println("Événement ajouté.");
    }

    public Event createEvent() {
        return Events.createEvent(this.eventType, authManager.getLoggedInUser());
    }
}
