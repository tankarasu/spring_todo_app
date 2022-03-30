package com.tankarau.spring_todo_app.controllers;

// Internal requirements

import com.jayway.jsonpath.JsonPath;
import com.tankarau.spring_todo_app.models.Todo;
// Third-Party requirements
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// Core-module requirements
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    // FIXTURES
    List<Todo> initialTaskList = Arrays.asList(
            new Todo("1", "todo 1"),
            new Todo("2", "todo 2"),
            new Todo("3", "todo 3")
    );

    String initialTaskListJSON = "[{\"id\":\"1\",\"title\":\"todo 1\"," +
            "\"finished\":false},{\"id\":\"2\",\"title\":\"todo 2\",\"finished\":false},{\"id\":\"3\",\"title\":\"todo 3\",\"finished\":false}]";

    String taskTwoJSON = "{\"id\":\"2\",\"title\":\"todo 2\",\"finished\":false}";

    @Test
    void getAllTodos() throws Exception {
        this.mvc.perform(get("/todos").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(initialTaskListJSON));
    }

    @Test
    void getTodo() throws Exception {
        String idToFetch = "2";
        String url = "/todos/" + idToFetch;

        this.mvc.perform(get(url).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(taskTwoJSON));
    }

    @Test
    void createTodo() throws Exception {
        // GIVEN we want to create a new Task
        String newTodo = "{\"id\":\"4\",\"title\":\"todo 4\"}";
        String returnedTask = "{\"id\":\"4\",\"title\":\"todo 4\"," +
                "\"finished\":false}";

        // THEN the task should be created
        this.mvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTodo)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(returnedTask));
    }

    @Test
    void updateTodo() throws Exception {
        // GIVEN we want to update a task
        String returnedTask = "{\"id\":\"1\",\"title\":\"todo ONE\"," +
                "\"finished\":false}";

        // THEN the task should be updated
        this.mvc.perform(put("/todos?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("todo ONE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(returnedTask));
    }

    @Test
    void deleteTodo() throws Exception {
        this.mvc.perform(delete("/todos?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void handleFinishedState() throws Exception {
        String returnedTask = "{\"id\":\"1\",\"title\":\"todo 1\"," +
                "\"finished\":true}";

        this.mvc.perform(put("/todos/finished?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(returnedTask));

        this.mvc.perform(put("/todos/finished?id=1"));
    }
}