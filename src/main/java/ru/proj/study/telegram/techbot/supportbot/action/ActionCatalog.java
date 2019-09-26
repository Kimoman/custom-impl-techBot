package ru.proj.study.telegram.techbot.supportbot.action;

import org.springframework.stereotype.Component;
import ru.proj.study.telegram.techbot.supportbot.action.event.Events;
import ru.proj.study.telegram.techbot.supportbot.sender.Msg;

@Component
public class ActionCatalog {

    public BotAction getStartAction() {
        return new BotAction(Events.SUBSCRIBER, Events.SUPPORT).setAnswer(Msg.MAIN_MENU);
    }

    public BotAction getSubscriberAction() {
        return new BotAction(Events.WRITE_MSG, Events.MY_APPEALS).setAnswer(Msg.SUBSCRIBER_MENU);
    }

    public BotAction getSupportAction() {
        return new BotAction(Events.AUTHORIZATION, Events.REVERT).setAnswer(Msg.SUPPORT_MENU);
    }

    public BotAction getWriteAction() {
        return new BotAction(Events.FINISH_APPEAL).setAnswer(Msg.WRITE_MESSAGE);
    }

    public BotAction getEndOfAppealAction() {
        return new BotAction(Events.MAIN_MENU, Events.MY_APPEALS, Events.WRITE_MSG).setAnswer(Msg.END_WRITE_MESSAGE);
    }

    public BotAction getEndOfAnswerAction() {
        return new BotAction(Events.MAIN_MENU, Events.NEW_APPEAL).setAnswer(Msg.END_WRITE_MESSAGE);
    }

    public BotAction getLoginAction() {
        return new BotAction().setAnswer(Msg.LOGIN_VERIFICATION);
    }

    public BotAction getEnterNumAppealAction() {
        return new BotAction(Events.MAIN_MENU).setAnswer(Msg.APPEAL_VERIFICATION);
    }

    public BotAction getAnswerAction() {
        return new BotAction(Events.NEW_APPEAL, Events.ANSWER_APPEAL, Events.MAIN_MENU)
                .setAnswer(Msg.SUCCESSFUL_AUTHORIZATION);
    }

    public BotAction getFinishAnswerAction() {
        return new BotAction(Events.FINISH_ANSWER).setAnswer(Msg.WRITE_MESSAGE);
    }
}
