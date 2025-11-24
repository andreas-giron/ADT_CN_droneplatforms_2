package be.odisee.jah_bot.controller;

import be.odisee.jah_bot.ai.*;

import be.odisee.jah_bot.service.JahBotService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskController {

    private final JahBotService jahBotService;

    public AskController(JahBotService jahBotService) {
        this.jahBotService = jahBotService;
    }

    @PostMapping(path="/ask", produces="application/json")
    public Answer ask(@RequestBody Question question) {
        return jahBotService.askQuestion(question);
    }
}
