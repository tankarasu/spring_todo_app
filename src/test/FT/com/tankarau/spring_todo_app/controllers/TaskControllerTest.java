package com.tankarau.spring_todo_app.controllers;

// Third-Party requirements
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    // FIXTURES
    String initialTaskListJSON = "[{\"id\":\"1\",\"title\":\"task 1\"," +
            "\"finished\":false},{\"id\":\"2\",\"title\":\"task 2\"," +
            "\"finished\":false},{\"id\":\"3\",\"title\":\"task 3\"," +
            "\"finished\":false}]";

    String taskTwoJSON = "{\"id\":\"2\",\"title\":\"task 2\"," +
            "\"finished\":false}";

    @Test
    void getAllTasks() throws Exception {
    // GIVEN we want to fetch all available task
    // THEN all task SHOULD be fetched
        this.mvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(initialTaskListJSON));
    }

    @Test
    void getTodo() throws Exception {
        // GIVEN we want to fetch one specific Task
        String idToFetch = "2";

        // WHEN we invoke the method
        String url = "/tasks/" + idToFetch;

        // THEN should the task fetched
        this.mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
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
        this.mvc.perform(post("/tasks")
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
        this.mvc.perform(put("/tasks?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("todo ONE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(returnedTask));
    }

    @Test
    void deleteTodo() throws Exception {
        this.mvc.perform(delete("/tasks?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void handleFinishedState() throws Exception {
        // GIVEN we want to switch the FinishedState of a specific task
        String returnedTask = "{\"id\":\"1\",\"title\":\"task 1\"," +
                "\"finished\":true}";

        // THEN should state be changed: true <-> false
        this.mvc.perform(put("/tasks/finished?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(returnedTask));

        // Fix to get previous state
        this.mvc.perform(put("/tasks/finished?id=1"));
    }
}