package app.event;

import app.user.User;
import app.value_object.EventDuration;
import app.value_object.EventFrequency;
import app.value_object.EventTitle;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class PeriodicEvent extends Event {
    private final EventFrequency frequenceJours; // uniquement pour PERIODIQUE

    public PeriodicEvent(EventTitle title, User owner, LocalDateTime startDate, EventDuration durationMinutes, EventFrequency frequenceJours) {
        super(title, owner, startDate, durationMinutes);
        this.frequenceJours = frequenceJours;
    }

    public EventFrequency getFrequenceJours() {
        return frequenceJours;
    }

    @Override
    public boolean isOverlapping(Event e) {
        LocalDateTime start = e.startDate;
        LocalDateTime end = e.startDate.plusMinutes(e.durationMinutes.getValue());

        boolean overlaps = false;
        if(e instanceof PeriodicEvent pe) {
            LocalTime startTime1 = this.startDate.toLocalTime();
            LocalTime endTime1 = pe.startDate.plusMinutes(durationMinutes.getValue()).toLocalTime();

            LocalTime startTime2 = start.toLocalTime();
            LocalTime endTime2 = end.toLocalTime();

            overlaps = (endTime1.isAfter(startTime2) && endTime2.isAfter(startTime1))
            && (!pe.frequenceJours.equals(frequenceJours) || frequenceJours.getValue()==1);
        } else {
            LocalDateTime occurrenceStart = this.startDate;
            LocalDateTime occurrenceEnd = occurrenceStart.plusMinutes(this.durationMinutes.getValue());

            while ((occurrenceEnd.isBefore(end) || occurrenceStart.isBefore(end)) && !overlaps) {
                if (occurrenceEnd.isAfter(e.startDate) && end.isAfter(occurrenceStart)) {
                    overlaps = true;
                }

                occurrenceStart = occurrenceStart.plusDays(frequenceJours.getValue());
                occurrenceEnd = occurrenceStart.plusMinutes(this.durationMinutes.getValue());
            }
        }

        return overlaps;
    }

    @Override
    public String description() {
        return "Événement périodique : %s tous les %d jours %s".formatted(title, frequenceJours, getEventId());
    }
}
