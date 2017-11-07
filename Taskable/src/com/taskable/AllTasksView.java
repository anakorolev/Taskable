package com.taskable;

import com.taskable.Vendor.CustomizedButtonUI;
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
  private JButton addTaskButton, memberEditButton;
  private JComboBox<String> actionBox;
  private ArrayList<JCheckBox> group1;


  //constructor
  public AllTasksView(User u, Project p) {
    panel = new JPanel();
    project = p;
    user = u;
    group1 = new ArrayList<>();
    addTaskButton = new JButton();
    memberEditButton = new JButton();

    addTaskButton.setUI(new CustomizedButtonUI(
            new Color(7, 176, 221),
            new Color(91, 203, 235),
            new Color(0, 94, 119),
            createImageIcon("icons/addTaskWhite.png")));
    addTaskButton.setPreferredSize(new Dimension(80, 35));
    memberEditButton.setUI(new CustomizedButtonUI(
            new Color(176, 190, 197),
            new Color(220, 227, 230),
            new Color(144, 164, 174),
            createImageIcon("icons/managerMembersWhite.png")));
    memberEditButton.setPreferredSize(new Dimension(60, 35));

    String[] actions = {"Actions...", "Complete", "Delete"};
    actionBox = new JComboBox<>(actions);

    addTaskButton.addActionListener(this);
    memberEditButton.addActionListener(this);
    actionBox.addActionListener(this);

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

  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = TaskOverview.class.getResource(path);
    //error handling omitted for clarity...
    return new ImageIcon(imgURL);
  }

  //do all of the creating
  public void allTasksPanel() {
    JPanel top = new JPanel();
    top.setBorder(new EmptyBorder(0,15,0,0));
    top.setLayout(new GridLayout(0, 4, 20, 20));

    top.add(memberEditButton);
    top.add(addTaskButton);
    top.add(actionBox);
    top.add(new JLabel("" + project.getProjectMembers().size() + " Members"));


    JLabel task = new JLabel("Task", JLabel.LEFT);
    JLabel assignee = new JLabel("Assignee", JLabel.LEFT);
    JLabel dueDate = new JLabel("Due Date", JLabel.LEFT);
    JLabel rem = new JLabel("Remind", JLabel.LEFT);
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
      checkBox.setName("" + t.getTaskId());
      group1.add(checkBox);
      JButton taskName = new JButton(t.getTaskName());
      taskName.setUI(new CustomizedButtonUI(
              new Color(176, 190, 197), new Color(220, 227, 230),
              new Color(144, 164, 174), new Font("Arial", Font.BOLD, 14),
              new Color(50, 55, 56), Color.WHITE, new Color(50, 55, 56), null));
      taskName.setPreferredSize(new Dimension(100, 35));

      buttonAndBox.add(checkBox);
      buttonAndBox.add(taskName);
      buttonAndBox.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
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

      //add reminder button
      JButton remind = new JButton();
      remind.setUI(new CustomizedButtonUI(
              new Color(176, 190, 197), new Color(220, 227, 230),
              new Color(144, 164, 174), createImageIcon("icons/ic_notifications_black_24dp_1x.png")));

      JPanel remindPanel = new JPanel();
      remindPanel.setBorder(new EmptyBorder(0,40,0,40));
      remindPanel.setPreferredSize(new Dimension(35, 35));
      remindPanel.add(remind);

      //create a new panel to add the task description, assignee drop down, and due date to
      JPanel newPanel = new JPanel();
      newPanel.setLayout(new GridLayout(0, 4));
      newPanel.add(buttonAndBox);
      newPanel.add(taskAssignee);
      newPanel.add(due);
      newPanel.add(remindPanel, BorderLayout.LINE_END);
      newPanel.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(177,177,177)));

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

              int id = Integer.parseInt(jcb.getName());
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
              int id = Integer.parseInt(jcb.getName());
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
