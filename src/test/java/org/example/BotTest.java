package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.stream.Stream;

public class BotTest {

    private Bot bot;
    private HandlerMessage handlerMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bot = spy(new Bot("7446389542:AAE1Ryzi9BXDjX0XgbWm9t3CMYDpx40NQqA")); // Используем реальный объект Bot и шпион для него
        when(bot.getBotUsername()).thenReturn("cinema_java_bot");
        handlerMessage = mock(HandlerMessage.class);
        bot.setHandlerMessage(handlerMessage); // Устанавливаем зависимость
    }

    @ParameterizedTest
    @MethodSource("commandProvider")
     void testOnUpdateReceived(TestData testData)  {
        // Создание моков для Update и Message
        Update update = mock(Update.class);
        Message message = mock(Message.class);

        // Настройка поведения моков
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getChatId()).thenReturn(12345L);
        when(message.getText()).thenReturn(testData.command);
        when(handlerMessage.getAnswer(testData.command)).thenReturn(testData.response);


        // Вызов тестируемого метода
        bot.onUpdateReceived(update);

        // Создание аргумента для захвата отправленного сообщения
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);

        // Проверка, что execute был вызван и захват сообщения
        try {
            verify(bot).execute(argumentCaptor.capture());
        } catch (TelegramApiException e) {

        }

        // Получение захваченного сообщения
        SendMessage capturedMessage = argumentCaptor.getValue();

        // Проверка значений захваченного сообщения
        assertEquals("12345", capturedMessage.getChatId());
        //assertEquals("Этот бот поможет вам с выбором фильма", capturedMessage.getText());
        assertEquals(testData.response, capturedMessage.getText());
    }

    static Stream<TestData> commandProvider() {
        return Stream.of(
                new TestData("/start","Этот бот поможет вам с выбором фильма"),
                new TestData("/sport", "Кровавый спорт")
                );

    }
}
