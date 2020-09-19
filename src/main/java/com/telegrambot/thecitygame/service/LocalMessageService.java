package com.telegrambot.thecitygame.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
public class LocalMessageService {

    private final Locale locale;
    private final MessageSource messageSource;

    public LocalMessageService(@Value("${localeTag}") String localeTag, MessageSource messageSource) {
        this.locale = Locale.forLanguageTag(localeTag);
        this.messageSource = messageSource;
    }

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, locale);
    }

    public String getMessage(String message, Objects... args) {
        return messageSource.getMessage(message, args, locale);
    }
}
