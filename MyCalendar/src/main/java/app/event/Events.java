package app.event;

import app.user.User;
import app.value_object.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Events {
    private final List<Event> events;

    public Events() {
        this.events = new ArrayList<>();
    }

    public static Event createEvent(EventType eventType, User owner) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Titre de l'événement : ");
        EventTitle title = new EventTitle(scanner.nextLine());
        System.out.print("Année (AAAA) : ");
        YearStart year = new YearStart(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        MonthStart month = new MonthStart(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        DayStart day = new DayStart(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        HourStart hour = new HourStart(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        MinuteStart minute = new MinuteStart(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        EventDuration duration = new EventDuration(scanner.nextLine());

        Event e;

        if(eventType == EventType.PERSONAL) {
            e  = new PersonalEvent(title.getValue(), owner, LocalDateTime.of(year.getValue(), month.getValue(), day.getValue(), hour.getValue(), minute.getValue()), duration.getValue());
        } else if(eventType == EventType.MEETING) {
            System.out.println("Lieu :");
            EventPlace place = new EventPlace(scanner.nextLine());

            e = new MeetingEvent(title.getValue(), owner, LocalDateTime.of(year.getValue(), month.getValue(), day.getValue(), hour.getValue(), minute.getValue()), duration.getValue(), place.getValue());
        } else if(eventType == EventType.PERIODIC) {
            System.out.print("Frequence (en jours) : ");
            EventFrequence frequence = new EventFrequence(scanner.nextLine());

            e = new PeriodicEvent(title.getValue(), owner, LocalDateTime.of(year.getValue(), month.getValue(), day.getValue(), hour.getValue(), minute.getValue()), duration.getValue(), frequence.getValue());
        } else {
            e = null;
        }

        return e;
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
