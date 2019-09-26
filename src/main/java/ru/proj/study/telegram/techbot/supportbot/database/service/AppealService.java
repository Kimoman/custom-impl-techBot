package ru.proj.study.telegram.techbot.supportbot.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.proj.study.telegram.techbot.supportbot.database.repository.AppealRepository;
import ru.proj.study.telegram.techbot.supportbot.database.model.Appeal;

import java.util.Optional;
import java.util.stream.Collectors;

import static ru.proj.study.telegram.techbot.supportbot.database.model.AppealStatus.COMPLETED;
import static ru.proj.study.telegram.techbot.supportbot.database.model.AppealStatus.NEW;

@Service
public class AppealService {

    @Autowired
    private AppealRepository repository;

    @Autowired
    private GeneratorService generatorService;

    public void createAppeal(Long chatId, String text) {
        Appeal appeal
                = new Appeal()
                .setId(generatorService.generateSequence(Appeal.SEQUENCE_NAME))
                .setChatId(chatId)
                .setText(text)
                .setStatus(NEW);

        repository.save(appeal);
    }

    public void updateAppeal(Long id, String text) {
        getAppeal(id).ifPresent(appeal -> {
            appeal.setStatus(COMPLETED).setAnswer(text);
            repository.save(appeal.setStatus(COMPLETED).setAnswer(text));
        });
    }

    public String getMyAppeals(Long chatId) {
        String myAppeals = repository.findByChatId(chatId).stream()
                .map(item ->
                        String.format("№%s - Обращение : %s - Ответ : %s",
                                item.getId(),
                                item.getText(),
                                Optional.ofNullable(item.getAnswer()).orElseGet(() -> "'нет'")))
                .collect(Collectors.joining("\n"));

        return !myAppeals.isEmpty() ? myAppeals : "У Вас пока нет обращений";
    }

    public String getNewAppeals() {
        String newAppeals = repository.findByStatus(NEW).stream()
                .map(item ->
                        String.format("№%s - Обращение : %s",
                                item.getId(),
                                item.getText()))
                .collect(Collectors.joining("\n"));

        return !newAppeals.isEmpty() ? newAppeals : "Нет новых обращений";
    }

    public Optional<Appeal> getAppeal(Long id) {

        return repository.findById(id);
    }
}
