package be.odisee.jah_bot;

import be.odisee.jah_bot.ai.Question;
import be.odisee.jah_bot.service.JahBotServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.io.IOException;
import java.nio.charset.Charset;

// Cette annotation démarre un faux serveur OpenAI (WireMock)
@EnableWireMock(@ConfigureWireMock(baseUrlProperties = "spring.ai.openai.base-url"))
@SpringBootTest
public class JahBotServiceTest {

    // On charge notre faux fichier JSON créé à l'étape 3
    @Value("classpath:/canned/citymesh-response.json")
    Resource responseResource;

    @Autowired
    ChatClient.Builder chatClientBuilder;

    @BeforeEach
    public void setup() throws IOException {
        // On lit le fichier JSON
        var cannedResponse = responseResource.getContentAsString(Charset.defaultCharset());
        var mapper = new ObjectMapper();
        var responseNode = mapper.readTree(cannedResponse);

        // On dit à WireMock : "Si quelqu'un appelle l'URL /v1/chat/completions, renvoie ce JSON"
        WireMock.stubFor(WireMock.post("/v1/chat/completions")
                .willReturn(ResponseDefinitionBuilder.okForJson(responseNode)));
    }

    @Test
    public void testAskCityMeshQuestion() {
        var service = new JahBotServiceImpl(chatClientBuilder);

        // On pose une question (peu importe laquelle, WireMock renverra toujours la même chose)
        var answer = service.askQuestion(new Question("Que fait CityMesh ?"));

        // On vérifie que la réponse correspond bien à notre fichier JSON
        Assertions.assertThat(answer).isNotNull();
        Assertions.assertThat(answer.answer())
                .contains("Safety Drones"); // On vérifie un mot clé de la réponse
    }
}