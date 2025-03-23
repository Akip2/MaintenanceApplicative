package app.actions.logged;

import app.actions.Action;
import app.actions.ActionManager;
import app.calendar.CalendarManager;
import app.menu.MenuType;
import app.user.AuthManager;
import app.value_object.EventId;

import java.util.Scanner;

public class DeleteEventAction implements Action {
    private final CalendarManager calendarManager;
    public DeleteEventAction(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Id de l'événement à supprimer : ");
        EventId id = new EventId(scanner.nextLine());
        if(calendarManager.deleteEvent(id)) {
            System.out.println("Evénement supprimé avec succès.");
        } else {
            System.out.println("Id introuvable.");
        }
    }

    @Override
    public String getName() {
        return "Supprimer un événement";
    }
}
