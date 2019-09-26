package ru.proj.study.telegram.techbot.supportbot.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.proj.study.telegram.techbot.supportbot.database.repository.UserRepository;
import ru.proj.study.telegram.techbot.supportbot.database.model.User;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(String login) {
        User user = new User().setLogin(login);
        repository.save(user);
        return user;
    }

    public Optional<User> checkAndGet(String login) {
        return repository.findByLogin(login);
    }
}
