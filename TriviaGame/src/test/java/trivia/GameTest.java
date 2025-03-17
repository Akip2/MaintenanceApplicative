package trivia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
	private Game game;

	@BeforeEach
	public void setUp() {
		game = new Game();
	}

	@Test
	@DisplayName("IsPlayable with 0 player")
	public void testIsPlayableZeroPlayer() {
		assertFalse(game.isPlayable());
	}

	@Test
	@DisplayName("IsPlayable with 1 player")
	public void testIsPlayableOnePlayer() {
		game.add("Antoine");
		assertFalse(game.isPlayable());
	}

	@Test
	@DisplayName("IsPlayable with enough players")
	public void testIsPlayableEnoughPlayers() {
		game.add("Antoine");
		game.add("Damien");
		assertTrue(game.isPlayable());
	}

	@Test
	@DisplayName("Add player without reaching max")
	public void testAddPlayer() {
		assertTrue(game.add("Dylan"));
	}

	@Test
	@DisplayName("Add player and reaching max")
	public void testAddPlayerMax() {
		game.add("Axel");
		game.add("Luc");
		game.add("Zack");
		game.add("Nathan");
		game.add("Dylan");
		game.add("Antoine");
		assertFalse(game.add("Thomas"));
	}

	@Test
	@DisplayName("Roll while not in penalty")
	public void testRoll() {
		game.add("Antoine");
		int roll = 5;

		game.roll(roll);
		Player currentPlayer = game.getCurrentPlayer();

		assertEquals(6, currentPlayer.getPosition());
	}

	@Test
	@DisplayName("Wrong answer puts player in penalty")
	public void testWrongAnswer() {
		game.add("Antoine");
		Player currentPlayer = game.getCurrentPlayer();
		game.wrongAnswer();

		assertTrue(currentPlayer.isInPenalty());
	}

	@Test
	@DisplayName("Correct answer")
	public void testCorrectAnswer() {
		game.add("Antoine");
		Player currentPlayer = game.getCurrentPlayer();
		game.handleCorrectAnswer();

		assertEquals(1, currentPlayer.getCoins());
	}

	@Test
	@DisplayName("Roll and escape penalty")
	public void testRollEscape() {
		game.add("Antoine");
		Player currentPlayer = game.getCurrentPlayer();
		game.wrongAnswer();
		game.roll(3);

		assertFalse(currentPlayer.isInPenalty());
	}

	@Test
	@DisplayName("Roll and stays in penalty")
	public void testRollStays() {
		game.add("Antoine");
		Player currentPlayer = game.getCurrentPlayer();
		game.wrongAnswer();
		game.roll(2);

		assertTrue(currentPlayer.isInPenalty());
	}
}
