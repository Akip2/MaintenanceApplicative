package value_object;

import java.text.MessageFormat;

public class EventId {
    private final String id;

    public EventId(String id) {
        this.id = id;
    }

    public String getValue() {
        return id;
    }

    @Override
    public String toString() {
        return MessageFormat.format("(ID: {0})", id.toString());
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
