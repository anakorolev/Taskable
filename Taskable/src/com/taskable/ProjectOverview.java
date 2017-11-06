package com.taskable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by kylemccrosson on 11/3/17.
 */
public class ProjectOverview extends JFrame implements ActionListener{

  public ProjectOverview(/** User u */) {

    this.setLayout(new BorderLayout());
    // user = u;
    // project = user.getProjectsForUser.at(user.getCurrentProjectIdForUser);


    // Labels
    descripLabel = /** new JLabel(p.getProjectDesc()); */ new JLabel("Description:");
    memberLabel = new JLabel("Members:");
    dueDateLabel = new JLabel("Due Date:");
    /**
     * dueDate = p.getProjectDueDate();
     * int day = dueDate.getDay();
     * int month = dueDate.getMonth();
     * int year = dueDate.getYear();
     * dateLabel = new JLabel("" + month "/" + day + "/" + year);
     */
    dateLabel = new JLabel("12/12/12");


    description = /** new JTextArea(p.getDesc()); */ new JTextArea("This is the description.");
    description.setColumns(30);
    description.setRows(8);
    description.setEditable(false);

    //Buttons
    edit = new JButton("Edit");
    delete = new JButton("Delete");
    complete = new JButton("Complete");

    // List
    members = /** p.getProjectMembers(); */ new ArrayList<String>();
    members.add("Oliver Twist");
    members.add("The Gingerbread Man");
    members.add("Tom Haverford");

    initComponents();
  }

  private void initComponents() {

    // add action listeners
    edit.addActionListener(this);
    delete.addActionListener(this);
    complete.addActionListener(this);

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
    this.add(north, BorderLayout.NORTH);

    /** Set components for Left side */
    JPanel west = new JPanel();
    west.setLayout(new BorderLayout());
    west.setBorder(new EmptyBorder(30,15,0,0));

    JPanel leftSideTop = new JPanel();
    leftSideTop.setLayout(new BorderLayout());
    leftSideTop.add(descripLabel, BorderLayout.NORTH);


    leftSideTop.add(description, BorderLayout.SOUTH);
    west.add(leftSideTop, BorderLayout.NORTH);

    JPanel leftSideBottom = new JPanel();
    leftSideBottom.add(dueDateLabel);
    leftSideBottom.add(dateLabel);
    west.add(leftSideBottom, BorderLayout.WEST);
    this.add(west, BorderLayout.WEST);

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
    this.add(center, BorderLayout.CENTER);


    pack();

  }

  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == edit) {
      // do something
      /**
       * new projectModalView("Edit Project", project);
       */
    }
    if (src == complete) {
      Object[] options = {"Cancel",
          "Continue"};
      int n = JOptionPane.showOptionDialog(this,
          "Are you sure you want to complete this project?", "Complete Project",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);

      if (n == 1) {
        // move the project to completed section, or get rid of it
        System.out.println("You completed the project");
        /**
         * project.finishTask();
         */
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
        // how to get access to the list of projects to edit it
        /**
         * user.getProjectsForUser().remove(project);
         */
      }
    }
  }

  private JLabel descripLabel, memberLabel, dueDateLabel, dateLabel;
  private JButton edit, delete, complete;
  private ArrayList<String> members;
  // private Date dueDate;
  // private Project project;
  // private User user;
  private JTextArea description;

  public static void main(String[] args) {
    ProjectOverview p = new ProjectOverview();
    p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    p.pack();
    p.setVisible(true);
  }
}
