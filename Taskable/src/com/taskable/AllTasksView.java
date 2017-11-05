package com.taskable;

import java.awt.*;

import javax.swing.*;

/**
 * GridLayout
 * List of all tasks by row.
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

    JPanel line = new JPanel();
    line.setLayout(new BorderLayout());

    JLabel task = new JLabel("Tasks   ");
    JLabel assignee = new JLabel("Assignee   ");
    JLabel dueDate = new JLabel("Due Date   ");
    line.add(task, BorderLayout.LINE_START);
    line.add(assignee, BorderLayout.CENTER);
    line.add(dueDate, BorderLayout.LINE_END);

    JLabel taskDesc = new JLabel("Task description goes here");
    String[] listAssignees = { "None", "Nancy", "Jason", "Sally"}; //TODO get list of members from model
    JComboBox assigneeDropdown = new JComboBox(listAssignees);
    JLabel due = new JLabel("Due Date");

    JPanel mid = new JPanel();
    mid.setLayout(new BorderLayout());

    mid.add(taskDesc, BorderLayout.WEST);
    mid.add(assigneeDropdown, BorderLayout.CENTER);
    mid.add(due, BorderLayout.EAST);


    frame.setContentPane(new AllTasksView().panel);

    panel.add(line);
    panel.add(mid);

    frame.setPreferredSize(new Dimension(700, 300));

    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
