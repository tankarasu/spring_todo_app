package com.tankarau.spring_todo_app.controllers;

// Internal requirements

import com.tankarau.spring_todo_app.models.Todo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    String taskTwo = "{\"id\":\"2\",\"title\":\"todo 2\",\"finished\":false}";

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
                .andExpect(content().json(taskTwo));
    }

    @Test
    void createTodo() {
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