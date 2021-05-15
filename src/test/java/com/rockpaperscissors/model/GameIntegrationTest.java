package com.rockpaperscissors.model;

import com.rockpaperscissors.config.ApplicationConfig;
import com.rockpaperscissors.config.DefaultApplicationProperties;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * For purpose of testing {@link GameStrategy#getMove()} a setup for {@link org.mockito.plugins.MockMaker} was added to
 * resources/mockito-extensions with a single line which enabled testing of final classes (including enums).
 */
public class GameIntegrationTest {

	@Mock
	private final GameStrategy randomStrategyMock = GameStrategy.RANDOM;
	private int counter = 0;

	@BeforeTest
	void mockitoInit() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldReturnCorrectResultsWhenFlowOfGameIsValid() {
		when(randomStrategyMock.getMove()).thenReturn(this::getRandomMoveMock);
		var expectedResults = Map.of(GameRoundResult.PLAYER_ONE_WON, 34L,
				GameRoundResult.PLAYER_TWO_WON, 33L, GameRoundResult.DRAW, 33L);

		var defaultApplicationProperties = new DefaultApplicationProperties(100,
				GameStrategy.PAPER_ONLY, randomStrategyMock);

		var applicationConfig = new ApplicationConfig("Invalid name", defaultApplicationProperties);

		Game game = new Game(applicationConfig);
		var actualResults = game.play();
		assertThat(actualResults).containsExactlyInAnyOrderEntriesOf(expectedResults);
	}

	private Move getRandomMoveMock() {
		Move value = Move.values()[counter % 3];
		counter++;
		return value;
	}

	@Test(dataProvider = "nonRandomGameStrategyValues")
	void shouldResultConsistOfOnlyDrawsWhenBothPlayersUseSameNonRandomStrategy(GameStrategy strategy) {
		var defaultApplicationProperties = new DefaultApplicationProperties(100, strategy, strategy);

		var applicationConfig = new ApplicationConfig("Invalid name", defaultApplicationProperties);

		Game game = new Game(applicationConfig);
		var resultsWithNumbersOfOccurrences = game.play();
		assertThat(resultsWithNumbersOfOccurrences).containsExactly(entry(GameRoundResult.DRAW, 100L));
	}

	@DataProvider(name = "nonRandomGameStrategyValues")
	private static Object[] nonRandomGameStrategyValues() {
		return new GameStrategy[]{GameStrategy.ROCK_ONLY, GameStrategy.PAPER_ONLY, GameStrategy.SCISSORS_ONLY};
	}
}