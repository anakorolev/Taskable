package com.taskable.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class BaseView extends JFrame implements ActionListener {

  private JPanel BasePanel, baseLeft, baseRight, noPojectPanel;
  private JLabel taskableLogo, addNewProjectPromote ;
  private JButton addNewProjectButton;


  //empty constructor
  public BaseView() {

    BasePanel = new JPanel();

    BasePanel.setLayout(new BorderLayout());

    taskableLogo = new JLabel("TASKABLE", JLabel.CENTER);
    taskableLogo.setFont(new Font("TimesRoman",Font.BOLD,20));
    taskableLogo.setPreferredSize(new Dimension(0, 50));

    baseLeft = new JPanel();
    baseLeft.setPreferredSize(new Dimension(150,0));
    baseLeft.setBackground(Color.blue);

    baseRight = new JPanel();


    noPojectPanel = new JPanel();
    noPojectPanel.setLayout(new GridLayout(2, 0));
    addNewProjectPromote = new JLabel("No Project", JLabel.CENTER);
    noPojectPanel.add(addNewProjectPromote,CENTER_ALIGNMENT);

    addNewProjectButton = new JButton("add Project");
    noPojectPanel.add(addNewProjectButton);
    baseRight.add(noPojectPanel);





    BasePanel.add(taskableLogo, BorderLayout.NORTH);
    BasePanel.add(baseLeft, BorderLayout.WEST);
    BasePanel.add(baseRight, BorderLayout.CENTER);




  }

  public JPanel getBasePanel() {
    return this.BasePanel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource()==addNewProjectButton){
      //new project here
    }

  }
}