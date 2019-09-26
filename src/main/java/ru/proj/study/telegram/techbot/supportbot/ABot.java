package ru.proj.study.telegram.techbot.supportbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.proj.study.telegram.techbot.supportbot.config.BotConfig;
import ru.proj.study.telegram.techbot.supportbot.statemachine.context.ContextManager;
import ru.proj.study.telegram.techbot.supportbot.database.model.ContextBean;
import ru.proj.study.telegram.techbot.supportbot.database.repository.ContextRepository;

import javax.annotation.PostConstruct;

import java.util.List;

@Component
public class ABot extends AbilityBot {

    private AbilityCatalog abilityCatalog;


    private static final Logger logger
            = LoggerFactory.getLogger(TechFirstLineBot.class);
    private final ContextBean bean = new ContextBean();

    private BotConfig botConfig;

    @Autowired
    private ContextRepository repository;

    @Autowired
    private ContextManager contextManager;

    @Autowired
    public ABot(BotConfig config, AbilityCatalog abilityCatalog) {

        super(config.getToken(), config.getUsername());
        this.abilityCatalog = abilityCatalog;
        this.botConfig = config;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        abilityCatalog.sayHelloWorld(silent);
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getUsername();
    }

    @Override
    public int creatorId() {
        return 345999;
    }

    @PostConstruct
    public void init() {
        logger.info("username: {}, token: {}", botConfig.getUsername(), botConfig.getToken());
    }
}
