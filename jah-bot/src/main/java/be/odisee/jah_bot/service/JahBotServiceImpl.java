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
    public Answer askQuestion(Question question) {
        // Le System Prompt pour CityMesh
        var answerText = chatClient.prompt()
                .system("Tu es un assistant expert pour CityMesh, la plateforme de gestion de drones." +
                        "Réponds aux questions des pilotes de manière professionnelle et concise." +
                        "Si tu ne connais pas la réponse, dirige-les vers le support technique.")
                .user(question.question())
                .call()
                .content();

        return new Answer(answerText);
    }
}