package com.tankarau.spring_todo_app.controllers;

import com.tankarau.spring_todo_app.models.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmokeTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    List<Todo> initialTaskList = Arrays.asList(
            new Todo("1", "todo 1"),
            new Todo("2", "todo 2"),
            new Todo("3", "todo 3")
    );
/*
    @Test
    public void getAllTodos() throws Exception {
        String url = "http://localhost:" + port;
        assertThatObject(this.template.getForObject(url)).usingRecursiveComparison().isEqualTo(initialTaskList);
    }
    */

}
