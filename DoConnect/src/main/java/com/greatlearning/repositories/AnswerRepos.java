package com.greatlearning.repositories;

import com.greatlearning.entities.Answers;
import com.greatlearning.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepos  extends JpaRepository<Answers,Integer> {

    @Query(value = "select * from answers where a_id=?1",nativeQuery = true)
    Answers findByA_id(int aid);


    List<Answers> findAllByApproved(String approved);
    List<Answers> findAllByApprovedAndQuestions(String approved, Questions questions);
    List<Answers> findAllByQuestions(Questions questions);
}
