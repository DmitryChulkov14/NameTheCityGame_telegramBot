package com.telegrambot.thecitygame.appconfig;

import com.telegrambot.thecitygame.TheCityNameTelegramBot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:telegram.properties")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig {
    @Value("${bot.webHookPath}")
    String webHookPath;

    @Value("${bot.name}")
    String botUserName;

    @Value("${bot.token}")
    String botToken;

    @Bean
    public TheCityNameTelegramBot createTheCityNameTelegramBot() {
        TheCityNameTelegramBot theCityNameTelegramBot = new TheCityNameTelegramBot();
        theCityNameTelegramBot.setBotUserName(botUserName);
        theCityNameTelegramBot.setBotToken(botToken);
        theCityNameTelegramBot.setWebHookPath(webHookPath);

        return theCityNameTelegramBot;
    }
}
