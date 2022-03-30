package com.tankarau.spring_todo_app.controllers;

// Internals requirements
import com.tankarau.spring_todo_app.models.Task;
import com.tankarau.spring_todo_app.services.TaskService;
// Third-Party requirements
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// Core module requirements
import java.util.List;

@RestController
public class TaskController {
    @Autowired()
    private TaskService taskService = new TaskService();

    /**
     * it used to fetch All tasks
     *
     * @return an Array of all tasks
     */
    @GetMapping("/tasks")
    @ResponseBody
    public List<Task> getAllTasks() {
        return taskService.fetchAllTasks();
    }

    /**
     * get a specific task
     *
     * @param id required ID of the specified task
     * @return the specified task if exist
     */
    @GetMapping("/tasks/{id}")
    @ResponseBody
    public Task getTask(@PathVariable String id) {
        return taskService.fetchSpecificTask(id);
    }

    /**
     * used to create a new task
     *
     * @param task valid task for creating
     */
    @PostMapping("/tasks")
    @ResponseBody
    public Task createTask(@RequestBody Task task) {
        return taskService.createOneTask(task);
    }

    /**
     * used to update an existing task
     *
     * @param id valid ID for existing task
     */
    @PutMapping("/tasks")
    @ResponseBody
    public Task updateTask(@RequestParam String id,
                           @RequestBody String newDescription) {
        return taskService.updateSpecificTask(id, newDescription);
    }

    /**
     * used for deleting an existing task
     *
     * @param id id of the task to delete
     */
    @DeleteMapping("/tasks")
    public Boolean deleteTask(@RequestParam String id) {
        return taskService.deleteSpecificTask(id);
    }

    /**
     * used to switch between finished/not finished task
     *
     * @param id id of the task
     */
    @PutMapping("/tasks/finished")
    public Task handleFinishedState(@RequestParam String id) {
        return taskService.switchFinishedState(id);
    }

}