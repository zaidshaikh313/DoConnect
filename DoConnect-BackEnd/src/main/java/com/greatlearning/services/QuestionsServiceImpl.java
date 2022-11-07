package com.greatlearning.services;

import com.greatlearning.Dto.AnswerDto;
import com.greatlearning.Dto.QuestionDto;
import com.greatlearning.Dto.QuestionWithAnsDto;
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
import java.util.zip.Inflater;

@Transactional
@Service
public class QuestionsServiceImpl implements QuestionService {
    @Autowired
    private UserRepos userRepos;

    @Autowired
    private AnswerRepos answerRepos;
    @Autowired
    private QuestionsRepos questionsRepos;

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<QuestionDto> questionDtos = new ArrayList<>();

        questionsRepos.findAll().forEach(questions -> {
            questionDtos.add(convertQuestionToQuestionDto(questions));
        });
        return questionDtos;
    }

    @Override
    public String addQuestion(QuestionDto questionDto) {
        User user=userRepos.findByUsername(questionDto.getUsername()).get();

        Questions questions=new Questions();
        questions.setQuestion(questionDto.getQuestion());
        questions.setTitle(questionDto.getTitle());
        questions.setApproved("false");
        questions.setUser(user);
        questionsRepos.save(questions);
        return "Question is added successfully";
    }

    public List<QuestionWithAnsDto> getAllQuestionsWithAns() {
        List<QuestionWithAnsDto> questionWithAnsDtos = new ArrayList<>();


        questionsRepos.findAll().forEach(questions -> {
            QuestionWithAnsDto questionWithAnsDto = new QuestionWithAnsDto();
            List<AnswerDto> answerDtos1 = new ArrayList<>();
            QuestionDto questionDto2 = convertQuestionToQuestionDto(questions);
            questionWithAnsDto.setQuestionDto(questionDto2);
            List<Answers> answers = answerRepos.findAllByQuestions( questions);
            answers.forEach(answers1 -> {
                AnswerDto answerDto = convertAnswerToAnswerDto(answers1);

                answerDtos1.add(answerDto);

            });
            questionWithAnsDto.setAnswerDtos(answerDtos1);
            questionWithAnsDtos.add(questionWithAnsDto);

        });


        return questionWithAnsDtos;
    }


    @Override
    public List<QuestionWithAnsDto> getAllApprovedQuestionsWithAnsWithTitle(String approved,String title) {
        List<QuestionWithAnsDto> questionWithAnsDtos = new ArrayList<>();
        

        questionsRepos.findAllByApprovedAndTitle("true",title).forEach(questions -> {
            QuestionWithAnsDto questionWithAnsDto = new QuestionWithAnsDto();
            List<AnswerDto> answerDtos1 = new ArrayList<>();
            QuestionDto questionDto2 = convertQuestionToQuestionDto(questions);
            questionWithAnsDto.setQuestionDto(questionDto2);
            List<Answers> answers = answerRepos.findAllByApprovedAndQuestions(approved, questions);
            answers.forEach(answers1 -> {
                AnswerDto answerDto = convertAnswerToAnswerDto(answers1);

                answerDtos1.add(answerDto);

            });
            questionWithAnsDto.setAnswerDtos(answerDtos1);
            questionWithAnsDtos.add(questionWithAnsDto);

        });


        return questionWithAnsDtos;
    }

    @Override
    public List<QuestionWithAnsDto> getAllApprovedQuestionsWithAns(String approved) {
        List<QuestionWithAnsDto> questionWithAnsDtos = new ArrayList<>();

        questionsRepos.findAllByApproved("true").forEach(questions -> {
            QuestionWithAnsDto questionWithAnsDto = new QuestionWithAnsDto();
            List<AnswerDto> answerDtos1 = new ArrayList<>();
            QuestionDto questionDto2 = convertQuestionToQuestionDto(questions);
            questionWithAnsDto.setQuestionDto(questionDto2);
            List<Answers> answers = answerRepos.findAllByApprovedAndQuestions(approved, questions);
            answers.forEach(answers1 -> {
                AnswerDto answerDto = convertAnswerToAnswerDto(answers1);

                answerDtos1.add(answerDto);

            });
            questionWithAnsDto.setAnswerDtos(answerDtos1);
            questionWithAnsDtos.add(questionWithAnsDto);

        });


        return questionWithAnsDtos;

    }

    @Override
    public List<QuestionDto> getAllApprovedQuestions(String approved) {
        List<QuestionDto> questionDtos = new ArrayList<>();

        questionsRepos.findAllByApproved(approved).forEach(questions -> {
            questionDtos.add(convertQuestionToQuestionDto(questions));
        });
        return questionDtos;
    }

    @Override
    public List<QuestionDto> getApprovedQuestionsByTitle(String approved, String title) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        questionsRepos.findAllByApprovedAndTitle(approved, title).forEach(questions -> {
            questionDtos.add(convertQuestionToQuestionDto(questions));
        });
        return questionDtos;
    }

    @Override
    public ServerResponse approveQuestion(int qid) {
        Questions question = questionsRepos.findByQid(qid);
        question.setApproved("true");
        questionsRepos.save(question);
        ServerResponse response=new ServerResponse();
        response.setStatus(HttpStatus.OK);
        response.setMessage("Question Approved Successfully");
        return response;
    }

    @Override
    public ServerResponse deleteQuestion(int qid) {

        questionsRepos.deleteById(qid);
        ServerResponse serverResponse=new ServerResponse();
        serverResponse.setStatus(HttpStatus.OK);
        serverResponse.setMessage("Question deleted successfully");
        return serverResponse;
    }

    QuestionDto convertQuestionToQuestionDto(Questions questions) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setApproved(questions.getApproved());
        questionDto.setTitle(questions.getTitle());
        questionDto.setUsername(questions.getUser().getUsername());
        questionDto.setQuestion(questions.getQuestion());
        questionDto.setUser_id(questions.getUser().getUser_id());
        questionDto.setQ_id(questions.getQid());
        return questionDto;
    }


    AnswerDto convertAnswerToAnswerDto(Answers answers) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setA_id(answers.getA_id());
        answerDto.setAnswer(answers.getAnswer());
        if (answers.getImage()!=null) {

            answerDto.setReturnedImage(decompressBytes(answers.getImage()));
        }
        answerDto.setUsername(answers.getUser().getUsername());
        answerDto.setQ_id(answers.getQuestions().getQid());
        return answerDto;
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
