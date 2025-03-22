package app;

import app.event.Event;
import app.event.Events;
import app.event.MeetingEvent;
import app.event.PeriodicEvent;
import app.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventsTest {
    public Events events;
    public User user;

    @BeforeEach
    public void setUp() {
        events = new Events();
        user = new User("John", "Doe");
    }

    @Test
    @DisplayName("Add event")
    public void testAddEvent() {
        Event e = new MeetingEvent("1", user, LocalDateTime.now(), 60, "Nancy");
        events.addEvent(e);
        assertEquals(events.getEvents(), new ArrayList<>(List.of(e)));
    }

    @Test
    @DisplayName("Events in period")
    public void testEventsInPeriod() {
        Event e1 = new MeetingEvent("1", user, LocalDateTime.now(), 60, "Nancy");
        Event e2 = new MeetingEvent("2", user, LocalDateTime.of(2020, Month.MARCH, 28, 10, 10), 60, "Nancy");
        Event e3 = new MeetingEvent("3", user, LocalDateTime.of(2021, Month.MARCH, 28, 10, 10), 60, "Nancy");

        events.addEvent(e1);
        events.addEvent(e2);
        events.addEvent(e3);

        assertEquals(
                events.eventsInPeriod(
                        LocalDateTime.of(2019, 1, 1, 0, 0),
                        LocalDateTime.of(2023, 1, 1, 0, 0)
                ),
                new ArrayList<>(List.of(e2, e3))
        );
    }
}
