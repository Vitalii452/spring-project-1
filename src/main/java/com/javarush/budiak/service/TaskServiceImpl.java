package com.javarush.budiak.service;

import com.javarush.budiak.dao.TaskDAO;
import com.javarush.budiak.domain.Task;
import com.javarush.budiak.dto.PaginatedResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;

    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse<Task> findAll(int page, int size) {

        List<Task> tasks = taskDAO.findAll(page, size);
        long totalTasks = taskDAO.countTasks();
        int totalPages = (int) Math.ceil((double) totalTasks / size);

        return new PaginatedResponse<>(tasks, page, totalPages);
    }

    @Override
    @Transactional(readOnly = true)
    public Task findById(int id) {
        return taskDAO.findById(id);
    }

    @Override
    @Transactional
    public Task save(Task task) {
        return taskDAO.save(task);
    }

    @Override
    @Transactional
    public void delete(int id) {
        taskDAO.delete(id);
    }
}
