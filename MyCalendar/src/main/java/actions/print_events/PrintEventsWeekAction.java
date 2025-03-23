package actions.print_events;

import calendar.CalendarManager;
import value_object.Week;
import value_object.YearStart;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Scanner;

public class PrintEventsWeekAction extends PrintEventsAction {
    private final CalendarManager calendar;

    public PrintEventsWeekAction(CalendarManager calendar) {
        this.calendar = calendar;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'année (AAAA) : ");
        YearStart year = new YearStart(scanner.nextLine());

        System.out.print("Entrez le numéro de semaine (1-52) : ");
        Week week = new Week(scanner.nextLine());

        LocalDateTime start = LocalDateTime.now()
                .withYear(year.getValue())
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), week.getValue())
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime end = start.plusDays(7).minusSeconds(1);

        printList(calendar.eventsInPeriod(start, end));
    }

    public String getName() {
        return "Afficher les événements d'une SEMAINE précise";
    }
}
