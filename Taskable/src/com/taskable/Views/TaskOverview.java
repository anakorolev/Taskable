package com.taskable.Views;

import com.taskable.Vendor.CustomizedButtonUI;
import com.taskable.model.Project;
import com.taskable.model.Task;
import com.taskable.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by kylemccrosson on 11/3/17.
 */
public class TaskOverview extends JPanel implements ActionListener {
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
    dueDate = task.getTaskDueDate();

    int day = dueDate.getDate();
    int month = dueDate.getMonth();
    int year = dueDate.getYear();
    if (month == 0) {month = 12;}
    dateLabel = new JLabel("" + month + "/" + day + "/" + year);
    description = new JTextArea(task.getTaskDesc());
    description.setEditable(false);
    description.setColumns(40);
    description.setRows(15);
    taskName = new JLabel(task.getTaskName());
    taskName.setFont(new Font("Sans",Font.BOLD, 20));
    assigneeLabel = new JLabel("Assignee:");

    //Buttons
    edit = new JButton();
    edit.setUI(new CustomizedButtonUI(
            new Color(176, 190, 197),
            new Color(220, 227, 230),
            new Color(144, 164, 174),
            new ImageIcon("resources/edit.png")));
    edit.setPreferredSize(new Dimension(90, 35));
    delete = new JButton();
    delete.setUI(new CustomizedButtonUI(
            new Color(231, 73, 70),
            new Color(245, 124, 122),
            new Color(207, 44, 41),
            new ImageIcon("resources/delete1x.png")));
    delete.setPreferredSize(new Dimension(120, 35));
    complete = new JButton();
    complete.setUI(new CustomizedButtonUI(
            new Color(30, 190, 165),
            new Color(106, 213, 196),
            new Color(6, 139, 121),
            new ImageIcon("resources/Complete.png")));
    complete.setPreferredSize(new Dimension(120, 35));
    returnAllTasks = new JButton();
    returnAllTasks.setUI(new CustomizedButtonUI(
            new Color(176, 190, 197),
            new Color(220, 227, 230),
            new Color(144, 164, 174),
            new ImageIcon("resources/allTasks.png")));
    returnAllTasks.setPreferredSize(new Dimension(120, 35));
    remind = new JButton();
    remind.setUI(new CustomizedButtonUI(
            new Color(176, 190, 197),
            new Color(220, 227, 230),
            new Color(144, 164, 174),
            new ImageIcon("resources/bellWhite.png")));
    remind.setPreferredSize(new Dimension(60, 35));

    initComponents();
    taskOverviewPanel.setVisible(true);
  }

  private void initComponents() {

    // add action listeners
    edit.addActionListener(this);
    delete.addActionListener(this);
    complete.addActionListener(this);
    returnAllTasks.addActionListener(this);
    remind.addActionListener(this);

    if (task.getFinished() || project.getProjectFinished()) {
      edit.setEnabled(false);
      complete.setEnabled(false);
      remind.setEnabled(false);
      edit.setUI(new CustomizedButtonUI(
              new Color(220, 227, 230),
              new Color(220, 227, 230),
              new Color(220, 227, 230),
              new ImageIcon("resources/edit.png")));
      complete.setUI(new CustomizedButtonUI(
              new Color(106, 213, 196),
              new Color(106, 213, 196),
              new Color(106, 213, 196),
              new ImageIcon("resources/Complete.png")));
      remind.setUI(new CustomizedButtonUI(
              new Color(220, 227, 230),
              new Color(220, 227, 230),
              new Color(220, 227, 230),
              new ImageIcon("resources/bellWhite.png")));
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

    JPanel northCenter = new JPanel();
    northCenter.setPreferredSize(new Dimension(100, 0));
    north.add(northCenter, BorderLayout.CENTER);

    taskOverviewPanel.add(north, BorderLayout.NORTH);

    /** Set components for Left side */
    JPanel west = new JPanel();
    west.setLayout(new BorderLayout());
    west.setBorder(new EmptyBorder(0,15,0,0));
    west.setPreferredSize(new Dimension(500, 300));

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
    center.setBorder(new EmptyBorder(25,45,0,15));

    JPanel dueDateStuff = new JPanel();
    dueDateStuff.setLayout(new BorderLayout());
    dueDateStuff.add(dueDateLabel, BorderLayout.NORTH);
    JPanel actualDueDate = new JPanel();
    actualDueDate.setBorder(new EmptyBorder(0,15,0,15));
    actualDueDate.add(dateLabel);
    dueDateStuff.add(actualDueDate, BorderLayout.WEST);
    center.add(dueDateStuff, BorderLayout.NORTH);

    JPanel reminderPanel = new JPanel();
    reminderPanel.setLayout(new BorderLayout());
    reminderPanel.add(remind, BorderLayout.NORTH);
    dueDateStuff.add(reminderPanel, BorderLayout.EAST);

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
    repaint();
  }

  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == edit) {
      taskOverviewPanel.removeAll();
      new taskModalView("Edit Task", task, project);
      taskOverviewPanel.add(new TaskOverview(user, project, task).getTaskOverviewPanel());
      taskOverviewPanel.revalidate();
      taskOverviewPanel.repaint();

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
        edit.setUI(new CustomizedButtonUI(
                new Color(220, 227, 230),
                new Color(220, 227, 230),
                new Color(220, 227, 230),
                new ImageIcon("resources/edit.png")));
        complete.setUI(new CustomizedButtonUI(
                new Color(106, 213, 196),
                new Color(106, 213, 196),
                new Color(106, 213, 196),
                new ImageIcon("resources/Complete.png")));
        remind.setUI(new CustomizedButtonUI(
                new Color(220, 227, 230),
                new Color(220, 227, 230),
                new Color(220, 227, 230),
                new ImageIcon("resources/bellWhite.png")));
      }
    }
    if (src == delete) {
      Object[] options = {"Cancel",
          "Continue"};
      int n = JOptionPane.showOptionDialog(this,
          "Are you sure you want to delete this task? It cannot be undone!", "Delete Project",
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

    if (src == remind) {
      Object[] options = {"Remind!", "Cancel"};
      int n = JOptionPane.showOptionDialog(this,
              "Would you like to send a reminder to " + task.getTaskUser().toString()+ "?", "Send a Reminder",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[0]);
    }
  }

  public JPanel getTaskOverviewPanel() {
    return this.taskOverviewPanel;
  }

  private JLabel descripLabel, memberLabel, dueDateLabel, dateLabel, taskName, assigneeLabel;
  private JButton edit, delete, complete, returnAllTasks, remind;
  private JTextArea description;
  private Project project;
  private Task task;
  private User user;
  private Date dueDate;
  private JPanel taskOverviewPanel;


}
