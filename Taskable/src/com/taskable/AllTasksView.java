package com.taskable;

import java.awt.*;

import javax.swing.*;

/**
 * GridLayout
 * List of all tasks by row.
 *
 * have the top thing be at BorderLayout.NORTH
 * each task = a gridLayout - set the rows 0 column 3 - inside a borderlayout??
 */
public class AllTasksView {
  private static JPanel panel;

  //constructor
  public AllTasksView() {
    panel = new JPanel();
  }

  //do all of the creating
  public static void main(String[] args) {
    JFrame frame = new JFrame("Taskable");
    //BaseView base = new BaseView();

    JPanel top = new JPanel();
    top.setLayout(new GridLayout(0, 3));

    JLabel task = new JLabel("Task");
    JLabel assignee = new JLabel("Assignee");
    JLabel dueDate = new JLabel("Due Date");
    top.add(task);
    top.add(assignee);
    top.add(dueDate);

    //todo loop through each new task and add a new row

    JLabel taskDesc = new JLabel("Task description goes here");
    String[] listAssignees = { "None", "Nancy", "Jason", "Sally"}; //TODO get list of members from model
    JComboBox assigneeDropdown = new JComboBox(listAssignees);

    String[] dueDates = {"11/17/17", "11/18/17", "12/4/17"};
    JLabel due = new JLabel("Due: " + dueDates[0]); //todo get the duedate from the model

    JPanel mid = new JPanel();
    mid.setLayout(new GridLayout(0, 3));

    mid.add(taskDesc);
    mid.add(assigneeDropdown);
    mid.add(due);

    JLabel taskDesc2 = new JLabel("Next task description");
    JComboBox assigneeDropdown2 = new JComboBox(listAssignees); //cant use the same dropdown twice?
    JLabel due2 = new JLabel("Due: " + dueDates[2]);

    JPanel bottom = new JPanel();
    bottom.setLayout(new GridLayout(0, 3));
    bottom.add(taskDesc2);
    bottom.add(assigneeDropdown2);
    bottom.add(due2);

    frame.setContentPane(new AllTasksView().panel);

//    panel.setLayout(new BorderLayout());
//    panel.add(top, BorderLayout.NORTH);

    panel.setLayout(new GridLayout(3, 3));
    panel.add(top);

    JPanel nextPanel = new JPanel();
    nextPanel.setLayout(new BorderLayout());
    nextPanel.add(mid, BorderLayout.NORTH);

    JPanel nextPanel2 = new JPanel();
    nextPanel2.setLayout(new BorderLayout());
    nextPanel2.add(bottom, BorderLayout.NORTH);

    panel.add(nextPanel);
    panel.add(nextPanel2);

    frame.setPreferredSize(new Dimension(700, 300));

    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
