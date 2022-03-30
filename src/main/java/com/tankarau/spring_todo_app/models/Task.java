package com.tankarau.spring_todo_app.models;

public class Task {
    private final String id;
    private  String title;
    private Boolean isFinished;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public Task(String id, String title) {
        this.id = id;
        this.title = title;
        this.isFinished = false;
    }
}

