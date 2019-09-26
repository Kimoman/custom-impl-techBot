package ru.proj.study.telegram.techbot.supportbot.statemachine.context;

import org.springframework.stereotype.Component;
import ru.proj.study.telegram.techbot.supportbot.action.BotAction;
import ru.proj.study.telegram.techbot.supportbot.action.event.Events;
import ru.proj.study.telegram.techbot.supportbot.statemachine.state.BotState;

import static ru.proj.study.telegram.techbot.supportbot.sender.Msg.MAIN_MENU;

@Component
public class BotContext {

    private BotState state;

    private BotAction action;

    private Long chatId;

    private Long currentAppeal;

    private String text;

    public BotContext() {
        this.state = BotState.START_OFF;
        this.action = new BotAction(Events.SUBSCRIBER, Events.SUPPORT).setAnswer(MAIN_MENU);
    }

    public BotAction startOff() {
        state.startOff(this);
        return action;
    }

    public BotAction selectedSubscriber() {
        state.selectedSubscriber(this);
        return action;
    }

    public BotAction selectedSupport() {
        state.selectedSupport(this);
        return action;
    }

    public BotAction writeMessage() {
        state.writeMessage(this);
        return action;
    }

    public BotAction endOfAppeal() {
        state.endOfAppeal(this);
        return action;
    }

    public BotAction doStuff() {
        state.doStuff(this);
        return action;
    }

    public BotAction informationOutput() {
        state.informationOutput(this);
        return action;
    }

    public BotAction endOfAnswer() {
        state.endOfAnswer(this);
        return action;
    }

    public BotAction logIn() {
        state.logIn(this);
        return action;
    }

    public BotAction writeAnswer() {
        state.writeAnswer(this);
        return action;
    }

    public Long getCurrentAppeal() {
        return currentAppeal;
    }

    public BotContext setCurrentAppeal(Long currentAppeal) {
        this.currentAppeal = currentAppeal;
        return this;
    }

    public BotState getState() {
        return state;
    }

    public void setState(BotState state) {
        this.state = state;
    }

    public BotAction getAction() {
        return action;
    }

    public BotContext setAction(BotAction action) {
        this.action = action;
        return this;
    }

    public String getText() {
        return text;
    }

    public BotContext setText(String text) {
        this.text = text;
        return this;
    }

    public Long getChatId() {
        return chatId;
    }

    public BotContext setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }
}
