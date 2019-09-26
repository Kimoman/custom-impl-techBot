package ru.proj.study.telegram.techbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class StudyTelegramTechbotApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(StudyTelegramTechbotApplication.class, args);
    }
}
