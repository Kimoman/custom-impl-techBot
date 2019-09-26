package ru.proj.study.telegram.techbot.supportbot.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.proj.study.telegram.techbot.supportbot.database.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Override
    List<User> findAll();

    Optional<User> findByLogin(String login);
}
