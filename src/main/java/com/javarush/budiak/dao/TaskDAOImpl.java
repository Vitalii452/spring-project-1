package com.javarush.budiak.dao;

import com.javarush.budiak.domain.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> findAll(int page, int size) {
        TypedQuery<Task> query = entityManager.createQuery("from Task", Task.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public Task findById(int id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public Task save(Task task) {
        if (findById(task.getId()) == null) {
            entityManager.persist(task);
        } else {
            entityManager.merge(task);
        }

        return task;
    }

    @Override
    public void delete(int id) {
        Task task = findById(id);
        if (task != null) {
            entityManager.remove(task);
        }
    }

    @Override
    public long countTasks() {
        return (long) entityManager.createQuery("select count(t) from Task t").getSingleResult();
    }
}
