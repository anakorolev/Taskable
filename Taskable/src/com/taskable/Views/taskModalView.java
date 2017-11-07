package com.taskable.Views;

import com.taskable.Vendor.CustomizedButtonUI;
import com.taskable.model.Project;
import com.taskable.model.Task;

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
public class taskModalView extends JFrame{
    private Task task;
    private Project project;
    private JTextField inputTextField;
    private JTextArea inputTextArea;
    private ArrayList<JComboBox> inputDropDowns = new ArrayList<JComboBox>();
    JDialog dialog = new JDialog(this, "", Dialog.ModalityType.APPLICATION_MODAL);

    public taskModalView(String title, Task t, Project proj) {
        this.task = t;
        this.project = proj;
        dialog.setTitle(title);
        showTaskDialog();
    }

    private void showTaskDialog() {
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
        GridLayout labelLayout = new GridLayout(4,2,20,20);

        JLabel titleLabel = new JLabel("Title");
        JLabel descriptionLabel = new JLabel("Description");
        JLabel assigneeLabel = new JLabel("Assignee");
        JLabel dueDateLabel = new JLabel("Due Date");

        JLabel spacer = new JLabel("");
        JLabel spacer2 = new JLabel("");
        JLabel spacer3 = new JLabel("");
        JLabel spacer4 = new JLabel("");

        labels.setLayout(labelLayout);
        labels.add(spacer, labelLayout);
        labels.add(titleLabel, labelLayout);
        labels.add(spacer2, labelLayout);
        labels.add(descriptionLabel, labelLayout);
        labels.add(spacer3, labelLayout);
        labels.add(assigneeLabel, labelLayout);
        labels.add(spacer4, labelLayout);
        labels.add(dueDateLabel, labelLayout);

        return labels;
    }

    private JPanel setInputs() {
        JPanel inputs = new JPanel();
        GridLayout inputLayout = new GridLayout(0,1,60,20);
        GridLayout dueDateLayout = new GridLayout(1,3,5,5);

        JTextField titleInput = new JTextField();
        titleInput.setSize(100, 20);
        titleInput.setMaximumSize(new Dimension(100, 20));

        JTextArea descriptionInput = new JTextArea();

        JComboBox assigneeSelect = new JComboBox();

        for(int i = 0; i < this.project.getProjectMembers().size(); i++){
            assigneeSelect.addItem(this.project.getProjectMembers().get(i));
        }

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

        if (this.task != null) {
            titleInput.setText(this.task.getTaskName());
            descriptionInput.setText(this.task.getTaskDesc());
            for(int i = 0; i < this.project.getProjectMembers().size(); i++){
                if (this.project.getProjectMembers().get(i).equals(this.task.getTaskUser())) {
                    assigneeSelect.setSelectedIndex(i);
                }
            }

            if (this.task.getTaskDueDate().getMonth() == 0) {
                monthSelect.setSelectedIndex(12);
            } else {
                monthSelect.setSelectedIndex(this.task.getTaskDueDate().getMonth());
            }

            daySelect.setSelectedItem(this.task.getTaskDueDate().getDate());
            yearSelect.setSelectedItem(this.task.getTaskDueDate().getYear());
        }

        dueDateDropDowns.add(monthSelect, dueDateLayout);
        dueDateDropDowns.add(daySelect, dueDateLayout);
        dueDateDropDowns.add(yearSelect, dueDateLayout);

        inputs.setLayout(inputLayout);
        inputs.setBorder(new EmptyBorder(10,0,0,10));
        inputs.add(titleInput, inputLayout);
        inputs.add(descriptionInput, inputLayout);
        inputs.add(assigneeSelect, inputLayout);
        inputs.add(dueDateDropDowns, inputLayout);

        inputTextField = titleInput;
        inputTextArea = descriptionInput;
        inputDropDowns.add(assigneeSelect);
        inputDropDowns.add(yearSelect);
        inputDropDowns.add(monthSelect);
        inputDropDowns.add(daySelect);

        return inputs;
    }

    private JPanel setFooter() {
        JPanel footer = new JPanel();
        JButton closeButton = new JButton("Cancel");
        JButton nextStepButton = new JButton("Create");

        if(task != null) {
            nextStepButton.setText("Update");
        }

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


        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        nextStepButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputTextField.getText().equals("")){
                    JOptionPane.showMessageDialog(dialog,"Project Name is Required");
                } else if(inputDropDowns.get(2).getSelectedItem().toString().equals("Month")){
                    JOptionPane.showMessageDialog(dialog,"Month is Required");
                } else if(inputDropDowns.get(3).getSelectedItem().toString().equals("Day")){
                    JOptionPane.showMessageDialog(dialog,"Day is Required");
                } else if(inputDropDowns.get(1).getSelectedItem().toString().equals("Year")) {
                    JOptionPane.showMessageDialog(dialog, "Year is Required");
                } else {
                    dispose();
                    updateTask(task);
                }
            }
        });
        GridLayout buttonLayout = new GridLayout(1, 0, 20, 20);

        footer.setLayout(buttonLayout);
        footer.setBorder(new EmptyBorder(10,10,10,10));
        footer.add(new JPanel(), buttonLayout);
        footer.add(new JPanel(), buttonLayout);
        footer.add(closeButton, buttonLayout);
        footer.add(nextStepButton, buttonLayout);

        return footer;
    }

    private void updateTask(Task t) {
        Date date = new Date((Integer)inputDropDowns.get(1).getSelectedItem(),
                inputDropDowns.get(2).getSelectedIndex(),
                inputDropDowns.get(3).getSelectedIndex());

        if (t == null) {
            Task task = new Task(project.getTasks().size(),
                    inputTextField.getText(),
                    inputTextArea.getText(),
                    inputDropDowns.get(0).getSelectedItem().toString(),
                    date);
            project.addATaskToProject(task);
        } else {
            t.setTaskName(inputTextField.getText());
            t.setTaskDesc(inputTextArea.getText());
            t.setTaskDueDate(date);
            t.setTaskUser(inputDropDowns.get(0).getSelectedItem().toString());
        }

        task = t;
    }
}
