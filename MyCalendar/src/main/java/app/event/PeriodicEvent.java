package app.event;

import app.user.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class PeriodicEvent extends Event {
    private final int frequenceJours; // uniquement pour PERIODIQUE

    public PeriodicEvent(String title, User proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = frequenceJours;
    }

    public int getFrequenceJours() {
        return frequenceJours;
    }

    @Override
    public boolean isOverlapping(Event e) {
        LocalDateTime start = e.dateDebut;
        LocalDateTime end = e.dateDebut.plusMinutes(e.dureeMinutes);

        boolean overlaps = false;
        if(e instanceof PeriodicEvent pe) {
            LocalTime startTime1 = this.dateDebut.toLocalTime();
            LocalTime endTime1 = pe.dateDebut.plusMinutes(dureeMinutes).toLocalTime();

            LocalTime startTime2 = start.toLocalTime();
            LocalTime endTime2 = end.toLocalTime();

            overlaps = (endTime1.isAfter(startTime2) && endTime2.isAfter(startTime1))
            && (pe.frequenceJours != frequenceJours || frequenceJours==1);
        } else {
            LocalDateTime occurrenceStart = this.dateDebut;
            LocalDateTime occurrenceEnd = occurrenceStart.plusMinutes(this.dureeMinutes);

            while ((occurrenceEnd.isBefore(end) || occurrenceStart.isBefore(end)) && !overlaps) {
                if (occurrenceEnd.isAfter(e.dateDebut) && end.isAfter(occurrenceStart)) {
                    overlaps = true;
                }

                occurrenceStart = occurrenceStart.plusDays(frequenceJours);
                occurrenceEnd = occurrenceStart.plusMinutes(this.dureeMinutes);
            }
        }

        return overlaps;
    }

    @Override
    public String description() {
        return "Événement périodique : %s tous les %d jours".formatted(title, frequenceJours);
    }
}
