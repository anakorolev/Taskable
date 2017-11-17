package com.taskable.Views;

import com.taskable.Vendor.CustomizedButtonUI;
import com.taskable.model.IProject;
import com.taskable.model.Project;
import com.taskable.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
public class BaseView extends JPanel implements ActionListener {

  private JPanel BasePanel, baseLeft, baseRight, noPojectPanel, projectPanel, allTasksPanel, projectOverviewPanel;
  private JLabel taskableLogo, addNewProjectPromote ;
  private JButton addNewProjectButton, profileButton;
  private User user;
  private ProjectOverview projectOverview;
  private ArrayList<ArrayList> allProjectButtons;
  private ArrayList<Integer> allProjectButtonsId;
  private Boolean updateButtons;
  private String currentView = "tasks";

  //empty constructor
  public BaseView(User u) {
      user = u;
      BasePanel = new JPanel();


      BasePanel.setLayout(new BorderLayout());

      basePanel();
  }

  public void basePanel() {


    taskableLogo = new JLabel("TASKABLE", new ImageIcon("resources/taskable_small.png"), JLabel.LEFT);
    taskableLogo.setForeground(new Color(51,51,51));
    taskableLogo.setFont(new Font("Arial",Font.BOLD,20));
    taskableLogo.setPreferredSize(new Dimension(300, 50));

    profileButton = new JButton("Profile");

    JPanel header = new JPanel();
    header.setLayout(new BorderLayout());
    header.setBackground(new Color(213,213,213));
    header.setPreferredSize(new Dimension(0, 50));
    header.add(taskableLogo, BorderLayout.CENTER);
    //header.add(profileButton, BorderLayout.EAST);

    JMenuBar menu = new JMenuBar();

    JMenu profile = new JMenu();
    profile.setIcon(new ImageIcon("resources/ic_account_circle_black_24dp_1x.png"));
    JMenuItem signOut = new JMenuItem("Logout");
    profile.add(signOut);
    menu.add(profile);

    signOut.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BasePanel.setVisible(false);
        }
    });

    header.add(menu, BorderLayout.EAST);

    baseLeft = new JPanel();

    baseRight = new JPanel();
    baseRight.setPreferredSize(new Dimension(800,0));


    noPojectPanel = new JPanel();
    noPojectPanel.setLayout(new GridLayout(2, 0));
    addNewProjectPromote = new JLabel("No Projects!", JLabel.CENTER);
    addNewProjectPromote.setFont(new Font("Arial", Font.BOLD, 16));

    noPojectPanel.add(addNewProjectPromote,CENTER_ALIGNMENT);

    addNewProjectButton = new JButton();
    addNewProjectButton.setUI(new CustomizedButtonUI(
        new Color(7, 176, 221),
        new Color(91, 203, 235),
        new Color(0, 94, 119),
        new Font("Arial", Font.BOLD, 14),
        Color.WHITE, Color.WHITE, Color.WHITE,
            new ImageIcon("resources/addProject.png")));
    addNewProjectButton.setPreferredSize(new Dimension(120, 35));
    addNewProjectButton.addActionListener(this);

    if(user.getProjectsForUser().size() == 0) {
      noPojectPanel.add(addNewProjectButton);
      baseRight.add(noPojectPanel);
    } else {
        Project currentProj = (Project)user.getProjectsForUser().get(0);
        int currentId = user.getCurrentProjectIdForUser();
        for (IProject p : user.getProjectsForUser()) {
          Project proj = (Project)p;
          if (proj.getProjectId() == currentId) {
            currentProj = proj;
          }
        }

        projectOverview = new ProjectOverview(user, currentProj, this);

        projectOverviewPanel = projectOverview.getProjectOverviewPanel();
        projectOverviewPanel.setVisible(false);
        projectOverview.getDelete().addActionListener(this);
        baseRight.add(projectOverviewPanel);

        AllTasksView allTasks = new AllTasksView(user, currentProj);
        allTasksPanel = allTasks.getAllTasksPanel();
        allTasksPanel.setVisible(true);
        baseRight.add(allTasksPanel);

        baseLeft.setPreferredSize(new Dimension(200,0));
        baseLeft.add(getProjectPanel());
    }

    BasePanel.add(header, BorderLayout.NORTH);
    BasePanel.add(baseLeft, BorderLayout.WEST);
    BasePanel.add(baseRight, BorderLayout.CENTER);

    BasePanel.setVisible(true);
  }

  public void projectSidePanel() {

        projectPanel = new JPanel();
        BorderLayout projectPanelLayout = new BorderLayout();
        projectPanel.setLayout(projectPanelLayout);

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(scrollPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(scrollPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(true);
        scrollPane.setPreferredSize(new Dimension(200, 480));

        projectPanel.add(scrollPane, projectPanelLayout.WEST);

        JButton addProjectButton = new JButton();
        addProjectButton.setUI(new CustomizedButtonUI(
                new Color(7, 176, 221),
                new Color(91, 203, 235),
                new Color(0, 94, 119),
                new Font("Arial", Font.BOLD, 14),
                Color.WHITE, Color.WHITE, Color.WHITE,
                new ImageIcon("resources/addProject.png")));

        addProjectButton.setPreferredSize(new Dimension(200, 30));
        projectPanel.add(addProjectButton, projectPanelLayout.SOUTH);

        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BasePanel.removeAll();
                new projectModalView("New Project", null, user);
                basePanel();
                BasePanel.revalidate();
                BasePanel.repaint();
            }
        });

        JPanel projectGrid = new JPanel();
        GridLayout projectGridLayout = new GridLayout(0,1);
        projectGrid.setLayout(projectGridLayout);
        if(user.getProjectsForUser() != null){
            for(int i = 0; i < user.getProjectsForUser().size(); i++) {
                JPanel singleProject = new JPanel();
                singleProject.setLayout(new BorderLayout());
                projectGrid.add(singleProject);
                Project p = (Project)user.getProjectsForUser().get(i);
                JButton projectButton = new JButton(p.getProjectName());
                projectButton.setUI(new CustomizedButtonUI(
                        new Color(176, 190, 197), new Color(220, 227, 230),
                        new Color(144, 164, 174), new Font("Arial", Font.PLAIN, 14),
                        new Color(50, 55, 56), Color.WHITE, new Color(50, 55, 56), null));
                JButton projectOverview = new JButton("Overview");
                JButton allTasks = new JButton("Tasks");

                projectOverview.setUI(new CustomizedButtonUI(
                        new Color(220, 227, 230), new Color(231, 236, 238),
                        new Color(176, 190, 197), new Font("Arial", Font.PLAIN, 14),
                        new Color(50, 55, 56), Color.WHITE, new Color(50, 55, 56), null));

                allTasks.setUI(new CustomizedButtonUI(
                        new Color(220, 227, 230), new Color(231, 236, 238),
                        new Color(176, 190, 197), new Font("Arial", Font.PLAIN, 14),
                        new Color(50, 55, 56), Color.WHITE, new Color(50, 55, 56), null));

                projectButton.setPreferredSize(new Dimension(150,30));
                projectOverview.setPreferredSize(new Dimension(100,30));
                allTasks.setPreferredSize(new Dimension(100,30));

                if(p.getProjectFinished()){
                    projectButton.setUI(new CustomizedButtonUI(
                            new Color(30, 190, 165),
                            new Color(106, 213, 196),
                            new Color(6, 139, 121), new Font("Arial", Font.PLAIN, 14),
                            Color.WHITE, Color.WHITE, Color.WHITE, null));
                }

                if(user.getCurrentProjectIdForUser() == p.getProjectId() && currentView == "overview") {
                    updateButtons = true;
                    projectButton.setFont( new Font("Arial", Font.BOLD, 14));
                    projectOverview.setFont( new Font("Arial", Font.BOLD, 14));
                    allTasks.setFont( new Font("Arial", Font.PLAIN, 14));
                } else if(user.getCurrentProjectIdForUser() == p.getProjectId() && currentView == "tasks") {
                    updateButtons = true;
                    projectButton.setFont( new Font("Arial", Font.BOLD, 14));
                    projectOverview.setFont( new Font("Arial", Font.PLAIN, 14));
                    allTasks.setFont( new Font("Arial", Font.BOLD, 14));
                } else {
                    updateButtons = false;
                    projectButton.setFont( new Font("Arial", Font.PLAIN, 14));
                    projectOverview.setFont( new Font("Arial", Font.PLAIN, 14));
                    allTasks.setFont( new Font("Arial", Font.PLAIN, 14));
                }

                System.out.println(baseRight.getComponents());

                JPanel buttonPanel = new JPanel();
                GridLayout buttonPanelLayout = new GridLayout(2, 1);
                buttonPanel.setLayout(buttonPanelLayout);
                buttonPanel.add(projectOverview);
                buttonPanel.add(allTasks);
                buttonPanel.setBorder( new EmptyBorder(0,15, 0, 0));

                ArrayList<JButton> buttons = new ArrayList<>();
                buttons.add(projectButton);
                buttons.add(projectOverview);
                buttons.add(allTasks);

                System.out.println("project id: " + p.getProjectId());
                allProjectButtonsId = new ArrayList<>();
                allProjectButtons = new ArrayList<>();
                allProjectButtonsId.add(0, p.getProjectId());
                allProjectButtons.add(0, buttons);

                singleProject.add(buttonPanel, BorderLayout.SOUTH);

                projectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        user.setCurrentProjectId(p.getProjectId());
                        currentView = "overview";
                        baseRight.removeAll();
                        baseRight.add(new ProjectOverview(user, p, new BaseView(user)).getProjectOverviewPanel());
                        baseRight.revalidate();
                        baseRight.repaint();
                        baseLeft.removeAll();
                        baseLeft.add(getProjectPanel());
                        baseLeft.revalidate();
                        baseLeft.repaint();
                    }
                });

                projectOverview.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        user.setCurrentProjectId(p.getProjectId());
                        currentView = "overview";
                        baseRight.removeAll();
                        baseRight.add(new ProjectOverview(user, p, new BaseView(user)).getProjectOverviewPanel());
                        baseRight.revalidate();
                        baseRight.repaint();
                        baseLeft.removeAll();
                        baseLeft.add(getProjectPanel());
                        baseLeft.revalidate();
                        baseLeft.repaint();
                    }
                });

                allTasks.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        user.setCurrentProjectId(p.getProjectId());
                        currentView = "tasks";
                        baseRight.removeAll();
                        baseRight.add(new AllTasksView(user, p).getAllTasksPanel());
                        baseRight.revalidate();
                        baseRight.repaint();
                        baseLeft.removeAll();
                        baseLeft.add(getProjectPanel());
                        baseLeft.revalidate();
                        baseLeft.repaint();
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

  @Override
  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == addNewProjectButton) {
      BasePanel.removeAll();
      new projectModalView("New Project", null, user);
      basePanel();
      BasePanel.revalidate();
      BasePanel.repaint();
      currentView = "tasks";
    }
    if (src == projectOverview.getDelete()) {
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
        Project currentProj = (Project)user.getProjectsForUser().get(0);
        int currentId = user.getCurrentProjectIdForUser();
        for (IProject p : user.getProjectsForUser()) {
          Project proj = (Project)p;
          if (proj.getProjectId() == currentId) {
            currentProj = proj;
          }
        }
        user.getProjectsForUser().remove(currentProj);
        System.out.println(user.getProjectsForUser());

        user.setCurrentProjectId(0);
        removeAll();
        BasePanel.removeAll();
        basePanel();
        BasePanel.revalidate();
        BasePanel.repaint();
      }
    }
  }
}