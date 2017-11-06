package com.taskable;

import sun.dc.pr.PRError;

import javax.swing.*;

/**
 * Created by akorolev on 11/3/17.
 */
public class App {

    private JPanel basePanel;
    private static JPanel loginView;

    public App() {
        loginView = new LoginView().getLoignPanel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Taskable");

        frame.setContentPane(new App().basePanel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.add(loginView);
    }

}
