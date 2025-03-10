package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {
    public enum Category {
        POP("Pop"),
        SCIENCE("Science"),
        ROCK("Rock"),
        SPORTS("Sports");

        private final String stringValue;
        Category(final String name) {
            stringValue = name;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

    List<Player> players = new ArrayList<>();

    private final HashMap<Category, LinkedList<String>> questions;

    int currentPlayerId = 0;
    private Player currentPlayer;
    public Game() {
        questions = new HashMap<>();

        Category[] categories = Category.values();
        for (Category category : categories) {
            LinkedList<String> questionList = new LinkedList<>();

            for (int j = 0; j < 50; j++) {
                questionList.add(createQuestion(category, j));
            }

            questions.put(category, questionList);
        }
    }

    public String createQuestion(Category category, int index) {
        return category.toString() + " Question " + index;
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
            System.out.println("The category is " + getCurrentCategory());
            askQuestion();
        }
    }

    private void askQuestion() {
        Category currentCategory = getCurrentCategory();
        LinkedList<String> questionList = questions.get(currentCategory);
        System.out.println(questionList.removeFirst());
        questions.put(currentCategory, questionList);
    }

    private Category getCurrentCategory() {
        int currentPosition = currentPlayer.getPosition() - 1;

        return switch (currentPosition) {
            case 0, 4, 8 -> Category.POP;
            case 1, 5, 9 -> Category.SCIENCE;
            case 2, 6, 10 -> Category.SPORTS;
            default -> Category.ROCK;
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
