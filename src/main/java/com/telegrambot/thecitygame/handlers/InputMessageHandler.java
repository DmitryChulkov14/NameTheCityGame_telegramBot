package com.telegrambot.thecitygame.handlers;

import com.telegrambot.thecitygame.bot.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface InputMessageHandler {

    SendMessage handle(Message message);

    BotState getHandlerName();
}
