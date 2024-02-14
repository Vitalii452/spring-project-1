package com.javarush.budiak.dao;

import com.javarush.budiak.domain.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> findAll(int page, int size);

    Task findById(int id);

    Task save(Task task);

    void delete(int id);

    long countTasks();
}
