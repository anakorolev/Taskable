package com.taskable.model;

import java.util.ArrayList;
import java.util.Date;

public class Project implements IProject{

    private int id;
    private String name;
    private String desc;
    private ArrayList<IUser> members;
    private ArrayList<ITask> tasks;
    private Date dueDate;
    private Boolean finished;

    //constructor
    Project(int id, String name, String desc, ArrayList<IUser> members, ArrayList<ITask> taks, Date dueDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.members = members;
        this.tasks = taks;
        this.dueDate = dueDate;
        this.finished = false;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setMembers(ArrayList<IUser> members) {
        this.members =members;
    }
    public void addAMemberToProject(IUser user) {
        this.members.add(user);
    }
    public void setTasks(ArrayList<ITask> tasks) {
        this.tasks = tasks;
    }
    public void addATaskToProject(ITask task) {
        this.tasks.add(task);
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }




}
