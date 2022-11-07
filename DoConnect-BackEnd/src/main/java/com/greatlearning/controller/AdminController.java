package com.greatlearning.controller;

import com.greatlearning.Dto.ServerResponse;
import com.greatlearning.services.AnswerServiceImpl;
import com.greatlearning.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerServiceImpl answerService;

    @PostMapping("/approveQuestion/{qid}")
    ServerResponse approveQuestion(@PathVariable("qid") int qid) {
        return questionService.approveQuestion(qid);
    }

    @PostMapping("/approveAnswer/{aid}")
    ServerResponse approveAnswer(@PathVariable("aid") int aid) {
        return answerService.approveAnswer(aid);
    }

    @DeleteMapping("/deleteQuestion/{qid}")
    ServerResponse deleteQuestion(@PathVariable("qid") int qid) {
        return questionService.deleteQuestion(qid);
    }

    @DeleteMapping("/deleteAnswer/{aid}")
    ServerResponse deleteAnswer(@PathVariable("aid") int aid) {

        return answerService.deleteAnswer(aid);
    }
}
