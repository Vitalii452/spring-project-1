package com.javarush.budiak.controller;

import com.javarush.budiak.domain.Task;
import com.javarush.budiak.dto.PaginatedResponse;
import com.javarush.budiak.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String redirectToTasks() {
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String listTasks(Model model,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "5") int size) {

        PaginatedResponse<?> paginatedResponse = taskService.findAll(page, size);
        model.addAttribute("paginatedResponse", paginatedResponse);

        return "tasks/list";
    }

    @GetMapping("/tasks/new")
    public String showNewTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("allStatuses", Task.Status.values());
        return "tasks/form";
    }

    @PostMapping("/tasks")
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            model.addAttribute("allStatuses", Task.Status.values());
            return "tasks/form";
        }
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String showEditTaskForm(@PathVariable int id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("allStatuses", Task.Status.values());
        return "tasks/form";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/";
    }
}
