package app.event;

import app.user.User;
import app.value_object.EventDuration;
import app.value_object.EventTitle;

import java.text.MessageFormat;
import java.time.LocalDateTime;

public class PersonalEvent extends Event {
    public PersonalEvent(EventTitle title, User proprietaire, LocalDateTime dateDebut, EventDuration dureeMinutes) {
        super(title, proprietaire, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return MessageFormat.format("RDV_PERSONNEL : {0} à {1} {2}", title, dateDebut.toString(), getEventId());
    }
}
