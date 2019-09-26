package ru.proj.study.telegram.techbot.supportbot.statemachine.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.proj.study.telegram.techbot.supportbot.action.BotAction;
import ru.proj.study.telegram.techbot.supportbot.action.event.EventListiner;
import ru.proj.study.telegram.techbot.supportbot.action.event.EventManager;
import ru.proj.study.telegram.techbot.supportbot.database.model.ContextBean;
import ru.proj.study.telegram.techbot.supportbot.database.repository.ContextRepository;

import java.util.Optional;

import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.ANSWER_APPEAL;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.AUTHORIZATION;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.FINISH_ANSWER;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.FINISH_APPEAL;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.MAIN_MENU;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.MY_APPEALS;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.NEW_APPEAL;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.REVERT;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.SUBSCRIBER;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.SUPPORT;
import static ru.proj.study.telegram.techbot.supportbot.action.event.Events.WRITE_MSG;

@Component
public class ContextManager {

    public EventManager events = new EventManager();

    private BotContext context;

    @Autowired
    private ContextRepository repository;

    @Autowired
    public ContextManager(BotContext context) {
        this.context = context;
        init();
    }

    public BotAction getAction(String name) {
        BotAction action = events.notify(name);
        if (action != null) {
            return action;
        } else {
            return context.doStuff();
        }
    }

    private void init() {
        events.subscribe(SUBSCRIBER.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.selectedSubscriber();
            }
        });
        events.subscribe(REVERT.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.startOff();
            }
        });
        events.subscribe(SUPPORT.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.selectedSupport();
            }
        });
        events.subscribe(WRITE_MSG.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.writeMessage();
            }
        });
        events.subscribe(FINISH_APPEAL.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.endOfAppeal();
            }
        });
        events.subscribe(NEW_APPEAL.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.informationOutput();
            }
        });
        events.subscribe(MAIN_MENU.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.startOff();
            }
        });
        events.subscribe(MY_APPEALS.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.informationOutput();
            }
        });
        events.subscribe(AUTHORIZATION.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.logIn();
            }
        });
        events.subscribe(FINISH_ANSWER.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.endOfAnswer();
            }
        });
        events.subscribe(ANSWER_APPEAL.getValue(), new EventListiner() {
            @Override
            public BotAction apply(String name) {
                return context.writeAnswer();
            }
        });
    }

    public BotContext getContext() {
        return context;
    }

    public BotContext getContext(Long chatId, String text) {
        Optional<ContextBean> context = repository.findById(chatId);
        if (context.isPresent()) {
            this.context = context.get().getContext();
            return this.context.setChatId(chatId).setText(text);
        } else {
            return this.context.setChatId(chatId).setText(text);
        }
    }
}
