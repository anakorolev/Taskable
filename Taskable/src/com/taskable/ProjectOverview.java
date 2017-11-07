package com.taskable;

import com.taskable.Vendor.CustomizedButtonUI;
import com.taskable.Views.*;
import com.taskable.Views.BaseView;
import com.taskable.model.Project;
import com.taskable.model.User;
import com.taskable.model.Task;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by kylemccrosson on 11/3/17.
 */
public class ProjectOverview extends JFrame implements ActionListener{

  public ProjectOverview(User u, Project p, JPanel b) {
    projectOverviewPanel = new JPanel();
    projectOverviewPanel.setLayout(new BorderLayout());
    user = u;
    project = p;
    baseView = b;

    initComponents();
    this.setVisible(true);
  }

  private void initComponents() {
    // Labels
    descripLabel = new JLabel("Description:");
    memberLabel = new JLabel("Members:");
    dueDateLabel = new JLabel("Due Date:");

    dueDate = project.getProjectDueDate();
    int day = dueDate.getDate();
    int month = dueDate.getMonth();
    int year = dueDate.getYear();
    if(month == 0){
      month = 12;
    }
    dateLabel = new JLabel("" + month + "/" + day + "/" + year);


    description = new JTextArea(project.getProjectDesc());
    description.setColumns(30);
    description.setRows(8);
    description.setEditable(false);

    //Buttons
    edit = new JButton();
    edit.setUI(new CustomizedButtonUI(
            new Color(176, 190, 197),
            new Color(220, 227, 230),
            new Color(144, 164, 174),
            createImageIcon("icons/edit.png")));
    edit.setPreferredSize(new Dimension(90, 35));
    delete = new JButton();
    delete.setUI(new CustomizedButtonUI(
            new Color(231, 73, 70),
            new Color(245, 124, 122),
            new Color(207, 44, 41),
            createImageIcon("icons/delete1x.png")));
    delete.setPreferredSize(new Dimension(120, 35));
    complete = new JButton();
    complete.setUI(new CustomizedButtonUI(
        new Color(30, 190, 165),
        new Color(106, 213, 196),
        new Color(6, 139, 121),
        createImageIcon("icons/Complete.png")));
    complete.setPreferredSize(new Dimension(120, 35));
    rem = new JButton();
    rem.setUI(new CustomizedButtonUI(
              new Color(176, 190, 197),
              new Color(220, 227, 230),
              new Color(144, 164, 174),
              createImageIcon("icons/bellWhite.png")));
    rem.setPreferredSize(new Dimension(90, 35));

    if (project.getProjectFinished()) {
      edit.setEnabled(false);
      delete.setEnabled(false);
      complete.setEnabled(false);
      rem.setEnabled(false);
      edit.setUI(new CustomizedButtonUI(
              new Color(220, 227, 230),
              new Color(220, 227, 230),
              new Color(220, 227, 230),
              createImageIcon("icons/edit.png")));
      delete.setUI(new CustomizedButtonUI(
              new Color(245, 124, 122),
              new Color(245, 124, 122),
              new Color(245, 124, 122),
              createImageIcon("icons/delete1x.png")));
      complete.setUI(new CustomizedButtonUI(
              new Color(106, 213, 196),
              new Color(106, 213, 196),
              new Color(106, 213, 196),
              createImageIcon("icons/Complete.png")));
      rem.setUI(new CustomizedButtonUI(
              new Color(220, 227, 230),
              new Color(220, 227, 230),
              new Color(220, 227, 230),
              createImageIcon("icons/edit.png")));
    }

    // List
    members = project.getProjectMembers();

    // add action listeners
    edit.addActionListener(this);
    delete.addActionListener(this);
    complete.addActionListener(this);
    rem.addActionListener(this);

    // place items on the screen
    /** set components for top bar */
    JPanel north = new JPanel();
    north.setLayout(new BorderLayout());

    JPanel northLeft = new JPanel();
    northLeft.add(edit);
    north.add(northLeft, BorderLayout.WEST);

    JPanel northRight = new JPanel();
    northRight.add(delete);
    northRight.add(complete);
    north.add(northRight, BorderLayout.EAST);
    projectOverviewPanel.add(north, BorderLayout.NORTH);

    /** Set components for Left side */
    JPanel west = new JPanel();
    west.setLayout(new BorderLayout());
    west.setBorder(new EmptyBorder(30, 15, 0, 0));

    JPanel leftSideTop = new JPanel();
    leftSideTop.setLayout(new BorderLayout());
    leftSideTop.add(descripLabel, BorderLayout.NORTH);


    leftSideTop.add(description, BorderLayout.SOUTH);
    west.add(leftSideTop, BorderLayout.NORTH);

    JPanel leftSideBottom = new JPanel();
    leftSideBottom.add(dueDateLabel);
    leftSideBottom.add(dateLabel);
    leftSideBottom.add(rem);
    west.add(leftSideBottom, BorderLayout.WEST);
    projectOverviewPanel.add(west, BorderLayout.WEST);


    /** Set components for right side */
    JPanel center = new JPanel();
    center.setLayout(new BorderLayout());
    center.setBorder(new EmptyBorder(30,15,0,15));
    center.add(memberLabel, BorderLayout.NORTH);

    JPanel memberList = new JPanel();
    memberList.setLayout(new BorderLayout());
    memberList.setBorder(new EmptyBorder(0,15,0,0));

    JPanel memberGrid = new JPanel();
    memberGrid.setLayout(new GridLayout(0,1));
    for (String person : members) {
      memberGrid.add(new JLabel(person));
    }
    memberList.add(memberGrid, BorderLayout.NORTH);
    center.add(memberList, BorderLayout.CENTER);
    projectOverviewPanel.add(center, BorderLayout.CENTER);

  }

  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = TaskOverview.class.getResource(path);
    //error handling omitted for clarity...
    return new ImageIcon(imgURL);
  }

  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == edit) {
      // do something
      projectOverviewPanel.removeAll();
      new projectModalView("Edit Project", project, user);
      initComponents();
      projectOverviewPanel.revalidate();
      projectOverviewPanel.repaint();
    }
    if (src == complete) {
      if (project.getTasks() != null) {
        for (int i = 0; i < project.getTasks().size(); i++) {

          Task t = (Task) project.getTasks().get(i);
          if (!t.getFinished()) {
            JOptionPane.showMessageDialog(this,
                "You can't finish this project because there are incomplete tasks!");
            return;
          }
        }
      }

      Object[] options = {"Cancel",
          "Continue"};

      int n = JOptionPane.showOptionDialog(projectOverviewPanel,
          "Are you sure you want to complete this project?",
              "Complete Project", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);

      if (n == 1) {

        project.finishProject();
        edit.setEnabled(false);
        complete.setEnabled(false);
        delete.setEnabled(false);
        edit.setUI(new CustomizedButtonUI(
                new Color(220, 227, 230),
                new Color(220, 227, 230),
                new Color(220, 227, 230),
                createImageIcon("icons/edit.png")));
        delete.setUI(new CustomizedButtonUI(
                new Color(245, 124, 122),
                new Color(245, 124, 122),
                new Color(245, 124, 122),
                createImageIcon("icons/delete1x.png")));
        complete.setUI(new CustomizedButtonUI(
                new Color(106, 213, 196),
                new Color(106, 213, 196),
                new Color(106, 213, 196),
                createImageIcon("icons/Complete.png")));
        rem.setUI(new CustomizedButtonUI(
                new Color(220, 227, 230),
                new Color(220, 227, 230),
                new Color(220, 227, 230),
                createImageIcon("icons/edit.png")));
        repaint();
        pack();

      }
    }
    if (src == delete) {

      Object[] options = {"Cancel",
          "Continue"};

      int n = JOptionPane.showOptionDialog(projectOverviewPanel,
          "Are you sure you want to delete this project?", "Delete Project",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);

      if (n == 1) {
        // delete the project from the app
        // how to get access to the list of projects to edit it
        user.getProjectsForUser().remove(project);

        user.setCurrentProjectId(0);

        System.out.println(user.getProjectsForUser());
        baseView.removeAll();
        baseView.add(new BaseView(user).getBasePanel());
        baseView.revalidate();
        baseView.repaint();

      }
    }
    if (src == rem) {
      Object[] options = {"Remind!", "Cancel"};
      int n = JOptionPane.showOptionDialog(projectOverviewPanel,
              "Would you like to remind everyone in the group about " + project.getProjectName() + "?", "Send Reminder",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[0]);
    }
  }

  public JPanel getProjectOverviewPanel() {
    return this.projectOverviewPanel;
  }

  private JLabel descripLabel, memberLabel, dueDateLabel, dateLabel;
  private JButton edit, delete, complete, rem;
  private ArrayList<String> members;
  private Date dueDate;
  private Project project;
  private User user;
  private JTextArea description;
  private JPanel projectOverviewPanel;
  private JPanel baseView;



}
