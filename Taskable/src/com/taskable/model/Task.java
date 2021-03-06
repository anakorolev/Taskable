package com.taskable.model;

import java.util.Date;

public class Task implements ITask{

    private int id;
    private String name;
    private String desc;
    private String user;
    private Date dueDate;
    private boolean finished;

    //constructor
    public Task(int id, String name, String desc, String user, Date dueDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.user = user;
        this.dueDate = dueDate;
        this.finished = false;
    }

    //method
    public void updateTask(int id, String name, String desc, String user, Date dueDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.user = user;
        this.dueDate = dueDate;
    }

    public void setTaskId(int id) {
        this.id = id;
    }
    public void setTaskName(String name) {
        this.name = name;
    }
    public void setTaskDesc(String desc) {
        this.desc = desc;
    }
    public void setTaskUser(String user) {
        this.user = user;
    }
    public void setTaskDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getTaskId() {
        return this.id;
    }
    public String getTaskName() {
       return this.name;
    }
    public String getTaskDesc() {
        return this.desc;
    }
    public String getTaskUser() {
        return this.user;
    }
    public Date getTaskDueDate() {
        return this.dueDate;
    }

    public boolean getFinished() {return this.finished; }

    public void finishTask() {
        this.finished = true;
    }

}
