package com.telegrambot.thecitygame.bot;

import com.telegrambot.thecitygame.cache.UserDataCache;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TelegramReplier {

    BotStateContext botStateContext;
    UserDataCache userDataCache;

    public TelegramReplier(BotStateContext botStateContext, UserDataCache userDataCache) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User: {}, chatId: {}, with text: {}",
                    message.getFrom().getUserName(),
                    message.getChatId(),
                    message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMessage = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;

        switch (inputMessage) {
            case "/start":
                botState = BotState.START;
                break;
            default:
                botState = userDataCache.getUserCurrentBotState(userId);
                break;
        }

        userDataCache.setUserCurrentBotState(userId, botState);

        return botStateContext.processInputMessage(botState, message);
    }
}
