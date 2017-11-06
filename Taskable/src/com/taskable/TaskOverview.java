package com.taskable;

import com.taskable.model.Project;
import com.taskable.model.Task;
import com.taskable.model.User;
import com.taskable.Views.taskModalView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by kylemccrosson on 11/3/17.
 */
public class TaskOverview extends JFrame implements ActionListener {
  public TaskOverview(User u, Project p, Task t) {

    user = u;
    project = p;
    task = t;
    taskOverviewPanel = new JPanel();
    taskOverviewPanel.setLayout(new BorderLayout());

    // Labels
    descripLabel = new JLabel("Description:");
    memberLabel = new JLabel(task.getTaskUser());
    dueDateLabel = new JLabel("Due Date:");
    dueDate = t.getTaskDueDate();
    int day = dueDate.getDate();
    int month = dueDate.getMonth();
    int year = dueDate.getYear();
    if (month == 0) {month = 12;}
    dateLabel = new JLabel("" + month + "/" + day + "/" + year);
    description = new JTextArea(task.getTaskDesc());
    description.setEditable(false);
    description.setColumns(30);
    description.setRows(8);
    taskName = new JLabel(task.getTaskName());
    taskName.setFont(new Font("Sans",Font.BOLD, 20));
    assigneeLabel = new JLabel("Assignee:");

    //Buttons
    edit = new JButton("Edit");
    delete = new JButton("Delete");
    complete = new JButton("Complete");
    ImageIcon img = createImageIcon("icons/img_68687.png");
    returnAllTasks = new JButton("All Tasks", img);

    initComponents();
    taskOverviewPanel.setVisible(true);
  }

  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = TaskOverview.class.getResource(path);
    //error handling omitted for clarity...
    return new ImageIcon(imgURL);
  }

  private void initComponents() {

    // add action listeners
    edit.addActionListener(this);
    delete.addActionListener(this);
    complete.addActionListener(this);
    returnAllTasks.addActionListener(this);

    if (task.getFinished()) {
      edit.setEnabled(false);
      delete.setEnabled(false);
      complete.setEnabled(false);
    }

    // place items on the screen
    /** set components for top bar */
    JPanel north = new JPanel();
    north.setLayout(new BorderLayout());

    JPanel northLeft = new JPanel();
    northLeft.add(edit);
    northLeft.add(returnAllTasks);
    north.add(northLeft, BorderLayout.WEST);

    JPanel northRight = new JPanel();
    northRight.add(delete);
    northRight.add(complete);
    north.add(northRight, BorderLayout.EAST);
    taskOverviewPanel.add(north, BorderLayout.NORTH);

    /** Set components for Left side */
    JPanel west = new JPanel();
    west.setLayout(new BorderLayout());
    west.setBorder(new EmptyBorder(0,15,0,0));

    JPanel leftSideTop = new JPanel();
    leftSideTop.setLayout(new BorderLayout());
    leftSideTop.add(taskName, BorderLayout.NORTH);
    leftSideTop.add(descripLabel, BorderLayout.SOUTH);
    west.add(leftSideTop, BorderLayout.NORTH);

    JPanel leftSideBottom = new JPanel();
    leftSideBottom.add(description);
    west.add(leftSideBottom, BorderLayout.WEST);
    taskOverviewPanel.add(west, BorderLayout.WEST);

    /** Set components for right side */
    JPanel center = new JPanel();
    center.setLayout(new BorderLayout());
    center.setBorder(new EmptyBorder(25,15,0,15));

    JPanel dueDateStuff = new JPanel();
    dueDateStuff.setLayout(new BorderLayout());
    dueDateStuff.add(dueDateLabel, BorderLayout.NORTH);
    JPanel actualDueDate = new JPanel();
    actualDueDate.setBorder(new EmptyBorder(0,15,0,15));
    actualDueDate.add(dateLabel);
    dueDateStuff.add(actualDueDate, BorderLayout.WEST);
    center.add(dueDateStuff, BorderLayout.NORTH);

    JPanel assigneeStuff = new JPanel();
    assigneeStuff.setLayout(new BorderLayout());
    assigneeStuff.setBorder(new EmptyBorder(15,0,0,0));

    assigneeStuff.add(assigneeLabel, BorderLayout.NORTH);
    JPanel actualAssignee = new JPanel();
    actualAssignee.setBorder(new EmptyBorder(0,15,0,15));
    actualAssignee.add(memberLabel);
    assigneeStuff.add(actualAssignee, BorderLayout.WEST);
    center.add(assigneeStuff, BorderLayout.WEST);

    taskOverviewPanel.add(center, BorderLayout.CENTER);
    pack();
  }

  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == edit) {

      new taskModalView("Edit Task", task, project);
      this.dispose();
      new TaskOverview(user, project, task);

    }
    if (src == complete) {
      Object[] options = {"Cancel",
          "Continue"};
      int n = JOptionPane.showOptionDialog(this,
          "Are you sure you want to complete this task?", "Complete task",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);

      if (n == 1) {
        // move the project to completed section, or get rid of it
        task.finishTask();
        edit.setEnabled(false);
        complete.setEnabled(false);
        delete.setEnabled(false);
      }
    }
    if (src == delete) {
      Object[] options = {"Cancel",
          "Continue"};
      int n = JOptionPane.showOptionDialog(this,
          "Are you sure you want to delete this project?", "Delete Project",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);

      if (n == 1) {
        // delete the project from the app
        project.getTasks().remove(task);
        taskOverviewPanel.removeAll();
        taskOverviewPanel.add(new AllTasksView(user, project).getAllTasksPanel());
        taskOverviewPanel.revalidate();
        taskOverviewPanel.repaint();
      }
    }
    if (src == returnAllTasks) {
      // GO BACK TO THE TASKS VIEW
      taskOverviewPanel.removeAll();
      taskOverviewPanel.add(new AllTasksView(user, project).getAllTasksPanel());
      taskOverviewPanel.revalidate();
      taskOverviewPanel.repaint();
    }
  }

  public JPanel getTaskOverviewPanel() {
    return this.taskOverviewPanel;
  }

  private JLabel descripLabel, memberLabel, dueDateLabel, dateLabel, taskName, assigneeLabel;
  private JButton edit, delete, complete, returnAllTasks;
  private JTextArea description;
  private Project project;
  private Task task;
  private User user;
  private Date dueDate;
  private JPanel taskOverviewPanel;


}
