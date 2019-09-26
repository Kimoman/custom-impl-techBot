package ru.proj.study.telegram.techbot.controller;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Timed
    @GetMapping("/hello")
    public ResponseEntity<Hello> hello() {
        return ResponseEntity.ok(new Hello("Hello!"));
    }

    public static class Hello {
        private final String value;

        public Hello(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
