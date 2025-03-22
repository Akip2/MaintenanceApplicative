package app.actions.print_events;

import app.calendar.CalendarManager;
import app.value_object.DayStart;
import app.value_object.MonthStart;
import app.value_object.YearStart;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PrintEventsDayAction extends PrintEventsAction {
    private final CalendarManager calendar;

    public PrintEventsDayAction(CalendarManager calendar) {
        this.calendar = calendar;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'année (AAAA) : ");
        YearStart year = new YearStart(scanner.nextLine());

        System.out.print("Entrez le mois (1-12) : ");
        MonthStart month = new MonthStart(scanner.nextLine());

        System.out.print("Entrez le jour (1-31) : ");
        DayStart day = new DayStart(scanner.nextLine());

        LocalDateTime start = LocalDateTime.of(year.getValue(), month.getValue(), day.getValue(), 0, 0);
        LocalDateTime end = start.plusDays(1).minusSeconds(1);

        printList(calendar.eventsInPeriod(start, end));
    }

    public String getName() {
        return "Afficher les événements d'un JOUR précis";
    }
}
