package com.greatlearning.services;

import com.greatlearning.Dto.QuestionDto;
import com.greatlearning.Dto.QuestionWithAnsDto;
import com.greatlearning.Dto.ServerResponse;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();

    String addQuestion(QuestionDto questionDto);

    List<QuestionWithAnsDto> getAllApprovedQuestionsWithAnsWithTitle(String approved,String title);

    public List<QuestionWithAnsDto> getAllQuestionsWithAns();
    List<QuestionWithAnsDto> getAllApprovedQuestionsWithAns(String approved);

    List<QuestionDto> getAllApprovedQuestions(String approved);
    List<QuestionDto> getApprovedQuestionsByTitle(String approved,String title);

    ServerResponse approveQuestion(int qid);

    ServerResponse deleteQuestion(int qid);
}
