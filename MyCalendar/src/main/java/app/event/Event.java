package app.event;

import app.user.User;

import java.time.LocalDateTime;

public abstract class Event {
    //public String type; // "RDV_PERSONNEL", "REUNION", "PERIODIQUE"
    public String title;
    public User proprietaire;
    public LocalDateTime dateDebut;
    public int dureeMinutes;

    public Event(String title, User proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }

    public abstract String description();
}