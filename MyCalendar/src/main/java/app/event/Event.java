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

    public boolean isOverlapping(Event e) {
        if(e instanceof PeriodicEvent) {
            return e.isOverlapping(this);
        } else {
            LocalDateTime end1 = this.dateDebut.plusMinutes(this.dureeMinutes);
            LocalDateTime end2 = e.dateDebut.plusMinutes(e.dureeMinutes);

            return (end1.isAfter(e.dateDebut) && end2.isAfter(this.dateDebut));
        }
    }

    public abstract String description();
}