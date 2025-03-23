package app.event;

import app.user.User;
import app.value_object.EventDuration;
import app.value_object.EventFrequency;
import app.value_object.EventTitle;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FormationEvent extends Event {
    private User trainer;

    public FormationEvent(EventTitle title, User proprietaire, LocalDateTime dateDebut, EventDuration dureeMinutes) {
        super(title, proprietaire, dateDebut, dureeMinutes);
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    @Override
    public String description() {
        return "Formation par %s: %s %s".formatted(trainer, title, getEventId());
    }
}
