package com.example.PollWeb.service;


import com.example.PollWeb.model.Question;

import java.util.List;

public interface QuestionDao extends Dao<Question> {
    List<Question> findByPollId(long pollId);
}
