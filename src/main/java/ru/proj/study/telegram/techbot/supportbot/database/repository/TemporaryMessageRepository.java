package ru.proj.study.telegram.techbot.supportbot.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.proj.study.telegram.techbot.supportbot.database.model.TemporaryMessage;
import ru.proj.study.telegram.techbot.supportbot.database.model.TemporaryMessageStatus;

import java.util.List;

@Repository
public interface TemporaryMessageRepository extends MongoRepository<TemporaryMessage, Long> {
    @Override
    List<TemporaryMessage> findAll();

    List<TemporaryMessage> findByIdAndStatus(Long id, TemporaryMessageStatus status);

    List<TemporaryMessage> findByChatIdAndStatus(Long chatId,TemporaryMessageStatus status);

    void deleteAllByChatIdAndStatus(Long chatId, TemporaryMessageStatus status);
}
