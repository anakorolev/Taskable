package com.taskable;

import com.taskable.model.ITask;
import com.taskable.model.Project;
import com.taskable.model.Task;
import com.taskable.model.User;

import java.awt.*;
import java.util.ArrayList;

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
  //private Project project;

  //constructor
  public AllTasksView(User u, Project p) {
    panel = new JPanel();
    project = p;
    user = u;
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

    ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();

    //todo loop through each new task and add a new row
    for (int i = 0; i < project.getTasks().size(); i ++ ) {//(ITask t : project.getTasks()) {
      Task t = (Task)project.getTasks().get(i);
      JLabel taskDesc = new JLabel(t.getTaskDesc());
      List listAssignees = new List();
      for (String mem : project.getProjectMembers()) {
        listAssignees.add(mem);
      }
      JComboBox assigneeDropdown = new JComboBox((ComboBoxModel) listAssignees);

      JLabel due = new JLabel(project.getProjectDueDate().toString());

      JPanel newPanel = new JPanel();
      newPanel.setLayout(new GridLayout(0, 3));
      newPanel.add(taskDesc);
      newPanel.add(assigneeDropdown);
      newPanel.add(due);

      listOfPanels.add(newPanel);
    }

    frame.setContentPane(new AllTasksView().panel);

    panel.setLayout(new GridLayout(3, 3));
    panel.add(top);

    for (int i = 0; i < listOfPanels.size(); i ++) {
      JPanel anotherPanel = new JPanel();
      anotherPanel.setLayout(new BorderLayout());
      anotherPanel.add(listOfPanels.get(i), BorderLayout.NORTH);
      panel.add(anotherPanel);
    }

    frame.setPreferredSize(new Dimension(700, 300));

    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
