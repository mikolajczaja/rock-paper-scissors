package com.rockpaperscissors.config;

import com.rockpaperscissors.model.GameStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DefaultApplicationProperties {
	private final int numberOfRounds;
	private final GameStrategy playerOneStrategy;
	private final GameStrategy playerTwoStrategy;
}
