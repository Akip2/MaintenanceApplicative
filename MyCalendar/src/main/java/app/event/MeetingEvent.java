package app.event;

import app.user.User;
import app.value_object.EventDuration;
import app.value_object.EventPlace;
import app.value_object.EventTitle;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingEvent extends Event {
    private List<User> participants;
    private EventPlace lieu;

    public MeetingEvent(EventTitle title, User owner, LocalDateTime startDate, EventDuration durationMinutes,
                        EventPlace lieu) {
        super(title, owner, startDate, durationMinutes);
        this.participants = new ArrayList<>();
        this.lieu = lieu;

        this.addParticipant(owner);
    }

    public void addParticipant(User user) {
        this.participants.add(user);
    }

    public List<User> getParticipants() {
        return participants;
    }

    @Override
    public String description() {
        return MessageFormat.format("Réunion : {0} à {1} avec {2} {3}", title, lieu, participants, getEventId());
    }
}
