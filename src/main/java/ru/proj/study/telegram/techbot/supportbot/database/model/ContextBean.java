package ru.proj.study.telegram.techbot.supportbot.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.proj.study.telegram.techbot.supportbot.statemachine.context.BotContext;

@Document
public class ContextBean {

    @Id
    private Long id;

    private BotContext context;

    public Long getId() {
        return id;
    }

    public ContextBean setId(Long chatId) {
        this.id = chatId;
        return this;
    }

    public BotContext getContext() {
        return context;
    }

    public ContextBean setContext(BotContext context) {
        this.context = context;
        return this;
    }
}
