package com.greatlearning.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithAnsDto {

    private QuestionDto questionDto;
    private List<AnswerDto> answerDtos;


}
