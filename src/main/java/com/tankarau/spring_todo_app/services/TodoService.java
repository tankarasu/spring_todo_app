package com.tankarau.spring_todo_app.services;

import com.tankarau.spring_todo_app.models.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {
    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    private List<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo("1", "todo 1"),
            new Todo("2", "todo 2"),
            new Todo("3", "todo 3")
    ));

    public List<Todo> fetchAllTodos() {
        return todos;
    }

    public Todo fetchSpecificTodo(String id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
    }

    public void createOneTodo(Todo newTodo) {
        todos.add( newTodo);
    }

    public void updateSpecificTask(String id, String newDescription) {
        List<Todo> taskListBeforeChange = fetchAllTodos();
        Todo taskBeforeChange = fetchSpecificTodo(id);
        Integer index = fetchAllTodos().indexOf(taskBeforeChange);
        taskBeforeChange.setTitle(newDescription);
        taskListBeforeChange.set(index, taskBeforeChange);
        setTodos(taskListBeforeChange);
    }

    public void deleteSpecificTodo(String id) {
        todos.removeIf(task -> task.getId().equals(id));
    }

    public void switchFinishedState(String id) {
        List<Todo> taskListBeforeChange = fetchAllTodos();
        Todo taskBeforeChange = fetchSpecificTodo(id);
        Integer index = fetchAllTodos().indexOf(taskBeforeChange);
        taskBeforeChange.setFinished(!taskBeforeChange.getFinished());
        taskListBeforeChange.set(index, taskBeforeChange);
        setTodos(taskListBeforeChange);
    }
}
