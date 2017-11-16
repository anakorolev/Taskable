package com.taskable.Views;


import com.taskable.Vendor.CustomizedButtonUI;
import com.taskable.model.ITask;
import com.taskable.model.Project;
import com.taskable.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by akorolev on 11/4/17.
 */
public class projectModalView extends JFrame{
    Project project;
    User user;
    JDialog dialog = new JDialog(this, "", Dialog.ModalityType.APPLICATION_MODAL);
    private JTextField inputTextField;
    private JTextArea inputTextArea;
    private ArrayList<JComboBox> inputDropDowns = new ArrayList<JComboBox>();

    public projectModalView(String title, Project proj, User u) {
        this.project = proj;
        this.user = u;
        dialog.setTitle(title);
        showProjectDialog();
    }

    private void showProjectDialog() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(50, 350));
        panel.setLayout(new GridLayout(0,1));

        dialog.add(setLabels(), BorderLayout.WEST);
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(setInputs(), BorderLayout.EAST);
        dialog.add(setFooter(), BorderLayout.SOUTH);
        dialog.setBounds(150, 150, 500, 350);
        dialog.setVisible(true);
    }

    private JPanel setLabels() {
        JPanel labels = new JPanel();
        GridLayout labelLayout = new GridLayout(3,2,20,20);
        JLabel titleLabel = new JLabel("Title");
        JLabel descriptionLabel = new JLabel("Description");
        JLabel dueDateLabel = new JLabel("Due Date");

        JLabel spacer = new JLabel("");
        JLabel spacer2 = new JLabel("");
        JLabel spacer3 = new JLabel("");

        labels.setLayout(labelLayout);
        labels.add(spacer, labelLayout);
        labels.add(titleLabel, labelLayout);
        labels.add(spacer2, labelLayout);
        labels.add(descriptionLabel, labelLayout);
        labels.add(spacer3, labelLayout);
        labels.add(dueDateLabel, labelLayout);

        return labels;
    }

    private JPanel setInputs() {
        JPanel inputs = new JPanel();
        GridLayout inputLayout = new GridLayout(3,1,20,20);
        GridLayout dueDateLayout = new GridLayout(1,3,5,5);

        JTextField titleInput = new JTextField();
        titleInput.setSize(100, 20);
        titleInput.setMaximumSize(new Dimension(100, 20));

        JTextArea descriptionInput = new JTextArea();

        JPanel dueDateDropDowns = new JPanel();
        JComboBox monthSelect = new JComboBox();
        JComboBox daySelect = new JComboBox();
        JComboBox yearSelect = new JComboBox();

        monthSelect.addItem("Month");
        monthSelect.addItem("January");
        monthSelect.addItem("February");
        monthSelect.addItem("March");
        monthSelect.addItem("April");
        monthSelect.addItem("May");
        monthSelect.addItem("June");
        monthSelect.addItem("July");
        monthSelect.addItem("August");
        monthSelect.addItem("September");
        monthSelect.addItem("October");
        monthSelect.addItem("November");
        monthSelect.addItem("December");

        daySelect.addItem("Day");
        for(int i = 1; i <= 31; i++) {
            daySelect.addItem(i);
        }
        yearSelect.addItem("Year");
        for(int i = 2017; i <= 2025; i++) {
            yearSelect.addItem(i);
        }

        if (this.project != null) {
            titleInput.setText(this.project.getProjectName());
            descriptionInput.setText(this.project.getProjectDesc());

            monthSelect.setSelectedIndex(this.project.getProjectDueDate().getMonth());
            daySelect.setSelectedItem(this.project.getProjectDueDate().getDate());
            yearSelect.setSelectedItem(this.project.getProjectDueDate().getYear());
        }

        dueDateDropDowns.add(monthSelect, dueDateLayout);
        dueDateDropDowns.add(daySelect, dueDateLayout);
        dueDateDropDowns.add(yearSelect, dueDateLayout);

        inputs.setLayout(inputLayout);
        inputs.setBorder(new EmptyBorder(10,0,0,10));
        inputs.add(titleInput, inputLayout);
        inputs.add(descriptionInput, inputLayout);
        inputs.add(dueDateDropDowns, inputLayout);

        inputTextField = titleInput;
        inputTextArea = descriptionInput;
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputDropDowns.add(yearSelect);
        inputDropDowns.add(monthSelect);
        inputDropDowns.add(daySelect);

        return inputs;
    }

    private JPanel setFooter() {
        JPanel footer = new JPanel();
        JButton closeButton = new JButton("Cancel");
        JButton nextStepButton = new JButton("Next");

        closeButton.setUI(new CustomizedButtonUI(
                new Color(220, 227, 230),
                new Color(231, 236, 238),
                new Color(176, 190, 197),
                new Font("Arial", Font.BOLD, 14),
                new Color(50, 55, 56),
                Color.WHITE, new Color(50, 55, 56), null));
        nextStepButton.setUI(new CustomizedButtonUI(
                new Color(7, 176, 221),
                new Color(91, 203, 235),
                new Color(0, 94, 119),
                new Font("Arial", Font.BOLD, 14),
                Color.WHITE, Color.WHITE, Color.WHITE, null));

        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        nextStepButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                if(inputTextField.getText().equals("")){
                    JOptionPane.showMessageDialog(dialog,"Project Name is Required");
                } else if(inputDropDowns.get(1).getSelectedItem().toString().equals("Month")){
                    JOptionPane.showMessageDialog(dialog,"Month is Required");
                } else if(inputDropDowns.get(2).getSelectedItem().toString().equals("Day")){
                    JOptionPane.showMessageDialog(dialog,"Day is Required");
                } else if(inputDropDowns.get(0).getSelectedItem().toString().equals("Year")) {
                    JOptionPane.showMessageDialog(dialog, "Year is Required");
                } else if(inputDropDowns.get(0).getSelectedIndex() == 1 &&
                        inputDropDowns.get(1).getSelectedIndex() < date.getMonth()+1) {
                    JOptionPane.showMessageDialog(dialog, "Cannot Select a Past Date!");
                } else if(inputDropDowns.get(0).getSelectedIndex() == 1 &&
                        inputDropDowns.get(1).getSelectedIndex() == date.getMonth()+1 &&
                        inputDropDowns.get(2).getSelectedIndex() <= date.getDate()) {
                    JOptionPane.showMessageDialog(dialog, "Cannot Select a Past Date!");
                } else {
                    dispose();
                    Project p = updateProject(project);
                    if (dialog.getTitle().equals("Edit Project")){
                        new memberModalView(p, "Edit Members");
                    } else {
                        new memberModalView(p, "Add Members");
                    }

                }
            }
        });

        GridLayout buttonLayout = new GridLayout(1, 0, 20, 20);

        footer.setLayout(buttonLayout);
        footer.setBorder(new EmptyBorder(10,10,10,10));
        footer.add(new JPanel());
        footer.add(new JPanel());
        footer.add(closeButton);
        footer.add(nextStepButton);

        return footer;
    }

    private Project updateProject(Project p) {
        Date date = new Date((Integer)inputDropDowns.get(0).getSelectedItem(),
                inputDropDowns.get(1).getSelectedIndex(),
                inputDropDowns.get(2).getSelectedIndex());

        if (p == null) {
            p = new Project(user.getProjectsForUser().size(),
                    inputTextField.getText(),
                    inputTextArea.getText(),
                    new ArrayList<String>(),
                    new ArrayList<ITask>(),
                    date);
            user.addAProjectForUser(p);
            user.setCurrentProjectId(p.getProjectId());
        } else {
            p.setProjectName(inputTextField.getText());
            p.setProjectDesc(inputTextArea.getText());
            p.setProjectDueDate(date);
        }

        project = p;

        return p;
    }
}
