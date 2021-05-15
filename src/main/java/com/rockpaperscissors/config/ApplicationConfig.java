package com.rockpaperscissors.config;

import com.rockpaperscissors.model.GameStrategy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Properties;

@Slf4j
@Getter
public class ApplicationConfig {

	private String propertiesFileName="";
	private int numberOfRounds;
	private GameStrategy playerOneStrategy;
	private GameStrategy playerTwoStrategy;

	public ApplicationConfig(String propertiesFileName, DefaultApplicationProperties defaultApplicationProperties) {
		this.propertiesFileName = propertiesFileName;
		this.numberOfRounds= defaultApplicationProperties.getNumberOfRounds();
		this.playerOneStrategy= defaultApplicationProperties.getPlayerOneStrategy();
		this.playerTwoStrategy= defaultApplicationProperties.getPlayerTwoStrategy();
	}

	public void initialize() {
		try {
			readFromPropertiesFile();
			log.info("Successfully loaded config from properties file.");
		} catch (IOException e) {
			log.error("Cannot read properties from file: "+propertiesFileName+" ,cause: "+e+"\nKeeping default values.");
		}
	}

	/**
	 * @throws IOException on reading properties file failure
	 */
	private void readFromPropertiesFile() throws IOException {
		var properties=loadPropertiesFromFile();

		String numberOfRoundsPropertyValue = properties.getProperty("numberOfRounds");
		if(StringUtils.isNumeric(numberOfRoundsPropertyValue)){
			numberOfRounds =Integer.parseInt(numberOfRoundsPropertyValue);
		}
		GameStrategy.getGameStrategyWithName(properties.getProperty("playerOneStrategy"))
				.ifPresent(strategyFromProperties->playerOneStrategy=strategyFromProperties);
		GameStrategy.getGameStrategyWithName(properties.getProperty("playerTwoStrategy"))
				.ifPresent(strategyFromProperties->playerTwoStrategy=strategyFromProperties);
	}

	private Properties loadPropertiesFromFile() throws IOException {
		var properties = new Properties();
		var propertiesFileInputStream = ApplicationConfig.class.getClassLoader()
				.getResourceAsStream(propertiesFileName);

		if(propertiesFileInputStream==null){
			throw new IOException("Resource with fileName: "+propertiesFileName+" not found.");
		}
		properties.load(propertiesFileInputStream);
		return properties;
	}
}
