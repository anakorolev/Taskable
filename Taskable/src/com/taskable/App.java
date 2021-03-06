package com.taskable;

import com.taskable.Views.BaseView;
import com.taskable.Views.LoginView;
import com.taskable.model.IProject;
import com.taskable.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

/**
 * Created by akorolev on 11/3/17.
 */
public class App extends JFrame{
    private static User user = new User(new ArrayList<IProject>(), 0);

    private JPanel basePanel;
    private static JPanel loginView, baseView;
    private static JFrame frame;


    public App() {
        basePanel = new JPanel();

        basePanel.add(loginView);
        basePanel.setVisible(true);
    }

    public static void main(String[] args) {
        LoginView login = new LoginView();
        loginView = login.getLoginPanel();
        loginView.setVisible(true);

        BaseView base = new BaseView(user);
        baseView = base.getBasePanel();
        baseView.setVisible(false);

        frame = new JFrame("Taskable");
        frame.setPreferredSize(new Dimension(1000,600));

        frame.setContentPane(loginView);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


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
               baseView.setVisible(true);
               frame.setContentPane(baseView);
           }
        });

        baseView.addComponentListener(new ComponentListener() {
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

                loginView.setVisible(true);
                frame.setContentPane(loginView);
            }
        });

    }

}
