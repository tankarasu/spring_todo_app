package com.tankarau.spring_todo_app.services;

import com.tankarau.spring_todo_app.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<Task> tasks = new ArrayList<>(Arrays.asList(
            new Task("1", "task 1"),
            new Task("2", "task 2"),
            new Task("3", "task 3")
    ));

    public List<Task> fetchAllTasks() {
        return tasks;
    }

    public Task fetchSpecificTask(String id) {
        return tasks.stream().filter(task -> Objects.equals(task.getId(), id)).findFirst().get();
    }

    public Task createOneTask(Task newTask) {
        tasks.add( newTask);

        return newTask;
    }

    public Task updateSpecificTask(String id, String newDescription) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if(task.getId().equals(id)) tasks.get(i).setTitle(newDescription);
        }

        return fetchSpecificTask(id);
    }

    public Boolean deleteSpecificTask(String id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    public Task switchFinishedState(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if(task.getId().equals(id)) tasks.get(i).setFinished(!tasks.get(i).getFinished());
        }

        return fetchSpecificTask(id);
    }
}
