package app;

import app.event.Events;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventsTest {
    public static Events events;

    @BeforeEach
    public void setUp() {
        events = new Events();
    }
}
