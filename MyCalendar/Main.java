import event.*;
import user.AuthManager;
import user.User;
import user.UserManager;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);

        User utilisateur = null;
        boolean continuer = true;

        UserManager userManager = new UserManager();
        AuthManager authManager = new AuthManager(userManager);

        ActionManager actionManager = new ActionManager(userManager, authManager);

        while (true) {
            actionManager.askChoice();

            while (continuer && utilisateur != null) {
                System.out.println("\nBonjour, " + utilisateur);
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println("1 - Voir les événements");
                System.out.println("2 - Ajouter un rendez-vous perso");
                System.out.println("3 - Ajouter une réunion");
                System.out.println("4 - Ajouter un évènement périodique");
                System.out.println("5 - Se déconnecter");
                System.out.print("Votre choix : ");

                String choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        System.out.println("\n=== Menu de visualisation d'Événements ===");
                        System.out.println("1 - Afficher TOUS les événements");
                        System.out.println("2 - Afficher les événements d'un MOIS précis");
                        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
                        System.out.println("4 - Afficher les événements d'un JOUR précis");
                        System.out.println("5 - Retour");
                        System.out.print("Votre choix : ");

                        choix = scanner.nextLine();

                        switch (choix) {
                            case "1":
                                calendar.printEvents();
                                break;

                            case "2":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeMois = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le mois (1-12) : ");
                                int mois = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
                                LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

                                afficherListe(calendar.eventsInPeriod(debutMois, finMois));
                                break;

                            case "3":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeSemaine = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le numéro de semaine (1-52) : ");
                                int semaine = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutSemaine = LocalDateTime.now()
                                        .withYear(anneeSemaine)
                                        .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                                        .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                                        .withHour(0).withMinute(0);
                                LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

                                afficherListe(calendar.eventsInPeriod(debutSemaine, finSemaine));
                                break;

                            case "4":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeJour = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le mois (1-12) : ");
                                int moisJour = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le jour (1-31) : ");
                                int jour = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
                                LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

                                afficherListe(calendar.eventsInPeriod(debutJour, finJour));
                                break;
                        }
                        break;

                    case "2":
                        // Ajout simplifié d'un RDV personnel
                        Event personalEvent = Events.createEvent(EventType.PERSONAL, utilisateur);
                        calendar.ajouterEvent(personalEvent);

                        System.out.println("Événement ajouté.");
                        break;

                    case "3":
                        MeetingEvent meetingEvent = (MeetingEvent) Events.createEvent(EventType.MEETING, utilisateur);

                        System.out.println("Ajouter un participant ? (oui / non)");
                        String answer = scanner.nextLine();
                        boolean encore = answer.equals("oui");

                        while (encore) {
                            String participantName = scanner.nextLine();
                            User newParticipant = userManager.getUser(participantName);

                            meetingEvent.addParticipant(newParticipant);

                            System.out.print("Participants : ");
                            System.out.println(meetingEvent.getParticipants());

                            System.out.println("En ajouter un autre ? (oui/non)");
                            encore = scanner.nextLine().equals("oui");
                        }

                        calendar.ajouterEvent(meetingEvent);

                        System.out.println("Événement ajouté.");
                        break;

                    case "4":
                        Event periodicEvent = Events.createEvent(EventType.PERIODIC, utilisateur);
                        calendar.ajouterEvent(periodicEvent);

                        System.out.println("Événement ajouté.");
                        break;

                    default:
                        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
                        continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");

                        utilisateur = null;
                }
            }
        }
    }

    private static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}
