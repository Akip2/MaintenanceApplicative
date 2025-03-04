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

    List<Player> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayerId = 0;
    private Player currentPlayer;

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
        Player player = new Player(playerName);
        players.add(player);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());

        if(currentPlayer == null) {
            currentPlayer = players.get(0);
        }
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    private void advanceCurrentPlayer(int steps) {
        currentPlayer.advance(steps);
        System.out.println(currentPlayer + "'s new location is " + currentPlayer.getPosition());
    }

    public void roll(int roll) {
        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenalty()) { //Tries to get out of jail
            boolean isGettingOutOfPenaltyBox = roll % 2 != 0;
            if (isGettingOutOfPenaltyBox) {
                currentPlayer.escapePenaltyBox();
                System.out.println(players.get(currentPlayerId) + " is getting out of the penalty box");
            } else {
                System.out.println(players.get(currentPlayerId) + " is not getting out of the penalty box");
            }
        }

        if(!currentPlayer.isInPenalty()) { //Can play
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
        int currentPosition = currentPlayer.getPosition() - 1;

        return switch (currentPosition) {
            case 0, 4, 8 -> POP;
            case 1, 5, 9 -> SCIENCE;
            case 2, 6, 10 -> SPORTS;
            default -> ROCK;
        };
    }

    public boolean handleCorrectAnswer() {
        if (currentPlayer.isInPenalty()) {
            nextPlayer();
            return true;
        }

        System.out.println("Answer was correct!!!!");
        currentPlayer.addCoin();
        System.out.println(currentPlayer + " now has " + currentPlayer.getCoins() + " Gold Coins.");

        boolean winner = didPlayerWin();
        nextPlayer();
        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer + " was sent to the penalty box");
        currentPlayer.enterPenaltyBox();

        nextPlayer();
        return true;
    }

    public void nextPlayer() {
        currentPlayerId++;

        if (currentPlayerId == players.size()) {
            currentPlayerId = 0;
        }

        currentPlayer = players.get(currentPlayerId);
    }

    private boolean didPlayerWin() {
        return currentPlayer.getCoins() != 6;
    }
}
