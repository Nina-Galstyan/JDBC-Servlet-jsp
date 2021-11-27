package com.example.PollWeb.model;

import java.util.Objects;

public class Result {
    private long id;
    private String explanation;
    private int minScore;
    private int maxScore;

    public Result(String explanation, int minScore, int maxScore) {
        this.explanation = explanation;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public Result() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return id == result.id && minScore == result.minScore && maxScore == result.maxScore && Objects.equals(explanation, result.explanation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, explanation, minScore, maxScore);
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", explanation='" + explanation + '\'' +
                ", minScore=" + minScore +
                ", maxScore=" + maxScore +
                '}';
    }
}
