package ru.proj.study.telegram.techbot.supportbot.action.event;

public enum Events {

    SUBSCRIBER("Абонент"),
    REVERT("Вернуться"),
    FINISH_APPEAL("Закончить обращение"),
    FINISH_ANSWER("Закончить написание ответа"),
    SUPPORT("Техподдержка"),
    WRITE_MSG("Написать обращение"),
    MAIN_MENU("Главное меню"),
    MY_APPEALS("Мои обращения"),
    AUTHORIZATION("Авторизация"),
    FIND_AND_CONFIRM("Войти"),
    NEW_APPEAL("Новые обращения"),
    ANSWER_APPEAL("Написать ответ");

    private String value;

    Events(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
