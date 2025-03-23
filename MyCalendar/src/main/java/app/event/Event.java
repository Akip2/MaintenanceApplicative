package app.event;

import app.user.User;
import app.value_object.EventDuration;
import app.value_object.EventId;
import app.value_object.EventTitle;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Event {
    protected final EventTitle title;
    protected final User proprietaire;
    protected final LocalDateTime dateDebut;
    protected final EventDuration dureeMinutes;
    protected final EventId eventId;

    public Event(EventTitle title, User proprietaire, LocalDateTime dateDebut, EventDuration dureeMinutes) {
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.eventId = new EventId(UUID.randomUUID().toString());
    }

    public boolean isOverlapping(Event e) {
        if(e instanceof PeriodicEvent) {
            return e.isOverlapping(this);
        } else {
            LocalDateTime end1 = this.dateDebut.plusMinutes(this.dureeMinutes.getValue());
            LocalDateTime end2 = e.dateDebut.plusMinutes(e.dureeMinutes.getValue());

            return (end1.isAfter(e.dateDebut) && end2.isAfter(this.dateDebut));
        }
    }

    public EventId getEventId() {
        return eventId;
    }

    public abstract String description();
}