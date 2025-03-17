import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {
    public String type; // "RDV_PERSONNEL", "REUNION", "PERIODIQUE"
    public String title;
    public User proprietaire;
    public LocalDateTime dateDebut;
    public int dureeMinutes;
    public String lieu; // utilisé seulement pour REUNION
    public List<User> participants; // séparés par virgules (pour REUNION uniquement)
    public int frequenceJours; // uniquement pour PERIODIQUE

    public Event(String type, String title, User proprietaire, LocalDateTime dateDebut, int dureeMinutes,
                 String lieu, int frequenceJours) {
        this.type = type;
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.lieu = lieu;
        this.participants = new ArrayList<>();
        this.frequenceJours = frequenceJours;
    }

    public void addParticipant(User user) {
        this.participants.add(user);
    }

    public String description() {
        return switch (type) {
            case "RDV_PERSONNEL" -> "RDV : " + title + " à " + dateDebut.toString();
            case "REUNION" -> "Réunion : " + title + " à " + lieu + " avec " + participants;
            case "PERIODIQUE" -> "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
            default -> "";
        };
    }
}