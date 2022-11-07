package com.greatlearning.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private int q_id;
    private String title;
    private String question;
    private int user_id;
    private String username;
    private String approved;

}
