package com.taskable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by kylemccrosson on 11/3/17.
 */
public class TaskOverview extends JFrame implements ActionListener {
  public TaskOverview(/** Project p */) {
    this.setLayout(new BorderLayout());

    // Labels
    descripLabel = new JLabel("Description:");
    memberLabel = new JLabel("Joanne");
    dueDateLabel = new JLabel("Due Date:");
    dateLabel = new JLabel("12/12/12");
    description = new JTextArea("This is the description");
    description.setEditable(false);
    description.setColumns(30);
    description.setRows(8);
    taskName = new JLabel("Task Name");
    taskName.setFont(new Font("Sans",Font.BOLD, 20));
    assigneeLabel = new JLabel("Assignee:");

    //Buttons
    edit = new JButton("Edit");
    delete = new JButton("Delete");
    complete = new JButton("Complete");
    ImageIcon img = createImageIcon("icons/img_68687.png");
    returnAllTasks = new JButton("All Tasks", img);

    initComponents();
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
    this.add(north, BorderLayout.NORTH);

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
    this.add(west, BorderLayout.WEST);

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

    this.add(center, BorderLayout.CENTER);


    pack();

  }

  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == edit) {
      // do something
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
      }
    }
    if (src == returnAllTasks) {
      // do something
    }
  }

  private JLabel descripLabel, memberLabel, dueDateLabel, dateLabel, taskName, assigneeLabel;
  private JButton edit, delete, complete, returnAllTasks;
  private JTextArea description;

  public static void main(String[] args) {
    TaskOverview p = new TaskOverview();
    p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    p.pack();
    p.setVisible(true);
  }
}
