package com.taskable.model;

import java.util.ArrayList;
import java.util.Date;

public class Project implements IProject{

    private int id;
    private String name;
    private String desc;
    private ArrayList<String> members;
    private ArrayList<ITask> tasks;
    private Date dueDate;
    private Boolean finished;

    //constructor
    public Project(int id, String name, String desc, ArrayList<String> members, ArrayList<ITask> tasks, Date dueDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.members = members;
        this.tasks = tasks;
        this.dueDate = dueDate;
        this.finished = false;
    }

    public void setProjectId(int id) {
        this.id = id;
    }
    public void setProjectName(String name) {
        this.name = name;
    }
    public void setProjectDesc(String desc) {
        this.desc = desc;
    }
    public void setProjectMembers(ArrayList<String> members) {
        this.members =members;
    }
    public void addAMemberToProject(String user) {
        this.members.add(user);
    }
    public void setPojectTasks(ArrayList<ITask> tasks) {
        this.tasks = tasks;
    }
    public void addATaskToProject(ITask task) {
        this.tasks.add(task);
    }
    public void setProjectDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void finishProject() {
        this.finished = true;
    }

    public int getProjectId() {
        return this.id;
    }
    public String getProjectName() {
        return this.name;
    }
    public String getProjectDesc() {
        return this.desc;
    }
    public ArrayList<String> getProjectMembers() {
        return this.members;
    }

    public ArrayList<ITask> getTasks() {
        return this.tasks;
    }

    public Date getProjectDueDate() {
        return this.dueDate;
    }
    public boolean getProjectFinished() {
        return this.finished;
    }





}
