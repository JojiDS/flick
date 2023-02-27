package com.example.demo.controllers;

import com.example.demo.domain.Task;
import com.example.demo.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TaskController {
    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/task")
    public String task(Model model) {
        Iterable<Task> tasks = taskRepo.findAll();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @GetMapping("/task/add")
    public String taskAdd() {
        return "taskAdd";
    }

    @PostMapping("/task/add")
    public String postTaskAdd(@RequestParam String text,
                              @RequestParam String responsible,
                              @RequestParam String status) {
        Task task = new Task(text, responsible, status);
        taskRepo.save(task);
        return "redirect:/task";
    }

    @GetMapping("/task{id}")
    public String taskDetails(@PathVariable(value = "id") long id, Model model) {
        if (!taskRepo.existsById(id)) {
            return "redirect:/task";
        }
        Optional<Task> task = taskRepo.findById(id);
        ArrayList<Task> result = new ArrayList<>();
        task.ifPresent(result::add);
        model.addAttribute("task", result);
        return "taskDetail";
    }

    @GetMapping("/task{id}/edit")
    public String taskDetailsEdit(@PathVariable(value = "id") long id, Model model) {
        if (!taskRepo.existsById(id)) {
            return "redirect:/task";
        }
        Optional<Task> task = taskRepo.findById(id);
        ArrayList<Task> result = new ArrayList<>();
        task.ifPresent(result::add);
        model.addAttribute("task", result);
        return "taskDetailEdit";
    }

    @PostMapping("/task{id}/edit")
    public String postTaskUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String text,
                                 @RequestParam String responsible,
                                 @RequestParam String status,Model model) {
        Task task = taskRepo.findById(id).orElseThrow();
        task.setStatus(status);
        task.setResponsible(responsible);
        task.setText(text);
        taskRepo.save(task);
        return "redirect:/task";
    }

    @PostMapping("/task{id}/remove")
    public String postTaskRemove(@PathVariable(value = "id") long id) {
        Task task = taskRepo.findById(id).orElseThrow();
        taskRepo.delete(task);
        return "redirect:/task";
    }
}
