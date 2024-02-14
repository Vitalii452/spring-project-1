package com.javarush.budiak.service;

import com.javarush.budiak.domain.Task;
import com.javarush.budiak.dto.PaginatedResponse;

public interface TaskService {

    PaginatedResponse<Task> findAll(int page, int size);

    Task findById(int id);

    Task save(Task task);

    void delete(int id);
}
