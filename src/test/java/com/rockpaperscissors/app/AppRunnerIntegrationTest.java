package com.rockpaperscissors.app;

import org.testng.annotations.Test;

public class AppRunnerIntegrationTest {

	/**
	 * For sake of coverage :)
	 */
	@Test
	void testMainMethod(){
		AppRunner.main(new String[]{});
	}
}