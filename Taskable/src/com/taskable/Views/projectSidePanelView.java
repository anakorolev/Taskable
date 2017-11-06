package com.taskable.Views;

import com.taskable.model.IProject;
import com.taskable.model.Project;
import com.taskable.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by akorolev on 11/5/17.
 */
public class projectSidePanelView extends JFrame {
    User user;
    JPanel projectPanel;

    public projectSidePanelView(User u) {
        this.user = u;

        projectPanel = new JPanel();
        BorderLayout projectPanelLayout = new BorderLayout();
        projectPanel.setLayout(projectPanelLayout);
        this.add(projectPanel);

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(scrollPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(scrollPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(true);

        projectPanel.add(scrollPane, projectPanelLayout.WEST);

        JButton addProjectButton = new JButton("+");
        projectPanel.add(addProjectButton, projectPanelLayout.SOUTH);

        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new projectModalView("New Project", null, user);
                dispose();
                projectSidePanelView panel = new projectSidePanelView(user);
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

    public JPanel getProjectPanel() {
        return this.projectPanel;
    }

}
