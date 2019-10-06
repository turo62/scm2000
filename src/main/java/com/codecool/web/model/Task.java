package com.codecool.web.model;

public class Task {
    private int taskId;
    private int userId;
    private String taskTitle;
    private String taskContent;
    
    public Task(int taskId, int userId, String taskTitle, String taskContent) {
        this.taskId = taskId;
        this.userId = userId;
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
    }
    
    public int getTaskId() {
        return taskId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getTaskTitle() {
        return taskTitle;
    }
    
    public String getTaskContent() {
        return taskContent;
    }
    
    @Override
    public String toString() {
        return "Task{" +
            "taskId=" + taskId +
            ", userId=" + userId +
            ", taskTitle='" + taskTitle + '\'' +
            ", taskContent='" + taskContent + '\'' +
            '}';
    }
}
