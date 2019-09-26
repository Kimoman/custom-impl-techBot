package ru.proj.study.telegram.techbot.supportbot.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.proj.study.telegram.techbot.supportbot.database.model.ContextBean;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContextRepository extends MongoRepository<ContextBean, Long> {
    @Override
    List<ContextBean> findAll();

    Optional<ContextBean> findById(Long id);
}
