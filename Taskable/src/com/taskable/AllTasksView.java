package com.taskable;

import com.taskable.model.IProject;
import com.taskable.model.ITask;
import com.taskable.model.Project;
import com.taskable.model.Task;
import com.taskable.model.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

/**
 * Created by schou on 11/5/17.
 *
 * Class for the All Tasks View.
 */
public class AllTasksView {
  private static JPanel panel;
  private static Project project;
  private static User user;



  //constructor
  public AllTasksView(User u, Project p) {
    panel = new JPanel();

    ArrayList<String> members = new ArrayList<String>();
    for (int i = 0; i < p.getProjectMembers().size(); i ++) {
      members.add(p.getProjectMembers().get(i));
    }

//    members.add("Bob");
//    members.add("Joe");
//    members.add("Bill");
    ArrayList<ITask> listTask = new ArrayList<>();
    for (int i = 0; i < p.getTasks().size(); i ++) {
      listTask.add(p.getTasks().get(i));
    }
//    listTask.add(new Task(1, "Make view", "Task 1", "Bill", new Date(2017, 11, 3)));
//    listTask.add(new Task(1, "Make model", "Task 2", "Bob", new Date(2017, 12, 3)));
//    listTask.add(new Task(1, "Make constructor", "Task 3", "Joe", new Date(2017, 12, 4)));
//    listTask.add(new Task(1, "Make other", "Task 4", "None", new Date(2017, 11, 5)));
    
//    project = new Project(0, "Name", "Desc", members, listTask, new Date(2018, 8, 28));
//    ArrayList<IProject> listProjects = new ArrayList<>();
//    listProjects.add(project);
//    user = new User(listProjects, 1);
  }

  //do all of the creating
  public static void main(String[] args) {
    JFrame frame = new JFrame("Taskable");
    frame.setContentPane(new AllTasksView(user, project).panel);

    JPanel top = new JPanel();
    top.setLayout(new GridLayout(0, 4));

    JLabel task = new JLabel("Task");
    JLabel assignee = new JLabel("Assignee");
    JLabel dueDate = new JLabel("Due Date");
    JLabel rem = new JLabel("Remind");
    top.add(task);
    top.add(assignee);
    top.add(dueDate);
    top.add(rem);

    ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();

    //for each task in the Project
    for (int i = 0; i < project.getTasks().size(); i ++ ) {
      Task t = (Task)project.getTasks().get(i);

      //make the JLabel for the task description
      JLabel taskName = new JLabel(t.getTaskName());
      JLabel taskAssignee = new JLabel(t.getTaskUser());

      //get the due date of the Task
      Date d = t.getTaskDueDate();
      int day = d.getDate();
      int month = d.getMonth();
      int year = d.getYear();
      JLabel due = new JLabel("" + month + "/" + day + "/" + year);


      //add reminder button
      JButton remind = new JButton("R");

      //create a new panel to add the task description, assignee drop down, and due date to
      JPanel newPanel = new JPanel();
      newPanel.setLayout(new GridLayout(0, 4));
      newPanel.add(taskName);
      newPanel.add(taskAssignee);
      newPanel.add(due);
      newPanel.add(remind, BorderLayout.LINE_END);

      //add the new panel to the list of panels.
      listOfPanels.add(newPanel);
    }

    System.out.println("size of proj: " + project.getTasks().size());
    //set the main panel layout to be a new grid layout and add the top bar

    JPanel bord = new JPanel();
    bord.setLayout(new GridLayout(0, 1));
    bord.add(top);

    for (int i = 0; i < listOfPanels.size(); i ++) {
      //make another panel, set the layout to be border, and add the current panel from list to the new panel at NORTH
      JPanel anotherPanel = new JPanel();
      anotherPanel.setLayout(new BorderLayout());
      anotherPanel.add(listOfPanels.get(i), BorderLayout.NORTH);
      bord.add(anotherPanel);
    }

    panel.setLayout(new BorderLayout());
    panel.add(bord);

    frame.setPreferredSize(new Dimension(700, 300));

    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}