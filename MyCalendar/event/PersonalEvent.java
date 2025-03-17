package event;

import user.User;

import java.text.MessageFormat;
import java.time.LocalDateTime;

public class PersonalEvent extends Event {
    public PersonalEvent(String title, User proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super(title, proprietaire, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return MessageFormat.format("RDV_PERSONNEL : {0} à {1}", title, dateDebut.toString());
    }
}
