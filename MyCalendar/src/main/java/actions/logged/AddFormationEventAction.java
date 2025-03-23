package actions.logged;

import calendar.CalendarManager;
import event.EventType;
import event.FormationEvent;
import user.AuthManager;
import user.User;
import user.UserManager;

import java.util.Scanner;

public class AddFormationEventAction extends AddEventAction {
    private final UserManager userManager;
    public AddFormationEventAction(AuthManager authManager, CalendarManager calendar, UserManager userManager) {
        super(EventType.FORMATION, calendar, authManager);
        this.userManager = userManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        FormationEvent event = (FormationEvent) this.createEvent();

        User trainer = null;
        while (trainer == null) {
            System.out.print("Nom du formateur : ");
            String trainerName = scanner.nextLine();

            trainer = userManager.getUser(trainerName);
        }

        event.setTrainer(trainer);
        this.addEvent(event);
    }

    @Override
    public String getName() {
        return "Ajouter une formation";
    }
}
