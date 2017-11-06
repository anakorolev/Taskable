package com.taskable.Views;

import com.taskable.ProjectOverview;
import com.taskable.model.Project;
import com.taskable.model.User;

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
  private User user;


  //empty constructor
  public BaseView(User u) {
    user = u;
    BasePanel = new JPanel();

    BasePanel.setLayout(new BorderLayout());

    taskableLogo = new JLabel("TASKABLE", JLabel.CENTER);
    taskableLogo.setFont(new Font("TimesRoman",Font.BOLD,20));
    taskableLogo.setPreferredSize(new Dimension(0, 50));

    projectSidePanelView projectView = new projectSidePanelView(u);
    JPanel projectPanel = projectView.getProjectPanel();
    baseLeft = new JPanel();
    baseLeft.setPreferredSize(new Dimension(150,0));
    baseLeft.add(projectPanel);

    baseRight = new JPanel();


    noPojectPanel = new JPanel();
    noPojectPanel.setLayout(new GridLayout(2, 0));
    addNewProjectPromote = new JLabel("No Project", JLabel.CENTER);
    noPojectPanel.add(addNewProjectPromote,CENTER_ALIGNMENT);

    addNewProjectButton = new JButton("add Project");
    addNewProjectButton.addActionListener(this);

    if(u.getProjectsForUser().size() == 0) {
      noPojectPanel.add(addNewProjectButton);
      baseRight.add(noPojectPanel);
    } else {
      Project currentProj = (Project)u.getProjectsForUser().get(u.getCurrentProjectIdForUser());
      ProjectOverview projectOverview = new ProjectOverview(u, currentProj);
      JPanel projectOverviewPanel = projectOverview.getProjectOverviewPanel();
      baseRight.add(projectOverviewPanel);
    }



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
      new projectModalView("New Project", null, user);
      new BaseView(user);
    }

  }
}