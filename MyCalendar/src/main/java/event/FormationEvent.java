package event;

import user.User;
import value_object.EventDuration;
import value_object.EventTitle;
import java.time.LocalDateTime;

public class FormationEvent extends Event {
    private User trainer;

    public FormationEvent(EventTitle title, User owner, LocalDateTime startDate, EventDuration durationMinutes) {
        super(title, owner, startDate, durationMinutes);
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    @Override
    public String description() {
        return "Formation par %s: %s %s".formatted(trainer, title, getEventId());
    }
}
