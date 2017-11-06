package com.taskable.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        taskableLogo = new JLabel("TASKABLE", JLabel.CENTER);
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


        LoginPanel.add(taskableLogo, BorderLayout.NORTH);
        LoginPanel.add(enteringArea, BorderLayout.CENTER);
        LoginPanel.add(buttonArea, BorderLayout.SOUTH);

    }

    public JPanel getLoginPanel() {
        return LoginPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==butlogin){
            if(tfname.getText().equals("admin")&&tfpass.getText().equals("1234")){
                //BaseView here


            }else{
                JOptionPane.showMessageDialog(this,"User name and password is not matching, please try again");
            }
        }
    }
}


