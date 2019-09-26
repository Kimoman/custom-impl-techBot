package ru.proj.study.telegram.techbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.proj.study.telegram.techbot.supportbot.database.model.User;
import ru.proj.study.telegram.techbot.supportbot.database.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User createUser(@RequestParam String login) {
        return service.createUser(login);
    }
}
