package com.telegrambot.thecitygame.cache;

import com.telegrambot.thecitygame.bot.BotState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache implements DataCache {

    private final Map<Integer, BotState> userBotStates = new HashMap<>();

    @Override
    public void setUserCurrentBotState(int userId, BotState botState) {
        userBotStates.put(userId, botState);
    }

    @Override
    public BotState getUserCurrentBotState(int userId) {
        BotState botState = userBotStates.get(userId);
        if (botState == null) {
            botState = BotState.START;
        }

        return botState;
    }
}
