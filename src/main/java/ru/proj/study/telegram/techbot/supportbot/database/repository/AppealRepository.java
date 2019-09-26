package ru.proj.study.telegram.techbot.supportbot.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.proj.study.telegram.techbot.supportbot.database.model.Appeal;
import ru.proj.study.telegram.techbot.supportbot.database.model.AppealStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppealRepository extends MongoRepository<Appeal, Long> {
    @Override
    List<Appeal> findAll();

    Optional<Appeal> findById(Long id);

    List<Appeal> findByChatId(Long chatId);

    List<Appeal> findByStatus(AppealStatus status);
}
