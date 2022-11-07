package com.greatlearning.repos;

import com.greatlearning.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepos extends JpaRepository<Chat,Integer> {

}
