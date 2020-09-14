package com.telegrambot.thecitygame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TheCityGameApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(TheCityGameApplication.class, args);
	}

}
