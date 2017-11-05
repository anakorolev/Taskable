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
    Project(int id, String name, String desc, ArrayList<String> members, ArrayList<ITask> taks, Date dueDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.members = members;
        this.tasks = taks;
        this.dueDate = dueDate;
        this.finished = false;
    }

    public void setPojectId(int id) {
        this.id = id;
    }
    public void setPojectName(String name) {
        this.name = name;
    }
    public void setPojectDesc(String desc) {
        this.desc = desc;
    }
    public void setPojectMembers(ArrayList<String> members) {
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
    public void setTaskDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void finishTask() {
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
    public Date getProjectDueDate() {
        return this.dueDate;
    }
    public boolean getProjectFinished() {
        return this.finished;
    }





}
