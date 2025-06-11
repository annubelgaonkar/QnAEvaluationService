package dev.qna.qna_evaluation_service.llm;

import dev.qna.qna_evaluation_service.dto.EvaluationResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class LLMClient {

    private final WebClient webClient;

    public LLMClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.model}")
    private String model;

    public EvaluationResponseDTO evaluateAnswer(String topic,
                                                String question,
                                                String userAnswer) {
        String prompt = String.format(
                "I am learning about %s. Here's a question: \"%s\"\nMy answer: \"%s\"\n" +
                        "Please evaluate my answer. Correct me if I'm wrong, and explain the right answer if needed.",
                topic, question, userAnswer
        );

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", new Object[]{
                        Map.of("role", "system", "content", "You are an expert teacher who gives constructive feedback."),
                        Map.of("role", "user", "content", prompt)
                },
                "temperature", 0.7
        );
        Map response = webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        String feedback = ((Map)((Map)((java.util.List)response.get("choices")).get(0)).get("message")).get("content").toString();

        return new EvaluationResponseDTO(feedback.trim());
    }
}
