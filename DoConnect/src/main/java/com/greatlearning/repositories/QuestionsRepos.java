package com.greatlearning.repositories;

import com.greatlearning.entities.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionsRepos extends JpaRepository<Questions, Integer> {
    List<Questions> findAllByApprovedAndTitle(String approved,String title);
    @Query(value = "select * from questions where approved=?1",nativeQuery = true)
    List<Questions> findAllByApproved(String approved);

    Questions findByQid(int qid);

}
