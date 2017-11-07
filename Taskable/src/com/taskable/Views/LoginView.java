package com.taskable.Views;

import com.taskable.TaskOverview;
import com.taskable.Vendor.CustomizedButtonUI;
import com.taskable.model.ITask;
import com.taskable.model.Project;
import com.taskable.model.Task;
import com.taskable.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame implements ActionListener {
    private JPanel LoginPanel;
    private JTextField tfname;
    private JLabel labname;
    private JLabel labpass;
    private JButton butlogin;
    private JPasswordField tfpass;
    private JLabel taskableLogo;
    private JPanel enteringArea;
    private JPanel buttonArea;
    public boolean showBaseView = false;


    public LoginView() {
        LoginPanel = new JPanel();
        LoginPanel.setLayout(new BorderLayout());
        LoginPanel.setBackground(Color.white);

        taskableLogo = new JLabel("TASKABLE", createImageIcon("../icons/taskable_0.5x.png"), JLabel.CENTER);
        taskableLogo.setFont(new Font("TimesRoman",Font.BOLD,54));
        taskableLogo.setPreferredSize(new Dimension(0, 150));

        enteringArea = new JPanel();
        enteringArea.setLayout(new GridLayout(4,1));
        enteringArea.setBorder(new EmptyBorder(20,150,20,150));
        labname = new JLabel("Email/ Username:", JLabel.LEFT);
        tfname = new JTextField(10);
        labpass = new JLabel("Password:",JLabel.LEFT);
        tfpass = new JPasswordField(10);
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

        butlogin.setUI(new CustomizedButtonUI(
                new Color(7, 176, 221),
                new Color(91, 203, 235),
                new Color(0, 94, 119),
                new Font("Arial", Font.BOLD, 14),
                Color.WHITE, Color.WHITE, Color.WHITE));


        LoginPanel.add(taskableLogo, BorderLayout.NORTH);
        LoginPanel.add(enteringArea, BorderLayout.CENTER);
        LoginPanel.add(buttonArea, BorderLayout.SOUTH);

    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = LoginView.class.getResource(path);
        //error handling omitted for clarity...
        return new ImageIcon(imgURL);
    }

    public JPanel getLoginPanel() {
        return LoginPanel;
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList<String> members = new ArrayList<String>();
        members.add("Bob");
        members.add("Joe");
        members.add("Bill");

        Task t = new Task(1, "task", "description", "me", new Date());
        ArrayList<ITask> taskList = new ArrayList<>();
        taskList.add(t);
        Project p = new Project(0, "Name", "Desc", members, taskList, new Date(2018, 8, 28));
        User user = new User(null, -1);

        if(e.getSource()==butlogin){
            if(tfname.getText().equals("admin")&&tfpass.getText().equals("1234")){
                //BaseView here
                LoginPanel.setVisible(false);
                this.setVisible(false);
                //mainView Here
                this.dispose();
                new TaskOverview(user, p, t);


            }else{
                JOptionPane.showMessageDialog(this,"Incorrect username and password, please try again");
            }
        }
    }
}

