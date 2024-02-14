package com.javarush.budiak.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "task", schema = "todo")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Task description cannot be empty.")
    @Size(max = 100, message = "Task description cannot be more than 100 characters.")
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @NotNull(message = "Task status must be specified.")
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private Status status;

    public Task() {
    }

    public Task(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public enum Status {
        IN_PROGRESS,
        DONE,
        PAUSED
    }
}
