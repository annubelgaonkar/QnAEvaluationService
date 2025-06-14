package dev.qna.qna_evaluation_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EvaluationRequestDTO {
    private String topic;
    private String question;
    private String userAnswer;

}
