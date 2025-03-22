package app.actions.print_events;

import app.calendar.CalendarManager;
import app.value_object.MonthStart;
import app.value_object.YearStart;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PrintEventsMonthAction extends PrintEventsAction {
    private final CalendarManager calendar;

    public PrintEventsMonthAction(CalendarManager calendar) {
        this.calendar = calendar;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'année (AAAA) : ");
        YearStart year = new YearStart(scanner.nextLine());

        System.out.print("Entrez le mois (1-12) : ");
        MonthStart month = new MonthStart(scanner.nextLine());

        LocalDateTime start = LocalDateTime.of(year.getValue(), month.getValue(), 1, 0, 0);
        LocalDateTime end = start.plusMonths(1).minusSeconds(1);

        printList(calendar.eventsInPeriod(start, end));
    }

    public String getName() {
        return "Afficher les événements d'un MOIS précis";
    }
}
