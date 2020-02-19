package by.pochepko.res.spotbot.service;

import by.pochepko.res.spotbot.model.SpotMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
@Component
public class SpotBot extends TelegramLongPollingBot {

    @Autowired
    SpotMessageService spotMessageService;

    @Value("${telegram.bot.token}")
    private String token;
    @Value("${telegram.bot.username}")
    private String botUsername;


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            response.setChatId(message.getChatId());
            response.setText(spotMessageService.getSpotMessage(message.getText()).getMessage());
            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
