package app.event;

import app.user.User;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingEvent extends Event {
    private List<User> participants;
    private String lieu;

    public MeetingEvent(String title, User proprietaire, LocalDateTime dateDebut, int dureeMinutes,
                        String lieu) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.participants = new ArrayList<>();
        this.lieu = lieu;

        this.addParticipant(proprietaire);
    }

    public void addParticipant(User user) {
        this.participants.add(user);
    }

    public List<User> getParticipants() {
        return participants;
    }

    @Override
    public String description() {
        return MessageFormat.format("Réunion : {0} à {1} avec {2}", title, lieu, participants);
    }
}
