package com.taskable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;

/**
 * Class for the base view of the Taskable app.
 *
 * BorderLayout
 *    top nav - three buttons
 *    left project side
 *    middle empty
 * GridLayout
 *    project list (from UserModel)
 * TabLayout
 *    when clicking on a button, for Tasks and Overview (or two smaller buttons)
 */
public class BaseView {

  private static JPanel basePanel;
  private static JPanel baseGridPanel;
  //static JTextArea taskTextArea;
 // static JTextArea overviewTextArea;

  //empty constructor
  public BaseView() {
    basePanel = new JPanel();
    baseGridPanel = new JPanel();

  }

  public static void main(String[] args) {
    initComponents();
  }

  public static void initComponents() {
    JFrame frame = new JFrame("Taskable");

    //see members should pop up with the members from Model
    JButton members = new JButton("See Members"); //TODO add icon later

    //add task should go to the TaskModal
    JButton addTask = new JButton("Add Tasks"); //TODO add icon later
    //addTask.setPreferredSize(new Dimension(1, 1));

    //should have a pop up or drop down of tasks to apply
    //JButton actions = new JButton("Actions"); //TODO add the actions for delete, complete, etc
    String[] listOfActions = { "No Action Selected", "Complete all", "Delete all", "Assign all"};
    JComboBox actions = new JComboBox(listOfActions);
    actions.setSelectedIndex(0);
    //actions.addActionListener;

    //JLabel amtMembers = new JLabel("3 Members"); //todo get the # of members from the model

    //add the buttons to the buttonPanel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BorderLayout());
    buttonPanel.add(members, BorderLayout.LINE_START);
    buttonPanel.add(addTask, BorderLayout.CENTER);
    buttonPanel.add(actions, BorderLayout.LINE_END);

    JPanel leftSidePanel = new JPanel();
    leftSidePanel.setLayout(new GridLayout());
    //leftSidePanel.add(amtMembers, BorderLayout.LINE_START);


    //buttons for left side because i cant figure out tabs
    //todo getProjectName from the model
    JButton projectName = new JButton("My First Project"); //todo change this so that it works for adding more
//    ActionListener projListener = e -> {
//      //todo what to do here?? someone hide and show tabs
//
//    };
//    projectName.addActionListener(projListener);
    //JTextArea projectName = new JTextArea("My First Project");
//    JButton tasks = new JButton("Tasks");
//    JButton overview = new JButton("Overview");


    JPanel sidePanel = new JPanel();
    sidePanel.setLayout(new BorderLayout());
    sidePanel.add(projectName, BorderLayout.NORTH);
//    sidePanel.add(tasks, BorderLayout.CENTER);
//    sidePanel.add(overview, BorderLayout.SOUTH);

    //make the tabs for tasks and overview

    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setTabPlacement(JTabbedPane.LEFT);

    JComponent panel1 = new JPanel();
    tabbedPane.addTab("Tasks", panel1);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    JComponent panel2 = new JPanel();
    tabbedPane.addTab("Overview", panel2);
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

    sidePanel.add(tabbedPane);


    frame.setContentPane(new BaseView().basePanel);

    basePanel.setLayout(new BorderLayout());
    baseGridPanel.setLayout(new GridLayout());

    //add the button panel to the top of the main panel
    basePanel.add(buttonPanel, BorderLayout.NORTH);
    basePanel.add(sidePanel, BorderLayout.WEST);

    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
