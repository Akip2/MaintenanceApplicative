package app.calendar;

import app.event.Event;
import app.event.Events;
import app.value_object.EventId;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarManager {
    private final Events events;

    public CalendarManager() {
        this.events = new Events();
    }

    public boolean addEvent(Event e) {
        if(conflicts(e)) {
           return false;
        } else {
            events.addEvent(e);
            return true;
        }
    }

    private boolean conflicts(Event event) {
        return events.getEvents().stream().anyMatch(e -> e.isOverlapping(event));
    }

    public boolean deleteEvent(EventId eventId) {
        Event eventToDelete = this.getEvents().stream()
                .filter(event -> event.getEventId().equals(eventId))
                .findFirst()
                .orElse(null);

        if(eventToDelete != null) {
            this.events.removeEvent(eventToDelete);
            return true;
        } else {
            return false;
        }
    }

    public List<Event> eventsInPeriod(LocalDateTime debut, LocalDateTime fin) {
        return events.eventsInPeriod(debut, fin);
    }

    public List<Event> getEvents() {
        return events.getEvents();
    }

    public void printEvents() {
        events.printEvents();
    }
}