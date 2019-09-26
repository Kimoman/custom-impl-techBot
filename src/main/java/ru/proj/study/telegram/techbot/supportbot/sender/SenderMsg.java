package ru.proj.study.telegram.techbot.supportbot.sender;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.proj.study.telegram.techbot.supportbot.action.BotAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SenderMsg {

    private final SendMessage sender = new SendMessage();

    private final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public SenderMsg() {
        this.sender.enableMarkdown(true);
        this.replyKeyboardMarkup.setSelective(true);
        this.replyKeyboardMarkup.setResizeKeyboard(true);
        this.replyKeyboardMarkup.setOneTimeKeyboard(true);
    }

    private List<KeyboardRow> setReplyMarkup(String... names) {
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        Arrays.stream(names).forEach(buttonName -> row.add(buttonName));
        keyboard.add(row);
        return keyboard;
    }

    private SendMessage configSender(Update msg, BotAction action) {
        String[] names = action.apply();
        List<KeyboardRow> keyboard = setReplyMarkup(names);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return sender
                .setReplyToMessageId(msg.getMessage().getMessageId())
                .setChatId(msg.getMessage().getChatId())
                .setText(action.getAnswer())
                .setReplyMarkup(replyKeyboardMarkup);
    }

    public SendMessage send(Update msg, BotAction action) {
        return configSender(msg, action);
    }
}
