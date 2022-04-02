package com.tankarau.spring_todo_app.services;

// Internal requirements
import com.tankarau.spring_todo_app.models.Task;

// Third-party requirements
import org.springframework.stereotype.Service;

// Core-module requirements
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    /**
     * For Testing Purpose
     * @param tasks initial taskList for the parametrized testing
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<Task> tasks = new ArrayList<>(Arrays.asList(
            new Task("1", "task 1"),
            new Task("2", "task 2"),
            new Task("3", "task 3")
    ));

    /**
     * used to fetch all available tasks
     * @return a list of ask List
     */
    public List<Task> fetchAllTasks() {
        return tasks;
    }

    /**
     * used to fetch a specific tasks
     * @param id id of specific task
     * @return a specific Task
     */
    public Task fetchSpecificTask(String id) {
        return tasks.stream().filter(task -> Objects.equals(task.getId(), id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Used for task creation
     * @param newTask task to be created
     * @return return the newly created task
     */
    public Task createOneTask(Task newTask) {
        tasks.add(newTask);

        return newTask;
    }

    /**
     * used for updating a Task
     * @param id specific task ID
     * @param updatedTask updated Task
     * @return the updated Task if success
     */
    public Task updateSpecificTask(String id, Task updatedTask) {
        for (Task task : tasks)
            if (task.getId().equals(id))
                tasks.set(tasks.indexOf(task), updatedTask);

        return fetchSpecificTask(id);
    }

    /**
     * used to delete a specific Task
     * @param id the specific Task to delete
     * @return True if success otherwise false
     */
    public Boolean deleteSpecificTask(String id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}
