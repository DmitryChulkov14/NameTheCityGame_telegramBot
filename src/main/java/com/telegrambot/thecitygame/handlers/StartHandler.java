package com.telegrambot.thecitygame.handlers;

import com.telegrambot.thecitygame.bot.BotState;
import com.telegrambot.thecitygame.cache.UserDataCache;
import com.telegrambot.thecitygame.service.LocalMessageService;
import com.telegrambot.thecitygame.service.ReplyMessageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StartHandler implements InputMessageHandler {

    UserDataCache userDataCache;
    ReplyMessageService messageService;
    LocalMessageService localMessageService;

    public StartHandler(UserDataCache userDataCache, ReplyMessageService messageService, LocalMessageService localMessageService) {
        this.userDataCache = userDataCache;
        this.messageService = messageService;
        this.localMessageService = localMessageService;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUserInput(message);
    }

    private SendMessage processUserInput(Message inputMessage) {
        int userId = inputMessage.getFrom().getId();
        long chatId = inputMessage.getChatId();
        String answerToUser = generateAnswerWithRandomCity();

        userDataCache.setUserCurrentBotState(userId, BotState.WAIT_FOR_USER_REPLY);

        return messageService.sendReplyMessage(chatId, answerToUser);
    }

    private String generateAnswerWithRandomCity() {
        // implement getting random city from DB
        String cityName = "Киев";
        String lastCityLetter = getLastCityLetter(cityName);
        return generateAnswer(cityName, lastCityLetter);
    }

    private String getLastCityLetter(String cityName) {
        return cityName.substring(cityName.length() - 1);
    }

    private String generateAnswer(String cityName, String lastCityLetter) {
        return localMessageService.getMessage("bot.message.start") + " " + cityName +
                ". " + System.lineSeparator() +
                localMessageService.getMessage("bot.message.askUserCityName") + " \"" + lastCityLetter.toUpperCase() + "\"";
    }
}
