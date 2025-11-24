package be.odisee.jah_bot.service;

import be.odisee.jah_bot.ai.Answer;
import be.odisee.jah_bot.ai.Question;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.stereotype.Service;

@Service
public class JahBotServiceImpl implements JahBotService {

    private final ChatClient chatClient;

    public JahBotServiceImpl(ChatClient.Builder chatClientBuilder) {
            this.chatClient = chatClientBuilder.build();
    }

    @Override
    public Answer askQuestion (Question question){
        var answerText = chatClient.prompt()
                .user(question.question())
                .call()
                .content();
        return new Answer(answerText);
    }
}
