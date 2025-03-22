package app.calendar;

import app.event.Event;
import app.event.Events;
import app.event.PeriodicEvent;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarManager {
    public Events events;

    public CalendarManager() {
        this.events = new Events();
    }

    public void ajouterEvent(Event e) {
        events.addEvent(e);
    }

    public List<Event> eventsInPeriod(LocalDateTime debut, LocalDateTime fin) {
        return events.eventsInPeriod(debut, fin);
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.plusMinutes(e1.dureeMinutes);
        LocalDateTime fin2 = e2.dateDebut.plusMinutes(e2.dureeMinutes);

        if (e1 instanceof PeriodicEvent || e2 instanceof PeriodicEvent) {
            return false; // Simplification abusive
        }

        return e1.dateDebut.isBefore(fin2) && fin1.isAfter(e2.dateDebut);
    }

    public void printEvents() {
        events.printEvents();
    }
}