package actions.print_events;

import actions.Action;
import calendar.CalendarManager;

public class PrintAllEventsAction implements Action {
    private final CalendarManager calendar;

    public PrintAllEventsAction(CalendarManager calendar) {
        this.calendar = calendar;
    }

    public void execute() {
        calendar.printEvents();
    }

    public String getName() {
        return "Afficher TOUT les événements";
    }
}
