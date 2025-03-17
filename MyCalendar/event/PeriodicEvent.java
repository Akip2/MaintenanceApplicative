package event;

import user.User;

import java.time.LocalDateTime;

public class PeriodicEvent extends Event {
    private int frequenceJours; // uniquement pour PERIODIQUE

    public PeriodicEvent(String title, User proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = frequenceJours;
    }

    public int getFrequenceJours() {
        return frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique : %s tous les %d jours".formatted(title, frequenceJours);
    }
}
