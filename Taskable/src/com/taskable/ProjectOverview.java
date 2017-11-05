package com.taskable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by kylemccrosson on 11/3/17.
 */
public class ProjectOverview extends JFrame implements ActionListener{

  public ProjectOverview(/** Project p */) {
    this.setLayout(new BorderLayout());

    // Labels
    descripLabel = new JLabel("Description:");
    memberLabel = new JLabel("Members:");
    dueDateLabel = new JLabel("Due Date:");

    //Buttons
    edit = new JButton("Edit");
    delete = new JButton("Delete");
    complete = new JButton("Complete");

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

    JPanel leftSideTop = new JPanel();
    leftSideTop.setLayout(new BorderLayout());
    leftSideTop.add(descripLabel, BorderLayout.NORTH);
    JTextArea description = new JTextArea("This is the description");
    description.setColumns(30);
    description.setRows(8);
    leftSideTop.add(description, BorderLayout.SOUTH);
    west.add(leftSideTop, BorderLayout.NORTH);

    JPanel leftSideBottom = new JPanel();
    leftSideBottom.add(dueDateLabel);
    leftSideBottom.add(new JLabel("12/12/12"));
    west.add(leftSideBottom, BorderLayout.WEST);
    this.add(west, BorderLayout.WEST);

    /** Set components for right side */
    JPanel memberInfo = new JPanel();
    memberInfo.setLayout(new BorderLayout());
    memberInfo.setBorder(new EmptyBorder(0,15,0,0));
    memberInfo.add(memberLabel, BorderLayout.NORTH);
    this.add(memberInfo, BorderLayout.CENTER);


    pack();

  }

  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == edit) {
      // do something
    }
    if (src == complete) {
      // do something
    }
    if (src == delete) {
      // do something
    }
  }

  private JLabel descripLabel, memberLabel, dueDateLabel, dateLabel;
  private JButton edit, delete, complete;

  public static void main(String[] args) {
    ProjectOverview p = new ProjectOverview();
    p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    p.pack();
    p.setVisible(true);
  }
}
