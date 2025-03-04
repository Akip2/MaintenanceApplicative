package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {
    private final String ROCK = "Rock";
    private final String POP = "Pop";
    private final String SCIENCE = "Science";
    private final String SPORTS = "Sports";

    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(createQuestion(POP, i));
            scienceQuestions.addLast(createQuestion(SCIENCE, i));
            sportsQuestions.addLast(createQuestion(SPORTS, i));
            rockQuestions.addLast(createQuestion(ROCK, i));
        }
    }

    public String createQuestion(String type, int index) {
        return type + " Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        places[howManyPlayers()] = 1;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;
        players.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    private void advanceCurrentPlayer(int steps) {
        places[currentPlayer] = places[currentPlayer] + steps;
        if (places[currentPlayer] > 12) {
            places[currentPlayer] = places[currentPlayer] - 12;
        }
        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) { //Tries to get out of jail
            isGettingOutOfPenaltyBox = roll % 2 != 0;
            if (isGettingOutOfPenaltyBox) {
                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            }
        }

        if(!inPenaltyBox[currentPlayer] || isGettingOutOfPenaltyBox) { //Can play
            advanceCurrentPlayer(roll);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }
    }

    private void askQuestion() {
        switch (currentCategory()) {
            case POP -> System.out.println(popQuestions.removeFirst());
            case SCIENCE -> System.out.println(scienceQuestions.removeFirst());
            case SPORTS -> System.out.println(sportsQuestions.removeFirst());
            case ROCK -> System.out.println(rockQuestions.removeFirst());
        }
    }

    private String currentCategory() {
        int currentPosition = places[currentPlayer] - 1;

        return switch (currentPosition) {
            case 0, 4, 8 -> POP;
            case 1, 5, 9 -> SCIENCE;
            case 2, 6, 10 -> SPORTS;
            default -> ROCK;
        };
    }

    public boolean handleCorrectAnswer() {
        if (inPenaltyBox[currentPlayer]) {
            if (!isGettingOutOfPenaltyBox) {
                nextPlayer();
                return true;
            }
        }

        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

        boolean winner = didPlayerWin();
        nextPlayer();
        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        nextPlayer();
        return true;
    }

    public void nextPlayer() {
        currentPlayer++;

        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    private boolean didPlayerWin() {
        return purses[currentPlayer] != 6;
    }
}
