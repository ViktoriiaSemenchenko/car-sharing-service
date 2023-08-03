package com.example.carsharingservice.service.impl;

import com.example.carsharingservice.model.User;
import com.example.carsharingservice.service.TelegramNotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramNotificationServiceImpl extends TelegramLongPollingBot
        implements TelegramNotificationService {
    @Value("${telegram.bot.name}")
    private String botName;
    @Value("${telegram.bot.token}")
    private String botToken; // API-ключ  бота
    @Value("${telegram.user.id}")
    private String userId;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public void sendMessageToUser(String message, User user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
