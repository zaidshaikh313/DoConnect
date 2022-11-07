package com.greatlearning.services;
import com.greatlearning.Dto.AnswerDto;
import com.greatlearning.Dto.ServerResponse;
import com.greatlearning.entities.Answers;
import com.greatlearning.entities.Questions;
import com.greatlearning.entities.User;
import com.greatlearning.repositories.AnswerRepos;
import com.greatlearning.repositories.QuestionsRepos;
import com.greatlearning.repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Transactional
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AnswerRepos answerRepos;
    @Autowired
    private QuestionsRepos questionsRepos;
    @Autowired
    private QuestionsServiceImpl questionsService;

    public List<AnswerDto> getApprovedAnswerByQid(String approved, int qid){
        List<AnswerDto> answerDtos=new ArrayList<>();
        Questions questions=questionsRepos.findByQid(qid);
        answerRepos.findAllByApprovedAndQuestions(approved,questions).forEach(answers1 -> {
            AnswerDto answerDto = questionsService.convertAnswerToAnswerDto(answers1);

            answerDtos.add(answerDto);

        });
        return answerDtos;

    }

    public ServerResponse addAnswer(int qid,AnswerDto answerDto) throws IOException {
        User user =userRepos.findByUsername(answerDto.getUsername()).get();
        Questions questions=questionsRepos.findByQid(qid);
        Answers answers=new Answers();
        answers.setQuestions(questions);
        answers.setAnswer(answerDto.getAnswer());
        answers.setApproved("false");
        answers.setUser(user);
        if (answerDto.getImage()!=null){
            answers.setImage(compressBytes(answerDto.getImage().getBytes()));
        }

        ServerResponse response=new ServerResponse();
        answerRepos.save(answers);
        emailService.send("New Answer is added","Need your approval for added answer" );
        response.setStatus(HttpStatus.OK);
        response.setMessage("Answer added successfully and approval is pending");
        return response;

    }

    public ServerResponse approveAnswer(int aid){
        Answers answers=answerRepos.findByA_id(aid);
        answers.setApproved("true");
        answerRepos.save(answers);
        ServerResponse serverResponse =new ServerResponse();
        serverResponse.setMessage("Answer approved successfully");
        serverResponse.setStatus(HttpStatus.OK);
        return serverResponse;
    }

   public ServerResponse deleteAnswer(int aid){
        answerRepos.deleteById(aid);
        ServerResponse response=new ServerResponse("Answer deleted successfully",HttpStatus.OK);
        return response;
    }
    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }


}

