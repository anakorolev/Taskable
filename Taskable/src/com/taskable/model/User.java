package com.taskable.model;

import java.util.ArrayList;

public class User implements IUser {

    private String username;
    private String password;
    private String userEmail;

    private ArrayList<IProject> projects;
    private int currentProjectId;

    //Constructor
    public User(ArrayList<IProject> projects, int currentProjectId) {
        this.projects = projects;
        this.currentProjectId = currentProjectId;
    }

    //Constructor for user information
    public User(String username, String password, String userEmail) {
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;

    }

    public ArrayList<IProject> getProjectsForUser() {
        return this.projects;
    }
    public int getCurrentProjectIdForUser() {
        return this.currentProjectId;
    }

    public void setProjects(ArrayList<IProject> projects) {
        this.projects = projects;
    }

    public void addAProjectForUser(IProject project) {
        this.projects.add(project);

    }
    public void setCurrentProjectId(int id) {
        this.currentProjectId = id;
    }



}
