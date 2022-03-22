package com.tankarau.spring_todo_app.controllers;

// Internals requirements

import com.tankarau.spring_todo_app.models.Todo;
// Third-Party requirements
import com.tankarau.spring_todo_app.services.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// Core module requirements
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @Autowired
    private TodoService todoService;

    // GIVEN there's some todos to fetch
    // WHEN we invoke the methods
    // THEN we should have the todos displayed
    @Test
    void getAllTodos() {
        List<Todo> expectedTodoList = Arrays.asList(
                new Todo("1", "todo 1"),
                new Todo("2", "todo 2"),
                new Todo("3", "todo 3")
        );

        assertThat(todoService.fetchAllTodos())
                .usingRecursiveComparison()
                .isEqualTo(expectedTodoList);
    }

    @Test
    void getTodo() {
        Todo expectedTodo = new Todo("1", "todo 1");

        assertThat(todoService.fetchSpecificTodo("1"))
                .usingRecursiveComparison()
                .isEqualTo(expectedTodo);
    }

    @Test
    void createTodo() {
        List<Todo> expectedTodoList = Arrays.asList(
                new Todo("1", "todo 1"),
                new Todo("2", "todo 2"),
                new Todo("3", "todo 3"),
                new Todo("4", "todo 4")
        );

        Todo newTodo = new Todo("4", "todo 4");
        todoService.createOneTodo(newTodo);

        assertThat(todoService.fetchAllTodos())
                .usingRecursiveComparison()
                .isEqualTo(expectedTodoList);
    }

    @Test
    void updateTodo() {
    }

    @Test
    void deleteTodo() {
    }

    @Test
    void handleFinishedState() {
    }
}