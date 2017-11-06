package com.taskable.Views;

import com.taskable.ProjectOverview;
import com.taskable.model.Project;
import com.taskable.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

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

  private JPanel BasePanel, baseLeft, baseRight, noPojectPanel, projectPanel;
  private JLabel taskableLogo, addNewProjectPromote ;
  private JButton addNewProjectButton, profileButton;
  private User user;

  //empty constructor
  public BaseView(User u, String view) {
    user = u;
    BasePanel = new JPanel();

    BasePanel.setLayout(new BorderLayout());

    taskableLogo = new JLabel("TASKABLE", JLabel.CENTER);
    taskableLogo.setFont(new Font("TimesRoman",Font.BOLD,20));
    taskableLogo.setPreferredSize(new Dimension(300, 50));

    profileButton = new JButton("Profile");

    JPanel header = new JPanel();
    header.setLayout(new BorderLayout());
    header.setPreferredSize(new Dimension(0, 50));
    header.add(taskableLogo, BorderLayout.CENTER);
    //header.add(profileButton, BorderLayout.EAST);

    JMenuBar menu = new JMenuBar();

    JMenu profile = new JMenu("Profile");
    JMenuItem signOut = new JMenuItem("Logout");
    profile.add(signOut);
    menu.add(profile);

    signOut.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BasePanel.setVisible(false);
        }
    });

//    ImageIcon img = createImageIcon("icons/Hamburger_icon2%.png");
//    profile.setIcon(img);

    header.add(menu, BorderLayout.EAST);

    projectSidePanelView projectView = new projectSidePanelView(u);
    JPanel projectPanel = projectView.getProjectPanel();
    baseLeft = new JPanel();
    baseLeft.setPreferredSize(new Dimension(150,0));
    baseLeft.add(getProjectPanel());

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
        Project currentProj = (Project)user.getProjectsForUser().get(user.getCurrentProjectIdForUser());
        ProjectOverview projectOverview = new ProjectOverview(user, currentProj);
        JPanel projectOverviewPanel = projectOverview.getProjectOverviewPanel();
        baseRight.add(projectOverviewPanel);

        

        baseRight.add(allTasks);
    }



    BasePanel.add(header, BorderLayout.NORTH);
    BasePanel.add(baseLeft, BorderLayout.WEST);
    BasePanel.add(baseRight, BorderLayout.CENTER);

    BasePanel.setVisible(true);
  }

  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = BaseView.class.getResource(path);
    //error handling omitted for clarity...
    return new ImageIcon(imgURL);
  }

  public void projectSidePanel() {

        projectPanel = new JPanel();
        BorderLayout projectPanelLayout = new BorderLayout();
        projectPanel.setLayout(projectPanelLayout);
        projectPanel.setPreferredSize(new Dimension(150, 310));

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BorderLayout());
        scrollPanel.setPreferredSize(new Dimension(130, 100));

        JScrollPane scrollPane = new JScrollPane(scrollPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(scrollPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(true);

        projectPanel.add(scrollPane, projectPanelLayout.WEST);

        JButton addProjectButton = new JButton("+");
        addProjectButton.setPreferredSize(new Dimension(130, 25));
        projectPanel.add(addProjectButton, projectPanelLayout.SOUTH);

        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new projectModalView("New Project", null, user);
                repaint();
            }
        });

        JPanel projectGrid = new JPanel();
        GridLayout projectGridLayout = new GridLayout(4,1);
        projectGrid.setLayout(projectGridLayout);
        if(user.getProjectsForUser() != null){
            System.out.println("inside if");
            for(int i = 0; i < user.getProjectsForUser().size(); i++) {
                System.out.println("inside for loop");
                JPanel singleProject = new JPanel();
                singleProject.setLayout(new BorderLayout());
                projectGrid.add(singleProject);
                Project p = (Project)user.getProjectsForUser().get(i);
                JButton projectButton = new JButton(p.getProjectName());
                JButton allTasks = new JButton("Tasks");

                projectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new BaseView(user, "overview");
                    }
                });
                allTasks.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new BaseView(user, "tasks");
                    }
                });
                JButton projectOverview = new JButton("Overview");
                JPanel buttonPanel = new JPanel();
                GridLayout buttonPanelLayout = new GridLayout(2, 1);
                buttonPanel.setLayout(buttonPanelLayout);

                buttonPanel.add(projectOverview);
                buttonPanel.add(allTasks);
                buttonPanel.setVisible(false);
                buttonPanel.setBorder( new EmptyBorder(0,15, 0, 0));

                singleProject.add(buttonPanel, BorderLayout.SOUTH);

                projectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("action performed");
                        if(buttonPanel.isVisible()){
                            buttonPanel.setVisible(false);
                        } else {
                            buttonPanel.setVisible(true);
                        }
                    }
                });
                singleProject.add(projectButton, BorderLayout.NORTH);
                projectGrid.add(singleProject, projectGridLayout);
            }
        }

        scrollPanel.add(projectGrid, BorderLayout.NORTH);
    }

  public JPanel getBasePanel() {
    return this.BasePanel;
  }

  public JPanel getProjectPanel() {
      projectSidePanel();
      return this.projectPanel;
  }

  public JPanel getMainPanel(String view) {
      JPanel panel;
      if(view.equals("overview")){

      } else if (view.equals("tasks")){
          panel = new JPanel();
      } else {
          panel = new JPanel();
      }
      return panel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource()==addNewProjectButton){
      new projectModalView("New Project", null, user);
      BasePanel.setVisible(true);
      BasePanel.repaint();
    }


  }
}