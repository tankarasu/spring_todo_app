package com.tankarau.spring_todo_app.controllers;

// Internals requirements

import com.tankarau.spring_todo_app.models.Todo;

// Third-Party requirements
import com.tankarau.spring_todo_app.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Core module requirements
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {
    List<Todo> initialTaskList = Arrays.asList(
            new Todo("1", "todo 1"),
            new Todo("2", "todo 2"),
            new Todo("3", "todo 3")
    );

    @Autowired
    private TodoService todoService;

    @BeforeEach
    public void initEach() {
        todoService.setTodos(new ArrayList<>(initialTaskList));
    }

    @Test
    void getAllTodos() {
        // GIVEN there's some tasks to fetch
        // WHEN we invoke the methods
        // THEN we should have the todos fetched
        assertThat(todoService.fetchAllTodos())
                .usingRecursiveComparison()
                .isEqualTo(initialTaskList);
    }

    @Test
    void getTodo() {
        // GIVEN we want to fetch one specific Task
        Todo expectedTodo = new Todo("1", "todo 1");

        // WHEN we invoke method with specific ID
        // THEN we should fetch the specified task
        assertThat(todoService.fetchSpecificTodo("1"))
                .usingRecursiveComparison()
                .isEqualTo(expectedTodo);
    }

    @Test
    void createTodo() {
        // GIVEN we want to create a new Task
        List<Todo> expectedTodoList = Arrays.asList(
                new Todo("1", "todo 1"),
                new Todo("2", "todo 2"),
                new Todo("3", "todo 3"),
                new Todo("4", "todo 4")
        );

        // WHEN we invoke method with the Task in param
        Todo newTodo = new Todo("4", "todo 4");
        todoService.createOneTodo(newTodo);

        // THEN we should have created the specified task
        assertThat(todoService.fetchAllTodos())
                .usingRecursiveComparison()
                .isEqualTo(expectedTodoList);
    }

    @Test
    void updateTodo() {
        // GIVEN we want update a task description
        List<Todo> initialTodoList = Arrays.asList(
                new Todo("1", "todo 1"),
                new Todo("2", "todo 2"),
                new Todo("3", "todo 3")
        );
        // WHEN we provide the new description to the specified task
        todoService.updateSpecificTask("1", "todo ONE");
        // THEN the description of specified task should be updated
        assertThat(todoService.fetchSpecificTodo("1").getTitle())
                .isEqualTo("todo ONE");
    }

    @Test
    void deleteTodo() {
        // GIVEN we want to delete a task
        List<Todo> finalTaskList = Arrays.asList(
                new Todo("1", "todo 1"),
                new Todo("2", "todo 2")
        );
        // WHEN we invoke method with specified ID
        todoService.deleteSpecificTodo("3");
        // THEN the task should be deleted
        assertThat(todoService.fetchAllTodos())
                .usingRecursiveComparison()
                .isEqualTo(finalTaskList);
    }

    @Test
    void handleFinishedState() {
        // GIVEN we have a specific not finished task
        List<Todo> taskList = Arrays.asList(
                new Todo("1", "todo 1"),
                new Todo("2", "todo 2"),
                new Todo("3", "todo 3")
        );
        // WHEN we want to change the state to finished
        todoService.switchFinishedState("1");
        // THEN the task state must change
        assertThat(todoService.fetchSpecificTodo("1").getFinished())
                .isEqualTo(true);
    }
}