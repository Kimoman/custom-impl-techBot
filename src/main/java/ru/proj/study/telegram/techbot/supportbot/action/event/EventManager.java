package ru.proj.study.telegram.techbot.supportbot.action.event;

import ru.proj.study.telegram.techbot.supportbot.action.BotAction;

import java.util.HashMap;
import java.util.Map;

public class EventManager {
    Map<String, EventListiner> listeners = new HashMap<>();

    public void subscribe(String eventType, EventListiner listener) {
        listeners.put(eventType, listener);
    }

    public void unsubscribe(String eventType) {
        listeners.remove(eventType);
    }

    public BotAction notify(String eventType) {
        if (listeners.containsKey(eventType)) {
            EventListiner listiner = listeners.get(eventType);
            return listiner.apply(eventType);
        }
        return null;
    }
}
