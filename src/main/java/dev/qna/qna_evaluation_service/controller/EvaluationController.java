package dev.qna.qna_evaluation_service.controller;

import dev.qna.qna_evaluation_service.dto.EvaluationRequestDTO;
import dev.qna.qna_evaluation_service.dto.EvaluationResponseDTO;
import dev.qna.qna_evaluation_service.service.EvaluationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluate")
public class EvaluationController {


    public final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public EvaluationResponseDTO evaluate(@RequestBody EvaluationRequestDTO evaluationRequestDTO) {
        return evaluationService.evaluate(evaluationRequestDTO);
    }

}
