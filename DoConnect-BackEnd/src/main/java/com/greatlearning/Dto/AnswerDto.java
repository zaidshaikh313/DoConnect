package com.greatlearning.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

    private int a_id;
    private String answer;
    private int q_id;
    private String username;
    private byte[] returnedImage;
    public MultipartFile image;

}
