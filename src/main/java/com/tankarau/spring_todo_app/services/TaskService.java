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
        return tasks.stream().filter(task -> Objects.equals(task.getId(), id))
                .findFirst()
                .get();
    }

    public Task createOneTask(Task newTask) {
        tasks.add(newTask);

        return newTask;
    }

    public Task updateSpecificTask(String id, Task updatedTask) {
        for (Task task : tasks)
            if (task.getId().equals(id))
                tasks.set(tasks.indexOf(task), updatedTask);

        return fetchSpecificTask(id);
    }

    public Boolean deleteSpecificTask(String id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}
