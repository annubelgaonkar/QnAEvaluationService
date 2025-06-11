package dev.qna.qna_evaluation_service.dto;

public class EvaluationResponseDTO {
    private String feedback;

    public EvaluationResponseDTO(String feedback) {
        this.feedback = feedback;
    }
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
