package com.taskable;

import com.taskable.Views.BaseView;
import com.taskable.Views.LoginView;
import com.taskable.model.Project;
import com.taskable.model.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by akorolev on 11/3/17.
 */
public class App {




    private JPanel basePanel;
    private static JPanel loginView, baseView;


    public App() {



    }

    public static void main(String[] args) {


        ArrayList<String> members = new ArrayList<String>();
        members.add("Bob");
        members.add("Joe");
        members.add("Bill");

        Project p = new Project(0, "Name", "Desc", members, null, new Date(2018, 8, 28));
        User user = new User(null, -1);

        LoginView login = new LoginView();
        BaseView base = new BaseView();

        loginView = login.getLoginPanel();
        baseView = base.getBasePanel();



        JFrame frame = new JFrame("Taskable");

        frame.setContentPane(new App().basePanel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.add(loginView);


    }

}
