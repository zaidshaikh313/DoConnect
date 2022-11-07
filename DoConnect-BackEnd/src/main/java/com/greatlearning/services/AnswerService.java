package com.greatlearning.services;

import com.greatlearning.Dto.AnswerDto;
import com.greatlearning.Dto.ServerResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface AnswerService {

    public List<AnswerDto> getApprovedAnswerByQid(String approved, int qid);

    public ServerResponse addAnswer(int qid,AnswerDto answerDto) throws IOException;

    public ServerResponse approveAnswer(int aid);

    public ServerResponse deleteAnswer(int aid);


}
