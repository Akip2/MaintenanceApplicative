package actions.logged;

import calendar.CalendarManager;
import event.EventType;
import event.MeetingEvent;
import user.AuthManager;
import user.User;
import user.UserManager;

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
