package com.taskable;

import com.taskable.Views.BaseView;
import com.taskable.Views.LoginView;
import com.taskable.model.IProject;
import com.taskable.model.Project;
import com.taskable.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by akorolev on 11/3/17.
 */
public class App {




    private JPanel basePanel;
    private static JPanel loginView;


    public App() {



    }

    public static void main(String[] args) {

        LoginView login = new LoginView();

        loginView = login.getLoginPanel();


        JFrame frame = new JFrame("Taskable");

        frame.setContentPane(new App().basePanel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.add(loginView);

        loginView.addComponentListener(new ComponentListener() {
           @Override
           public void componentResized(ComponentEvent e) {
           }

           @Override
           public void componentMoved(ComponentEvent e) {
           }

           @Override
           public void componentShown(ComponentEvent e) {
           }

           @Override
           public void componentHidden(ComponentEvent e) {
               System.out.println("componentHidden");
               ArrayList<String> members = new ArrayList<String>();
               members.add("Bob");
               members.add("Joe");
               members.add("Bill");

               Project p = new Project(0, "Name", "Desc", members, null, new Date(2018, 8, 28));

               ArrayList<IProject> projects = new ArrayList<IProject>();
               projects.add(p);

               User user = new User(projects, 0);
               frame.add(new BaseView(user).getBasePanel());
           }
        });

    }

}
