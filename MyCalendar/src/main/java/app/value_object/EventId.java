package app.value_object;

import java.util.UUID;

public class EventId {
    private final UUID id;

    public EventId(UUID id) {
        this.id = id;
    }

    public UUID getValue() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof EventId eventId) {
            return eventId.getValue().equals(this.id);
        } else {
            return false;
        }
    }
}
