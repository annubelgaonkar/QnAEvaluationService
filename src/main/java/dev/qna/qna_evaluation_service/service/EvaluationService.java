package dev.qna.qna_evaluation_service.service;

import dev.qna.qna_evaluation_service.dto.EvaluationRequestDTO;
import dev.qna.qna_evaluation_service.dto.EvaluationResponseDTO;
import dev.qna.qna_evaluation_service.llm.LLMClient;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    private final LLMClient llmClient;
    public EvaluationService(LLMClient llmClient) {
        this.llmClient = llmClient;
    }

    public EvaluationResponseDTO evaluate(EvaluationRequestDTO evaluationRequestDTO) {
        return llmClient.evaluateAnswer(
                evaluationRequestDTO.getTopic(),
                evaluationRequestDTO.getQuestion(),
                evaluationRequestDTO.getUserAnswer()
        );
    }
}
