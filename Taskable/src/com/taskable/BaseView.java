package com.taskable;

import java.awt.*;

import javax.swing.*;

/**
 * Class for the base view of the Taskable app.
 *
 * BorderLayout
 *    top nav
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

  //empty constructor
  public BaseView() {
    basePanel = new JPanel();
    baseGridPanel = new JPanel();
  }

  public static void main(String[] args) {

    JFrame frame = new JFrame("Taskable");

    JButton members = new JButton("See Members"); //TODO add icon later
    JButton addTask = new JButton("Add Tasks"); //TODO add icon later
    JButton actions = new JButton("Actions"); //TODO add the actions for delete, complete, etc

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BorderLayout());
    buttonPanel.add(members, BorderLayout.LINE_START);
    buttonPanel.add(addTask, BorderLayout.CENTER);
    buttonPanel.add(actions, BorderLayout.LINE_END);



    frame.setContentPane(new BaseView().basePanel);

    basePanel.setLayout(new BorderLayout());
    baseGridPanel.setLayout(new GridLayout());

    basePanel.add(buttonPanel, BorderLayout.NORTH);
    basePanel.add(baseGridPanel, BorderLayout.WEST);


    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
