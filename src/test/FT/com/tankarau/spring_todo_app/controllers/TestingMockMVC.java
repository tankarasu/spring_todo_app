package com.tankarau.spring_todo_app.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingMockMVC {
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/");
        MvcResult result =  mvc.perform(request).andReturn();
        assertEquals("Hello, world", result.getResponse().getContentAsString());
    }

}
