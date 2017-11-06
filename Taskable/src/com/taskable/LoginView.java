package com.taskable;

import com.taskable.Views.projectModalView;
import com.taskable.Views.projectSidePanelView;
import com.taskable.model.IProject;
import com.taskable.model.Project;
import com.taskable.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class LoginView extends JFrame implements ActionListener {
    private JPanel LoginPanel;
    private JTextField tfname;
    private JLabel labname;
    private JLabel labpass;
    private JButton butlogin;
    private JTextField tfpass;
    private JLabel taskableLogo;
    private JPanel enteringArea;
    private JPanel buttonArea;


    protected LoginView() {
        LoginPanel = new JPanel();
        LoginPanel.setLayout(new BorderLayout());
        LoginPanel.setBackground(Color.white);

        taskableLogo = new JLabel("TASKABLE", JLabel.CENTER);
        taskableLogo.setFont(new Font("TimesRoman",Font.BOLD,54));
        taskableLogo.setPreferredSize(new Dimension(0, 150));

        enteringArea = new JPanel();
        enteringArea.setLayout(new GridLayout(4,1));
        enteringArea.setBorder(new EmptyBorder(20,150,20,150));
        labname = new JLabel("Email/ Username:", JLabel.LEFT);
        tfname = new JTextField(10);
        labpass = new JLabel("Password:",JLabel.LEFT);
        tfpass = new JTextField(10);
        enteringArea.setPreferredSize(new Dimension(0, 150));



        enteringArea.add(labname);
        enteringArea.add(tfname);
        enteringArea.add(labpass);
        enteringArea.add(tfpass);

        buttonArea= new JPanel();
        butlogin = new JButton("SIGN-IN");
        butlogin.addActionListener(this);
        buttonArea.setPreferredSize(new Dimension(0, 80));
        buttonArea.add(butlogin);


        LoginPanel.add(taskableLogo, BorderLayout.NORTH);
        LoginPanel.add(enteringArea, BorderLayout.CENTER);
        LoginPanel.add(buttonArea, BorderLayout.SOUTH);

    }

    public JPanel getLoignPanel() {
        return LoginPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==butlogin){
            if(tfname.getText().equals("admin")&&tfpass.getText().equals("1234")){
                this.setVisible(false);
                //mainView Here

                ArrayList<String> members = new ArrayList<String>();
                members.add("Bob");
                members.add("Joe");
                members.add("Bill");

                Project p = new Project(0, "Name", "Desc", members, null, new Date(2018, 8, 28));

                ArrayList<IProject> projects = new ArrayList<IProject>();
                projects.add(p);

                User user = new User(projects, 0);
                new projectSidePanelView(user);

            }else{
                JOptionPane.showMessageDialog(this,"User name and password is not matching, please try again");
            }
        }
    }
}


