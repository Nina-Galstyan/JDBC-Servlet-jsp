package com.example.PollWeb.model;

import java.util.List;
import java.util.Objects;

public class Poll {
    private long id;
    private String name;
    private String description;
    private List<Question> questions;
    private List<Result> results;

    public Poll(String name, String description, List<Question> questions, List<Result> results) {
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.results = results;
    }

    public Poll() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return id == poll.id && Objects.equals(name, poll.name) && Objects.equals(description, poll.description) && Objects.equals(questions, poll.questions) && Objects.equals(results, poll.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, questions, results);
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                ", results=" + results +
                '}';
    }
}
