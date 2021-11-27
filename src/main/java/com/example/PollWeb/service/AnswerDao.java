package com.example.PollWeb.service;


import com.example.PollWeb.model.Answer;

import java.util.List;

public interface AnswerDao extends Dao<Answer> {
    List<Answer> findByQuestionId(long questionId);
}
