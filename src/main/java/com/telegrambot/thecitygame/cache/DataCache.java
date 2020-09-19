package com.telegrambot.thecitygame.cache;

import com.telegrambot.thecitygame.bot.BotState;

public interface DataCache {

    void setUserCurrentBotState(int userId, BotState botState);

    BotState getUserCurrentBotState(int userId);
}
