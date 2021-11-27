package com.example.PollWeb.service.impl;

import com.example.PollWeb.db.DBConnectionProvider;
import com.example.PollWeb.model.Result;
import com.example.PollWeb.service.ResultDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {
    Connection connection = DBConnectionProvider.getProvider().getConnection();

    @Override
    public List<Result> findByPollId(long pollId) {
        String sql = "SELECT * FROM result WHERE poll_id= " +pollId;
        List<Result> resultList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                resultList.add(getResultFromResulSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Result findByScore(long pollId, int score) {
         String sql = "SELECT * FROM result WHERE poll_id =? AND min_score<=? AND max_score>=?";
         try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setLong(1,pollId);
            preparedStatement.setInt(2,score);
            preparedStatement.setInt(3,score);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Result resultFromResulSet = getResultFromResulSet(resultSet);
                return resultFromResulSet;
            }
         } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Result> findAll() {
        String sql = "SELECT * FROM result";
        List<Result> results = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(getResultFromResulSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }


    private Result getResultFromResulSet(ResultSet resultSet) {
        try {
            Result result = new Result();
            long id = resultSet.getLong(1);
            String explaination = resultSet.getString(2);
            int minScore = resultSet.getInt(3);
            int maxScore = resultSet.getInt(4);
            result.setId(id);
            result.setExplanation(explaination);
            result.setMinScore(minScore);
            result.setMaxScore(maxScore);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Result findById(long id) {
        String sql = "SELECT * FROM result WHERE id= " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
            Result resultById = getResultFromResulSet(resultSet);
            return resultById;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
