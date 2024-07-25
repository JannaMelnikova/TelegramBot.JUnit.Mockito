package org.example;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

@Getter
@Setter
public class Bot extends TelegramLongPollingBot {
    private HandlerMessage handlerMessage=new HandlerMessage();
    private Logger logger = Logger.getLogger("Bot");

    public Bot(String botToken) {
        super(botToken);
    }

    public Bot() {
    }

    @Override
    public void onUpdateReceived(Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Long chatId = update.getMessage().getChatId();
                String text = update.getMessage().getText();
                String answer = handlerMessage.getAnswer(text);
                logger.info("user message:" + " id "+ chatId + ", message " + text +" \nБот Жанр фильма: " + answer);

                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(answer);
                sendMessage.enableHtml(true);

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    //throw new RuntimeException(e);
                }

            }
    }

    @Override
    public String getBotUsername() {
        return "cinema_java_bot";
    }
}
