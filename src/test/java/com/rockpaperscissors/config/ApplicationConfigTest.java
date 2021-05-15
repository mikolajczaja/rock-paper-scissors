package com.rockpaperscissors.config;

import com.rockpaperscissors.model.GameStrategy;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationConfigTest {

	@Test
	void shouldReturnDefaultValuesWhenPropertiesFileNameIsInvalid() {
		DefaultApplicationProperties defaultApplicationProperties = new DefaultApplicationProperties(100,
				GameStrategy.ROCK_ONLY, GameStrategy.PAPER_ONLY);

		ApplicationConfig applicationConfig = new ApplicationConfig("INVALID NAME", defaultApplicationProperties);
		applicationConfig.initialize();

		assertThat(applicationConfig.getNumberOfRounds()).isEqualTo(defaultApplicationProperties.getNumberOfRounds());
		assertThat(applicationConfig.getPlayerOneStrategy()).isEqualTo(
				defaultApplicationProperties.getPlayerOneStrategy());
		assertThat(applicationConfig.getPlayerTwoStrategy()).isEqualTo(
				defaultApplicationProperties.getPlayerTwoStrategy());
	}

	@Test
	void shouldReturnCorrectValuesWhenPropertiesFileIsValid() {
		DefaultApplicationProperties defaultApplicationProperties = new DefaultApplicationProperties(100,
				GameStrategy.ROCK_ONLY, GameStrategy.PAPER_ONLY);

		ApplicationConfig applicationConfig = new ApplicationConfig("complete-test.properties",
				defaultApplicationProperties);
		applicationConfig.initialize();

		assertThat(applicationConfig.getNumberOfRounds()).isEqualTo(123);
		assertThat(applicationConfig.getPlayerOneStrategy()).isEqualTo(GameStrategy.RANDOM);
		assertThat(applicationConfig.getPlayerTwoStrategy()).isEqualTo(GameStrategy.ROCK_ONLY);

	}

	@Test
	void shouldReturnCorrectValuesWhenPropertiesFileIsValidButIncomplete() {
		DefaultApplicationProperties defaultApplicationProperties = new DefaultApplicationProperties(100,
				GameStrategy.ROCK_ONLY, GameStrategy.PAPER_ONLY);
		int expectedNumberOfRounds = 724;
		GameStrategy expectedPlayerOneStrategy = GameStrategy.SCISSORS_ONLY;

		ApplicationConfig applicationConfig = new ApplicationConfig("incomplete-test.properties",
				defaultApplicationProperties);
		applicationConfig.initialize();

		assertThat(applicationConfig.getNumberOfRounds()).isEqualTo(expectedNumberOfRounds);
		assertThat(applicationConfig.getPlayerOneStrategy()).isEqualTo(expectedPlayerOneStrategy);
		assertThat(applicationConfig.getPlayerTwoStrategy()).isEqualTo(
				defaultApplicationProperties.getPlayerTwoStrategy());
	}
}