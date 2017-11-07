package com.taskable;

import com.taskable.Views.memberModalView;
import com.taskable.Views.taskModalView;
import com.taskable.model.ITask;
import com.taskable.model.Project;
import com.taskable.model.Task;
import com.taskable.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by schou on 11/5/17.
 *
 * Class for the All Tasks View.
 */
public class AllTasksView implements ActionListener{
  private static JPanel panel;
  private static Project project;
  private static User user;
  private JButton addTaskButton, memberEditButton, remind;
  private JComboBox<String> actionBox;
  private ArrayList<JCheckBox> group1;


  //constructor
  public AllTasksView(User u, Project p) {
    panel = new JPanel();
    project = p;
    user = u;
    group1 = new ArrayList<>();
    addTaskButton = new JButton("+ Task");
    memberEditButton = new JButton("Members...");
    remind = new JButton("Send a Reminder");
    String[] actions = {"Actions...", "Complete", "Delete"};
    actionBox = new JComboBox<>(actions);

    addTaskButton.addActionListener(this);
    memberEditButton.addActionListener(this);
    actionBox.addActionListener(this);
    remind.addActionListener(this);

    ArrayList<String> members = new ArrayList<String>();
    for (int i = 0; i < p.getProjectMembers().size(); i ++) {
      members.add(p.getProjectMembers().get(i));
    }


    ArrayList<ITask> listTask = new ArrayList<>();
    for (int i = 0; i < p.getTasks().size(); i ++) {
      listTask.add(p.getTasks().get(i));
    }

    allTasksPanel();
  }

  //do all of the creating
  public void allTasksPanel() {
    JPanel top = new JPanel();
    top.setBorder(new EmptyBorder(0,15,0,0));
    top.setLayout(new GridLayout(0, 4));

    top.add(memberEditButton);
    top.add(addTaskButton);
    top.add(actionBox);
    top.add(new JLabel("" + project.getProjectMembers().size() + " Members"));


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
      JPanel buttonAndBox = new JPanel();
      JCheckBox checkBox = new JCheckBox();
      checkBox.setLabel("" + t.getTaskId());
      group1.add(checkBox);
      JButton taskName = new JButton(t.getTaskName());
      buttonAndBox.add(checkBox);
      buttonAndBox.add(taskName);
      taskName.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          panel.removeAll();
          panel.add(new TaskOverview(user, project, t).getTaskOverviewPanel());
          panel.revalidate();
          panel.repaint();
        }
      });
      JLabel taskAssignee = new JLabel(t.getTaskUser());

      //get the due date of the Task
      Date d = t.getTaskDueDate();
      int day = d.getDate();
      int month = d.getMonth();
      int year = d.getYear();
      if (month == 0) { month = 12;}
      JLabel due = new JLabel("" + month + "/" + day + "/" + year);

      JButton reminderButton = new JButton("Send a Reminder");
      reminderButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Object[] options = {"Remind!", "Cancel"};
          int n = JOptionPane.showOptionDialog(getAllTasksPanel(),
                  "Would you like to send a reminder to " + t.getTaskUser() + "?", "Send a Reminder",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
        }
      });

      //create a new panel to add the task description, assignee drop down, and due date to
      JPanel newPanel = new JPanel();
      newPanel.setLayout(new GridLayout(0, 4));
      newPanel.add(buttonAndBox);
      newPanel.add(taskAssignee);
      newPanel.add(due);
      newPanel.add(reminderButton);
      //newPanel.add(remind);

      //add the new panel to the list of panels.
      listOfPanels.add(newPanel);
    }


    //set the new panel layout to be a new grid layout and add the top bar
    JPanel bord = new JPanel();
    bord.setLayout(new GridLayout(0, 1));
    bord.add(top);

    //make another panel, set the layout to be border, and add the current panel from list to the new panel at NORTH
    for (int i = 0; i < listOfPanels.size(); i ++) {
      JPanel anotherPanel = new JPanel();
      anotherPanel.setLayout(new BorderLayout());
      anotherPanel.add(listOfPanels.get(i), BorderLayout.NORTH);
      bord.add(anotherPanel);
    }

    panel.setLayout(new BorderLayout());
    panel.add(bord);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == addTaskButton) {
      panel.removeAll();
      new taskModalView("New Task", null, project);
      allTasksPanel();
      panel.revalidate();
      panel.repaint();
    }
    if (src == memberEditButton) {
      panel.removeAll();
      new memberModalView(project).setTitle("Edit Members");
      allTasksPanel();
      panel.revalidate();
      panel.repaint();
    }
    if (src == actionBox) {
      JComboBox cb = (JComboBox)src;
      String action = (String)cb.getSelectedItem();
      if (action.equals("Delete")) {
        Object[] options = {"Cancel",
            "Continue"};

        int n = JOptionPane.showOptionDialog(getAllTasksPanel(),
            "Are you sure you want to delete all selected tasks?", "Delete Tasks",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

        if (n == 1) {
          for (JCheckBox jcb : group1) {
            if (jcb.isSelected()) {

              int id = Integer.parseInt(jcb.getLabel());
              ArrayList<ITask> newTaskList = new ArrayList<>(project.getTasks());
              for (ITask task : newTaskList) {
                Task t = (Task) task;
                if (id == t.getTaskId()) {
                  System.out.println(t.getTaskId());
                  System.out.println(id);
                  int index = project.getTasks().indexOf(t);
                  project.getTasks().remove(index);
                }
              }
            }
          }
        }
      }
      if (action.equals("Complete")) {
        Object[] options = {"Cancel",
            "Continue"};

        int n = JOptionPane.showOptionDialog(getAllTasksPanel(),
            "Are you sure you want to complete all selected tasks?", "Complete Tasks",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        if (n == 1) {
          for (JCheckBox jcb : group1) {
            if (jcb.isEnabled()) {
              int id = Integer.parseInt(jcb.getLabel());
              for (ITask task : project.getTasks()) {
                Task t = (Task) task;
                if (id == t.getTaskId()) {
                  t.finishTask();
                }
              }
            }
          }
        }
      }
      panel.removeAll();
      allTasksPanel();
      panel.revalidate();
      panel.repaint();
    }
  }


  public JPanel getAllTasksPanel() {
    return this.panel;
  }
}
