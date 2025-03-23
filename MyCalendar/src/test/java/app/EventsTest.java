package app;

import app.event.Event;
import app.event.Events;
import app.event.MeetingEvent;
import app.user.User;
import app.value_object.EventDuration;
import app.value_object.EventPlace;
import app.value_object.EventTitle;
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
        Event e = new MeetingEvent(new EventTitle("1"), user, LocalDateTime.now(), new EventDuration("60"), new EventPlace("Nancy"));
        events.addEvent(e);
        assertEquals(events.getEvents(), new ArrayList<>(List.of(e)));
    }

    @Test
    @DisplayName("Events in period")
    public void testEventsInPeriod() {
        Event e1 = new MeetingEvent(new EventTitle("1"), user, LocalDateTime.now(), new EventDuration("60"), new EventPlace("Nancy"));
        Event e2 = new MeetingEvent(new EventTitle("2"), user, LocalDateTime.of(2020, Month.MARCH, 28, 10, 10), new EventDuration("60"), new EventPlace("Nancy"));
        Event e3 = new MeetingEvent(new EventTitle("3"), user, LocalDateTime.of(2021, Month.MARCH, 28, 10, 10), new EventDuration("60"), new EventPlace("Nancy"));

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
