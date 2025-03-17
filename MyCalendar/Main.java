import event.*;
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

        while (true) {
            if (utilisateur == null) {
                System.out.println("  _____         _                   _                __  __");
                System.out.println(" / ____|       | |                 | |              |  \\/  |");
                System.out.println(
                        "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
                System.out.println(
                        "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
                System.out.println(
                        "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
                System.out.println(
                        " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
                System.out.println(
                        "                                                                                   __/ |");
                System.out.println(
                        "                                                                                  |___/");

                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Choix : ");

                switch (scanner.nextLine()) {
                    case "1":
                        System.out.print("Nom d'utilisateur: ");
                        String name1 = scanner.nextLine();

                        User userToCompare = userManager.getUser(name1);

                        if (userToCompare != null) {
                            System.out.print("Mot de passe: ");
                            String pass = scanner.nextLine();

                            if (userToCompare.isPassword(pass)) {
                                utilisateur = userToCompare;
                            } else {
                                System.out.println("Mot de passe invalide.");
                            }
                        } else {
                            System.out.println("Utilisateur inexistant.");
                        }
                        break;

                    case "2":
                        System.out.print("Nom d'utilisateur: ");
                        String name2 = scanner.nextLine();

                        System.out.print("Mot de passe: ");
                        String pass = scanner.nextLine();
                        System.out.print("Répéter mot de passe: ");
                        if (scanner.nextLine().equals(pass)) {
                            utilisateur = new User(name2, pass);
                            userManager.addUser(utilisateur);
                        } else {
                            System.out.println("Les mots de passes ne correspondent pas...");
                        }
                        break;
                }
            }

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
