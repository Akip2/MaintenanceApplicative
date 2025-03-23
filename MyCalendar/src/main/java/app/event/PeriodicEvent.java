package app.event;

import app.user.User;
import app.value_object.EventDuration;
import app.value_object.EventFrequency;
import app.value_object.EventTitle;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class PeriodicEvent extends Event {
    private final EventFrequency frequenceJours; // uniquement pour PERIODIQUE

    public PeriodicEvent(EventTitle title, User proprietaire, LocalDateTime dateDebut, EventDuration dureeMinutes, EventFrequency frequenceJours) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = frequenceJours;
    }

    public EventFrequency getFrequenceJours() {
        return frequenceJours;
    }

    @Override
    public boolean isOverlapping(Event e) {
        LocalDateTime start = e.dateDebut;
        LocalDateTime end = e.dateDebut.plusMinutes(e.dureeMinutes.getValue());

        boolean overlaps = false;
        if(e instanceof PeriodicEvent pe) {
            LocalTime startTime1 = this.dateDebut.toLocalTime();
            LocalTime endTime1 = pe.dateDebut.plusMinutes(dureeMinutes.getValue()).toLocalTime();

            LocalTime startTime2 = start.toLocalTime();
            LocalTime endTime2 = end.toLocalTime();

            overlaps = (endTime1.isAfter(startTime2) && endTime2.isAfter(startTime1))
            && (!pe.frequenceJours.equals(frequenceJours) || frequenceJours.getValue()==1);
        } else {
            LocalDateTime occurrenceStart = this.dateDebut;
            LocalDateTime occurrenceEnd = occurrenceStart.plusMinutes(this.dureeMinutes.getValue());

            while ((occurrenceEnd.isBefore(end) || occurrenceStart.isBefore(end)) && !overlaps) {
                if (occurrenceEnd.isAfter(e.dateDebut) && end.isAfter(occurrenceStart)) {
                    overlaps = true;
                }

                occurrenceStart = occurrenceStart.plusDays(frequenceJours.getValue());
                occurrenceEnd = occurrenceStart.plusMinutes(this.dureeMinutes.getValue());
            }
        }

        return overlaps;
    }

    @Override
    public String description() {
        return "Événement périodique : %s tous les %d jours %s".formatted(title, frequenceJours, getEventId());
    }
}
