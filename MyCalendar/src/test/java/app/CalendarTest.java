package app;

import app.calendar.CalendarManager;
import app.event.Event;
import app.event.MeetingEvent;
import app.event.PeriodicEvent;
import app.user.User;
import app.value_object.EventDuration;
import app.value_object.EventFrequency;
import app.value_object.EventPlace;
import app.value_object.EventTitle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {
    public CalendarManager calendarManager;
    public static User user;

    @BeforeAll
    public static void setup() {
        user = new User("John Doe", "pass");
    }

    @BeforeEach
    public void setUp() {
        calendarManager = new CalendarManager();
    }

    @Test
    @DisplayName("Add event")
    public void testAddEvent() {
        Event e = new MeetingEvent(new EventTitle("1"), user, LocalDateTime.now(), new EventDuration("60"), new EventPlace("Nancy"));
        calendarManager.addEvent(e);
        assertEquals(calendarManager.getEvents(), new ArrayList<>(List.of(e)));
    }

    @Test
    @DisplayName("Events in period")
    public void testEventsInPeriod() {
        Event e1 = new MeetingEvent(new EventTitle("1"), user, LocalDateTime.now(), new EventDuration("60"), new EventPlace("Nancy"));
        Event e2 = new MeetingEvent(new EventTitle("2"), user, LocalDateTime.of(2020, Month.MARCH, 28, 10, 10), new EventDuration("60"), new EventPlace("Nancy"));
        Event e3 = new MeetingEvent(new EventTitle("3"), user, LocalDateTime.of(2021, Month.MARCH, 28, 10, 10), new EventDuration("60"), new EventPlace("Nancy"));

        calendarManager.addEvent(e1);
        calendarManager.addEvent(e2);
        calendarManager.addEvent(e3);

        assertEquals(
                calendarManager.eventsInPeriod(
                        LocalDateTime.of(2019, 1, 1, 0, 0),
                        LocalDateTime.of(2023, 1, 1, 0, 0)
                ),
                new ArrayList<>(List.of(e2, e3))
        );
    }

    @Test
    @DisplayName("Add event without conflict")
    public void testAddEventNoConflict() {
        Event e = new MeetingEvent(new EventTitle("1"), user, LocalDateTime.now(), new EventDuration("60"), new EventPlace("Nancy"));
        assertTrue(calendarManager.addEvent(e));
    }

    @Test
    @DisplayName("Add event with conflict")
    public void testAddEventConflict() {
        Event e1 = new MeetingEvent(new EventTitle("1"), user, LocalDateTime.now(), new EventDuration("60"), new EventPlace("Nancy"));
        Event e2 = new MeetingEvent(new EventTitle("2"), user, LocalDateTime.now(), new EventDuration("60"), new EventPlace("Nancy"));

        calendarManager.addEvent(e1);
        assertFalse(calendarManager.addEvent(e2));
    }

    @Test
    @DisplayName("Add periodic event without conflict 1")
    public void testAddEventPeriodicNoConflict1() {
        Event e = new MeetingEvent(new EventTitle("1"), user, LocalDateTime.of(2020,10, 10, 0, 0), new EventDuration("60"), new EventPlace("Nancy"));
        Event periodic = new PeriodicEvent(new EventTitle("2"), user, LocalDateTime.of(2020, 10, 1, 0, 0), new EventDuration("60"), new EventFrequency("5"));

        calendarManager.addEvent(e);
        assertTrue(calendarManager.addEvent(periodic));
    }

    @Test
    @DisplayName("Add periodic event without conflict 2")
    public void testAddEventPeriodicNoConflict2() {
        Event e = new MeetingEvent(new EventTitle("1"), user, LocalDateTime.of(2020,10, 10, 0, 0), new EventDuration("60"), new EventPlace("Nancy"));
        Event periodic = new PeriodicEvent(new EventTitle("2"), user, LocalDateTime.of(2020, 10, 1, 0, 0), new EventDuration("60"), new EventFrequency("5"));

        calendarManager.addEvent(periodic);
        assertTrue(calendarManager.addEvent(e));
    }
}