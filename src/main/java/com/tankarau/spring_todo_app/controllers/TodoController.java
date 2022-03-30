package com.tankarau.spring_todo_app.controllers;

// Internals requirements
import com.tankarau.spring_todo_app.models.Todo;
import com.tankarau.spring_todo_app.services.TodoService;
// Third-Party requirements
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// Core module requirements
import javax.print.MultiDoc;
import java.util.List;

@RestController
public class TodoController {
    @Autowired(required = true)
    private TodoService todoService = new TodoService();

    /**
     * it used to fetch All todos
     *
     * @return an Array of all todos
     */
    @GetMapping("/todos")
    @ResponseBody
    public List<Todo> getAllTodos() {
        return todoService.fetchAllTodos();
    }

    /**
     * get a specific Todo
     *
     * @param id required ID of the specified todo
     * @return the specified todo if exist
     */
    @GetMapping("/todos/{id}")
    @ResponseBody
    public Todo getTodo(@PathVariable String id) {
        return todoService.fetchSpecificTodo(id);
    }

    /**
     * used to create a new todo
     *
     * @param todo valid todo for creating
     */
    public void createTodo(Todo todo) {

    }

    /**
     * used to update an existing todo
     *
     * @param id valid ID for existing todo
     */
    public void updateTodo(String id) {

    }

    /**
     * used for deleting an existing todo
     *
     * @param id id of the todo to delete
     */
    public void deleteTodo(String id) {

    }

    /**
     * used to switch between finished/not finished todo
     *
     * @param id id of the todo
     */
    public void handleFinishedState(String id) {

    }

}
