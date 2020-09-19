package com.telegrambot.thecitygame.appconfig;

import com.telegrambot.thecitygame.TheCityNameTelegramBot;
import com.telegrambot.thecitygame.bot.TelegramReplier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

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
    public TheCityNameTelegramBot createTheCityNameTelegramBot(TelegramReplier telegramReplier) {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        TheCityNameTelegramBot theCityNameTelegramBot = new TheCityNameTelegramBot(options, telegramReplier);
        theCityNameTelegramBot.setBotUserName(botUserName);
        theCityNameTelegramBot.setBotToken(botToken);
        theCityNameTelegramBot.setWebHookPath(webHookPath);

        return theCityNameTelegramBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
