package app.actions.print_events;

import app.actions.Action;
import app.event.Event;

import java.util.List;

public abstract class PrintEventsAction implements Action {
    public void printList(List<Event> events) {
        if (events.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : events) {
                System.out.println("- " + e.description());
            }
        }
    }
}
