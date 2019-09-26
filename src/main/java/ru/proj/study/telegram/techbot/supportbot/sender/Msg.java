package ru.proj.study.telegram.techbot.supportbot.sender;

import static ru.proj.study.telegram.techbot.supportbot.sender.ConstStr.COMMON;

public enum Msg {

    MAIN_MENU(String.format("Главное меню%s", COMMON)),
    SUBSCRIBER_MENU(String.format("Меню абонента%s", COMMON)),
    SUPPORT_MENU(String.format("Меню сотрудника%s", COMMON)),
    SUCCESSFUL_AUTHORIZATION(String.format("Вы авторизованы%s", COMMON)),
    WRITE_MESSAGE("... по окончанию написания сообщений нажмите кнопку ниже"),
    END_WRITE_MESSAGE(COMMON),
    LOGIN_VERIFICATION("Введите Логин ... будет осуществлена проверка"),
    APPEAL_VERIFICATION("Введите номер обращения ... будет осуществлена проверка");

    private String value;

    Msg(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
