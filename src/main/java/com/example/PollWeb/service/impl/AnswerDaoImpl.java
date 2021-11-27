package com.example.PollWeb.service.impl;


import com.example.PollWeb.db.DBConnectionProvider;
import com.example.PollWeb.model.Answer;
import com.example.PollWeb.service.AnswerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    Connection connection = DBConnectionProvider.getProvider().getConnection();

    @Override
    public List<Answer> findByQuestionId(long questionId) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT * FROM answer WHERE question_id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answers.add(getAnswerFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answers;
    }

    @Override
    public List<Answer> findAll() {
        String sql = "SELECT * FROM answer";
        List<Answer> answers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answers.add(getAnswerFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answers;
    }

    private Answer getAnswerFromResultSet(ResultSet resultSet) {
        try {
            Answer answer = new Answer();
            long id = resultSet.getLong(1);
            String text = resultSet.getString(2);
            int weight = resultSet.getInt(3);
            answer.setId(id);
            answer.setText(text);
            answer.setWeight(weight);
            return answer;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    @Override
    public Answer findById(long id) {
        String sql = "SELECT * FROM answer WHERE id= " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getAnswerFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
