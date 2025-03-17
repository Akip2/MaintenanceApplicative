package event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Events {
    private final List<Event> events;

    public Events() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public List<Event> eventsInPeriod(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e instanceof PeriodicEvent) {
                LocalDateTime temp = e.dateDebut;
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(((PeriodicEvent) e).getFrequenceJours());
                }
            } else if (!e.dateDebut.isBefore(debut) && !e.dateDebut.isAfter(fin)) {
                result.add(e);
            }
        }

        return result;
    }

    public void printEvents() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }
}
