package event;

import user.User;
import value_object.EventDuration;
import value_object.EventId;
import value_object.EventTitle;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Event {
    protected final EventTitle title;
    protected final User owner;
    protected final LocalDateTime startDate;
    protected final EventDuration durationMinutes;
    protected final EventId eventId;

    public Event(EventTitle title, User owner, LocalDateTime startDate, EventDuration durationMinutes) {
        this.title = title;
        this.owner = owner;
        this.startDate = startDate;
        this.durationMinutes = durationMinutes;
        this.eventId = new EventId(UUID.randomUUID().toString());
    }

    public boolean isOverlapping(Event e) {
        if(e instanceof PeriodicEvent) {
            return e.isOverlapping(this);
        } else {
            LocalDateTime end1 = this.startDate.plusMinutes(this.durationMinutes.getValue());
            LocalDateTime end2 = e.startDate.plusMinutes(e.durationMinutes.getValue());

            return (end1.isAfter(e.startDate) && end2.isAfter(this.startDate));
        }
    }

    public EventId getEventId() {
        return eventId;
    }

    public abstract String description();
}