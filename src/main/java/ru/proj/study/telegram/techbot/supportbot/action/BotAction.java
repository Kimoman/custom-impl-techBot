package ru.proj.study.telegram.techbot.supportbot.action;

import ru.proj.study.telegram.techbot.supportbot.action.event.Events;
import ru.proj.study.telegram.techbot.supportbot.sender.Msg;

import java.util.stream.Stream;

public class BotAction {

    private String answer;
    private Events[] events;

    public BotAction(Events... events) {
        this.events = events;
    }

    public String getAnswer() {
        return answer;
    }

    public BotAction setAnswer(Msg answer) {
        this.answer = answer.getValue();
        return this;
    }

    public BotAction setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public BotAction setEvents(Events... events) {
        this.events = events;
        return this;
    }

    public String[] apply() {
        return Stream.of(events).map(Events::getValue).toArray(String[]::new);
    }
}
