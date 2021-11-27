package com.example.PollWeb.service;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();

    T findById(long id);
}
