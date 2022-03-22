package com.tankarau.spring_todo_app.services;

import com.tankarau.spring_todo_app.models.Todo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {
    private List<Todo> todos = Arrays.asList(
            new Todo("1", "todo 1"),
            new Todo("2", "todo 2"),
            new Todo("3", "todo 3")
    );

    public List<Todo> fetchAllTodos() {
        return todos;
    }

    public Todo fetchSpecificTodo(String id){
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
    }
}
