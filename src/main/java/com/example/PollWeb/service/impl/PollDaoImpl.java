package com.example.PollWeb.service.impl;



import com.example.PollWeb.db.DBConnectionProvider;
import com.example.PollWeb.model.Poll;
import com.example.PollWeb.service.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PollDaoImpl implements Dao<Poll> {
    QuestionDaoImpl questionDao = new QuestionDaoImpl();
    ResultDaoImpl resultDao = new ResultDaoImpl();
    private Connection connection = DBConnectionProvider.getProvider().getConnection();

    @Override
    public List<Poll> findAll() {
        List<Poll> polls = new ArrayList<Poll>();
        String sql = "SELECT * FROM task ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                polls.add(getPollFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return polls;
    }

    private Poll getPollFromResultSet(ResultSet resultSet) {
        try {
            Poll poll = new Poll();
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            poll.setId(id);
            poll.setName(name);
            poll.setDescription(description);
            return poll;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Poll findById(long id) {
        String sql = "SELECT * FROM poll where id= " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getPollFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
