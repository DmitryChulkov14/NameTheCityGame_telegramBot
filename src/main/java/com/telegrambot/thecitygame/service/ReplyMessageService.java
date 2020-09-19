package com.telegrambot.thecitygame.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Objects;

@Service
public class ReplyMessageService {

    private final LocalMessageService localMessageService;

    public ReplyMessageService(LocalMessageService localMessageService) {
        this.localMessageService = localMessageService;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, localMessageService.getMessage(replyMessage));
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage, Objects... args) {
        return new SendMessage(chatId, localMessageService.getMessage(replyMessage, args));
    }

    public SendMessage sendReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, replyMessage);
    }
}
