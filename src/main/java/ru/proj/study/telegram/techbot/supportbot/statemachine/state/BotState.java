package ru.proj.study.telegram.techbot.supportbot.statemachine.state;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.proj.study.telegram.techbot.supportbot.statemachine.context.BotContext;
import ru.proj.study.telegram.techbot.supportbot.action.ActionCatalog;
import ru.proj.study.telegram.techbot.supportbot.database.model.Appeal;
import ru.proj.study.telegram.techbot.supportbot.database.model.User;
import ru.proj.study.telegram.techbot.supportbot.database.repository.AppealRepository;
import ru.proj.study.telegram.techbot.supportbot.database.service.AppealService;
import ru.proj.study.telegram.techbot.supportbot.database.service.GeneratorService;
import ru.proj.study.telegram.techbot.supportbot.database.service.TemporaryMessageService;
import ru.proj.study.telegram.techbot.supportbot.database.service.UserService;

import javax.annotation.PostConstruct;
import java.util.EnumSet;
import java.util.Optional;

import static ru.proj.study.telegram.techbot.supportbot.database.model.TemporaryMessageStatus.ANSWER;
import static ru.proj.study.telegram.techbot.supportbot.database.model.TemporaryMessageStatus.APPEAL;

public enum BotState {

    START_OFF {
        @Override
        public void startOff(BotContext context) {
            context.setState(this);
            context.setAction(catalog.getStartAction());
        }

        @Override
        public void selectedSubscriber(BotContext context) {
            context.setState(SELECTED_SUBSCRIBER);
            context.setAction(catalog.getSubscriberAction());
        }

        @Override
        public void selectedSupport(BotContext context) {
            context.setState(SELECTED_SUPPORT);
            context.setAction(catalog.getSupportAction());
        }

        @Override
        public void writeMessage(BotContext context) {

        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {

        }

        @Override
        public void informationOutput(BotContext context) {

        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    },
    SELECTED_SUBSCRIBER {
        @Override
        public void startOff(BotContext context) {

        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {
            context.setState(WRITE_APPEAL);
            context.setAction(catalog.getWriteAction());
        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {

        }

        @Override
        public void informationOutput(BotContext context) {
            String answer = appealService.getMyAppeals(context.getChatId());
            context.setAction(catalog.getSubscriberAction().setAnswer(answer));
        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    },
    SELECTED_SUPPORT {
        @Override
        public void startOff(BotContext context) {
            context.setState(START_OFF);
            context.setAction(catalog.getStartAction());
        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {

        }

        @Override
        public void logIn(BotContext context) {
            context.setState(LOG_IN);
            context.setAction(catalog.getLoginAction());
        }

        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {

        }

        @Override
        public void informationOutput(BotContext context) {

        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    },
    LOG_IN {
        @Override
        public void startOff(BotContext context) {

        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {

        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {
            Optional<User> user = userService.checkAndGet(context.getText());
            user.map(u -> u.getLogin()).ifPresent(login -> {
                context.setState(SUPPORT);
                context.setAction(catalog.getAnswerAction());
            });
        }

        @Override
        public void informationOutput(BotContext context) {

        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    },
    WRITE_APPEAL {
        @Override
        public void endOfAppeal(BotContext context) {
            temporaryMessageService.textOfMessages(context.getChatId(), APPEAL)
                    .ifPresent(str -> {
                        appealService.createAppeal(context.getChatId(), str);
                        temporaryMessageService.deleteMessage(context.getChatId(), APPEAL);
                    });
            context.setState(END_OF_APPEAL);
            context.setAction(catalog.getEndOfAppealAction());
        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void startOff(BotContext context) {

        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {

        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {
            temporaryMessageService.saveCommonMessage(context.getChatId(), context.getText());
        }

        @Override
        public void informationOutput(BotContext context) {

        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    },
    END_OF_APPEAL {
        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void startOff(BotContext context) {
            context.setState(START_OFF);
            context.setAction(catalog.getStartAction());
        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {
            context.setState(WRITE_APPEAL);
            context.setAction(catalog.getWriteAction());
        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {

        }

        @Override
        public void informationOutput(BotContext context) {
            String answer = appealService.getMyAppeals(context.getChatId());
            context.setAction(catalog.getEndOfAppealAction().setAnswer(answer));
        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    },
    SUPPORT {
        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void startOff(BotContext context) {
            context.setState(START_OFF);
            context.setAction(catalog.getStartAction());
        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {

        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {

        }

        @Override
        public void informationOutput(BotContext context) {
            String answer = appealService.getNewAppeals();
            context.setAction(catalog.getAnswerAction().setAnswer(answer));
        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {
            context.setState(ENTER_ANSWER_NUMBER);
            context.setAction(catalog.getEnterNumAppealAction());
        }
    },
    ENTER_ANSWER_NUMBER {
        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void startOff(BotContext context) {
            context.setState(START_OFF);
            context.setAction(catalog.getStartAction());
        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {

        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {
            Optional<Appeal> appeal = appealService.getAppeal(NumberUtils.toLong(context.getText()));
            appeal.ifPresent(a -> {
                context.setCurrentAppeal(a.getId());
                context.setState(WRITE_ANSWER);
                context.setAction(catalog.getFinishAnswerAction());
            });
        }

        @Override
        public void informationOutput(BotContext context) {

        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    },
    WRITE_ANSWER {
        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void startOff(BotContext context) {

        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {

        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {
            temporaryMessageService.saveAnswerMessage(context.getChatId(), context.getText());
        }

        @Override
        public void informationOutput(BotContext context) {

        }

        @Override
        public void endOfAnswer(BotContext context) {
            temporaryMessageService.textOfMessages(context.getChatId(), ANSWER)
                    .ifPresent(txt -> {
                        appealService.updateAppeal(context.getCurrentAppeal(), txt);
                        temporaryMessageService.deleteMessage(context.getChatId(), ANSWER);
                    });
            context.setState(SUPPORT);
            context.setAction(catalog.getAnswerAction());
        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    },
    END_OF_ANSWER {
        @Override
        public void endOfAppeal(BotContext context) {

        }

        @Override
        public void mainMenu(BotContext context) {

        }

        @Override
        public void myAppeals(BotContext context) {

        }

        @Override
        public void startOff(BotContext context) {
            context.setState(START_OFF);
            context.setAction(catalog.getStartAction());
        }

        @Override
        public void selectedSubscriber(BotContext context) {

        }

        @Override
        public void selectedSupport(BotContext context) {

        }

        @Override
        public void writeMessage(BotContext context) {

        }

        @Override
        public void logIn(BotContext context) {

        }

        @Override
        public void doStuff(BotContext context) {
        }

        @Override
        public void informationOutput(BotContext context) {
            String answer = appealService.getNewAppeals();
            context.setAction(catalog.getAnswerAction().setAnswer(answer));
        }

        @Override
        public void endOfAnswer(BotContext context) {

        }

        @Override
        public void writeAnswer(BotContext context) {

        }
    };

    public TemporaryMessageService temporaryMessageService;

    public AppealService appealService;

    public GeneratorService generatorService;

    public UserService userService;

    public ActionCatalog catalog;

    public abstract void doStuff(BotContext context);

    public abstract void informationOutput(BotContext context);

    public abstract void endOfAppeal(BotContext context);

    public abstract void endOfAnswer(BotContext context);

    public abstract void mainMenu(BotContext context);

    public abstract void myAppeals(BotContext context);

    public abstract void writeAnswer(BotContext context);

    public abstract void startOff(BotContext context);

    public abstract void selectedSubscriber(BotContext context);

    public abstract void selectedSupport(BotContext context);

    public abstract void writeMessage(BotContext context);

    public abstract void logIn(BotContext context);

    public BotState setTemporaryMessageService(TemporaryMessageService temporaryMessageService) {
        this.temporaryMessageService = temporaryMessageService;
        return this;
    }

    public BotState setAppealService(AppealService appealService) {
        this.appealService = appealService;
        return this;
    }

    public BotState setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
        return this;
    }

    public BotState setCatalog(ActionCatalog catalog) {
        this.catalog = catalog;
        return this;
    }

    public BotState setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    @Component
    private static class AutowiredRepository {
        @Autowired
        private AppealRepository appealRepo;

        @Autowired
        private TemporaryMessageService temporaryMessageService;

        @Autowired
        private AppealService appealService;

        @Autowired
        private GeneratorService generatorServ;

        @Autowired
        private UserService userService;

        @Autowired
        private ActionCatalog catalog;

        @PostConstruct
        public void postConstuct() {
            for (BotState bs : EnumSet.allOf(BotState.class)) {
                bs.setTemporaryMessageService(temporaryMessageService);
                bs.setGeneratorService(generatorServ);
                bs.setCatalog(catalog);
                bs.setAppealService(appealService);
                bs.setUserService(userService);
            }
        }
    }
}