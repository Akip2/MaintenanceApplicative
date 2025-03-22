package app.actions.logged;

import app.calendar.CalendarManager;
import app.event.EventType;
import app.event.MeetingEvent;
import app.user.AuthManager;
import app.user.User;
import app.user.UserManager;

import java.util.Scanner;

public class AddMeetingEventAction extends AddEventAction {
    private final UserManager userManager;

    public AddMeetingEventAction(AuthManager authManager, CalendarManager calendar, UserManager userManager) {
        super(EventType.MEETING, calendar, authManager);
        this.userManager = userManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        MeetingEvent event = (MeetingEvent) this.createEvent();

        System.out.println("Ajouter un participant ? (oui / non)");
        String answer = scanner.nextLine();
        boolean encore = answer.equals("oui");

        while (encore) {
            System.out.print("Nom du participant : ");
            String participantName = scanner.nextLine();
            User newParticipant = userManager.getUser(participantName);

            event.addParticipant(newParticipant);

            System.out.print("Participants : ");
            System.out.println(event.getParticipants());

            System.out.println("En ajouter un autre ? (oui/non)");
            encore = scanner.nextLine().equalsIgnoreCase("oui");
        }

        this.addEvent(event);
    }

    @Override
    public String getName() {
        return "Ajouter une réunion";
    }
}
