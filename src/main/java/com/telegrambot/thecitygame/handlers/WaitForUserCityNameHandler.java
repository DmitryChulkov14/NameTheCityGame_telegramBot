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
public class WaitForUserCityNameHandler implements InputMessageHandler {

    UserDataCache userDataCache;
    ReplyMessageService messageService;
    LocalMessageService localMessageService;

    public WaitForUserCityNameHandler(UserDataCache userDataCache, ReplyMessageService messageService, LocalMessageService localMessageService) {
        this.userDataCache = userDataCache;
        this.messageService = messageService;
        this.localMessageService = localMessageService;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.WAIT_FOR_USER_REPLY;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUserInput(message);
    }

    private SendMessage processUserInput(Message inputMessage) {
        int userId = inputMessage.getFrom().getId();
        long chatId = inputMessage.getChatId();
        String userInputCityName = inputMessage.getText();
        String cityLastLetter = getCityLastLetter(userInputCityName);
        String answerToUser = generateAnswerWithLastLetter(cityLastLetter);

        userDataCache.setUserCurrentBotState(userId, BotState.WAIT_FOR_USER_REPLY);

        return messageService.sendReplyMessage(chatId, answerToUser);
    }

    private String getCityLastLetter(String inputCityName) {
        String lastLetter = "";
        if (!inputCityName.isEmpty()) {
            lastLetter = inputCityName.substring(inputCityName.length() - 1);
        }
        return lastLetter;
    }

    private String generateAnswerWithLastLetter(String lastLetter) {
        return localMessageService.getMessage("bot.message.askUserCityName") + " " + "\""
                + lastLetter.toUpperCase() + "\"";
    }
}
