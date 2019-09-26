package ru.proj.study.telegram.techbot.supportbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.proj.study.telegram.techbot.supportbot.action.BotAction;
import ru.proj.study.telegram.techbot.supportbot.config.BotConfig;
import ru.proj.study.telegram.techbot.supportbot.sender.SenderMsg;
import ru.proj.study.telegram.techbot.supportbot.statemachine.context.BotContext;
import ru.proj.study.telegram.techbot.supportbot.statemachine.context.ContextManager;
import ru.proj.study.telegram.techbot.supportbot.database.model.ContextBean;
import ru.proj.study.telegram.techbot.supportbot.database.repository.ContextRepository;

import javax.annotation.PostConstruct;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;


public class TechFirstLineBot extends AbilityBot {

    private static final Logger logger
            = LoggerFactory.getLogger(TechFirstLineBot.class);
    private final ContextBean bean = new ContextBean();

    private BotConfig botConfig;

    @Autowired
    private ContextRepository repository;

    @Autowired
    private ContextManager contextManager;

    @Autowired
    public TechFirstLineBot(BotConfig config) {
        super(config.getToken(), config.getUsername());
        this.botConfig = config;
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

    public Ability sayHelloWorld() {
        return Ability
                .builder()
                .name("hello")
                .info("says hello world!")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> silent.send("Hello world!", ctx.chatId()))
                .build();
    }

    @Override
    public void onUpdateReceived(Update update) {

        SenderMsg sender = new SenderMsg();
        String event = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        BotContext botContext = contextManager.getContext(chatId, event);
        BotAction action = contextManager.getAction(event);

        try {
            execute(sender.send(update, action));
            repository.save(bean.setContext(contextManager.getContext()).setId(chatId));
        } catch (TelegramApiException e) {
            logger.error("error: {}", e.getMessage());
        }
        logger.info("Message received - {} : Chat - {}",
                update.getMessage().getText(),
                update.getMessage().getChatId());
    }

    @PostConstruct
    public void init() {
        logger.info("username: {}, token: {}", botConfig.getUsername(), botConfig.getToken());
    }
}
