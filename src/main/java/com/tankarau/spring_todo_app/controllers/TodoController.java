package com.tankarau.spring_todo_app.controllers;

import com.tankarau.spring_todo_app.models.Todo;

public class TodoController {
    public Todo[] getAllTodos(){
        return new Todo[]{new Todo()};
    }

    public Todo getTodo(String id){
        return new Todo();
    }

    public void createTodo(Todo todo){

    }

    public void updateTodo(String id){

    }

    public void deleteTodo(String id){

    }

    public void handleFinishedState(String id){

    }

}
