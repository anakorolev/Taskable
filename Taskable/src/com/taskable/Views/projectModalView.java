package com.taskable.Views;

import com.taskable.Vendor.CustomizedButtonUI;
import com.taskable.Views.memberModalView;
import com.taskable.model.Project;
import com.taskable.model.Task;
import com.taskable.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
        panel.setPreferredSize(new Dimension(50, 300));
        panel.setLayout(new GridLayout(0,1));

        dialog.add(setLabels(), BorderLayout.WEST);
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(setInputs(), BorderLayout.EAST);
        dialog.add(setFooter(), BorderLayout.SOUTH);
        dialog.setBounds(350, 350, 500, 300);
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
        inputs.add(titleInput, inputLayout);
        inputs.add(descriptionInput, inputLayout);
        inputs.add(dueDateDropDowns, inputLayout);

        inputTextField = titleInput;
        inputTextArea = descriptionInput;
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
                Color.WHITE, new Color(50, 55, 56)));
        nextStepButton.setUI(new CustomizedButtonUI(
                new Color(7, 176, 221),
                new Color(91, 203, 235),
                new Color(0, 94, 119),
                new Font("Arial", Font.BOLD, 14),
                Color.WHITE, Color.WHITE, Color.WHITE));

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
                } else if(inputDropDowns.get(1).getSelectedItem().toString().equals("Month")){
                    JOptionPane.showMessageDialog(dialog,"Month is Required");
                } else if(inputDropDowns.get(2).getSelectedItem().toString().equals("Day")){
                    JOptionPane.showMessageDialog(dialog,"Day is Required");
                } else if(inputDropDowns.get(0).getSelectedItem().toString().equals("Year")) {
                    JOptionPane.showMessageDialog(dialog, "Year is Required");
                } else {
                    dispose();
                    Project p = updateProject(project);
                    new memberModalView(p);
                }
            }
        });

        footer.setLayout(new GridLayout());
        footer.add(new JPanel(), GridLayout.class);
        footer.add(new JPanel(), GridLayout.class);
        footer.add(closeButton, GridLayout.class);
        footer.add(nextStepButton, GridLayout.class);

        return footer;
    }

    private Project updateProject(Project p) {
        Date date = new Date((Integer)inputDropDowns.get(0).getSelectedItem(),
                inputDropDowns.get(1).getSelectedIndex()+1,
                inputDropDowns.get(2).getSelectedIndex()+1);

        if (p == null) {
            p = new Project(user.getProjectsForUser().size(),
                    inputTextField.getText(),
                    inputTextArea.getText(),
                    null,
                    null,
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
