package events;

import users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    /*
    public String description() {
        return switch (type) {
            case "RDV_PERSONNEL" -> "RDV : " + title + " à " + dateDebut.toString();
            case "REUNION" -> "Réunion : " + title + " à " + lieu + " avec " + participants;
            case "PERIODIQUE" -> "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
            default -> "";
        };
    }
     */

    public abstract String description();
}