package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Bot bot=new Bot("7446389542:AAE1Ryzi9BXDjX0XgbWm9t3CMYDpx40NQqA");
        try {
            TelegramBotsApi telegramBotsAPI=new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsAPI.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
