package ru.proj.study.telegram.techbot.supportbot.action.event;

import ru.proj.study.telegram.techbot.supportbot.action.BotAction;

public interface EventListiner {

    BotAction apply(String name);
}
