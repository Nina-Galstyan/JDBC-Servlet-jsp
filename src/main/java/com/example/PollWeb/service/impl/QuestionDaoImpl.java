package com.example.PollWeb.service.impl;


import com.example.PollWeb.db.DBConnectionProvider;
import com.example.PollWeb.model.Question;
import com.example.PollWeb.service.QuestionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private Connection connection = DBConnectionProvider.getProvider().getConnection();
    AnswerDaoImpl answerDao = new AnswerDaoImpl();
    @Override
    public List<Question> findByPollId(long pollId) {
        String sql = "SELECT * FROM question WHERE poll_id= " + pollId;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return (List<Question>) getQuestionFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Question> findAll() {
        String sql= "SELECT * FROM question";
        List<Question> questionList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                questionList.add(getQuestionFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionList;
    }

    private Question getQuestionFromResultSet(ResultSet resultSet) {
            try {
                Question question = new Question();
                long id = resultSet.getLong(1);
                String text = resultSet.getString(2);
                question.setId(id);
                question.setText(text);
                return question;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }

    @Override
    public Question findById(long id) {
        String sql = "SELECT * FROM question WHERE id=" + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
         if (resultSet.next()){
             return getQuestionFromResultSet(resultSet);
         }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
