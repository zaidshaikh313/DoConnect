package com.greatlearning.controller;


import com.greatlearning.Dto.AnswerDto;
import com.greatlearning.Dto.QuestionWithAnsDto;
import com.greatlearning.Dto.ServerResponse;
import com.greatlearning.entities.Answers;
import com.greatlearning.entities.Questions;
import com.greatlearning.repositories.AnswerRepos;
import com.greatlearning.services.AnswerServiceImpl;

import com.greatlearning.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerRepos answerRepos;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerServiceImpl answerService;

    @GetMapping("/all")
    public List<QuestionWithAnsDto> getAllQuestionsWithAns(){
        return questionService.getAllQuestionsWithAns();
    }
    @GetMapping("/notApproved")
    List<QuestionWithAnsDto> getAllNotApprovedQuestionsWithAns() {
        return questionService.getAllApprovedQuestionsWithAns("false");
    }

    @GetMapping("/{approved}/{qid}")
    public List<AnswerDto> getApprovedAnswerByQid(@PathVariable("approved") String approved, @PathVariable("qid") int qid) {
        return answerService.getApprovedAnswerByQid(approved, qid);
    }

    @PostMapping("/add/{qid}")
    public ServerResponse addAnswer(@PathVariable("qid")int qid, @ModelAttribute AnswerDto answerDto) {
        try {
            return answerService.addAnswer(qid,answerDto);
        } catch (IOException e) {
            ServerResponse response = new ServerResponse();
            response.setMessage("Something went wrong");
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
    }

}
