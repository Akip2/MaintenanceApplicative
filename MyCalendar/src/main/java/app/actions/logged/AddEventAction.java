package app.actions.logged;

import app.actions.Action;
import app.calendar.CalendarManager;
import app.event.Event;
import app.event.EventType;
import app.event.Events;
import app.user.AuthManager;

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
        boolean wasAbleToAdd = calendar.addEvent(e);
        System.out.println(wasAbleToAdd ? "Événement ajouté." : "L'événement n'a pas pu être ajouté.\nIl chevauche un événement déjà existant");
    }

    public Event createEvent() {
        return Events.createEvent(this.eventType, authManager.getLoggedInUser());
    }
}
