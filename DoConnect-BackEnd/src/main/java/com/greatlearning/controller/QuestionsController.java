package com.greatlearning.controller;

import com.greatlearning.Dto.QuestionDto;
import com.greatlearning.Dto.QuestionWithAnsDto;
import com.greatlearning.Dto.ServerResponse;
import com.greatlearning.repositories.QuestionsRepos;
import com.greatlearning.services.EmailService;
import com.greatlearning.services.QuestionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    private QuestionsServiceImpl questionService;
    @Autowired
    private QuestionsRepos questionsRepos;
    @Autowired
    private EmailService emailService;


    @GetMapping("/approved/{approved}")
    List<QuestionWithAnsDto> getAllApprovedQuestionsWithAns(@PathVariable("approved") String approved){
        return questionService.getAllApprovedQuestionsWithAns(approved);
    }

    @GetMapping("/{title}")
    List<QuestionWithAnsDto> getAllApprovedQuestionsWithAnsByTitle(@PathVariable("title") String title){
        return questionService.getAllApprovedQuestionsWithAnsWithTitle("true",title);
    }
    @GetMapping("/all")
    List<QuestionDto> getAllQuestions(){
        return questionService.getAllQuestions();
    }


    @PostMapping("/add")
    ServerResponse addQuestion(@RequestBody QuestionDto questionDto){
        ServerResponse response=new ServerResponse();
        questionService.addQuestion(questionDto);
        emailService.send("New Question is asked","Need your approval for new Question asked" );
        response.setMessage("Question added successfully and approval is pending");
        response.setStatus(HttpStatus.OK);
        return  response;
    }

    @GetMapping("/getByApproved/{approved}")
    List<QuestionDto> getAllByApproved(@PathVariable("approved") String approved){
        return  questionService.getAllApprovedQuestions(approved);
    }

}
