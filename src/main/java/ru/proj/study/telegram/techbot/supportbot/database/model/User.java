package ru.proj.study.telegram.techbot.supportbot.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
/*
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";*/

  /*  @Id
    private Long id;*/

    @Id
    private String login;

/*    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }*/

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }
}
