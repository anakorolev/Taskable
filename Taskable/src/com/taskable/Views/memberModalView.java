package com.taskable.Views;

import com.taskable.Vendor.CustomizedButtonUI;
import com.taskable.model.Project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by akorolev on 11/4/17.
 */
public class memberModalView extends JFrame{
    private final Project project;
    private final JDialog dialog = new JDialog(this, "", Dialog.ModalityType.APPLICATION_MODAL);
    private ArrayList<JTextField> textFields = new ArrayList<JTextField>();

    public memberModalView(Project proj) {
        this.project = proj;
        showMemberDialog();
    }

    public void showMemberDialog() {
        dialog.setLayout(new BorderLayout());
        JPanel panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(100, 300));

        JPanel panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(100, 300));

        dialog.add(panelLeft, BorderLayout.WEST);
        dialog.add(setFields(), BorderLayout.CENTER);
        dialog.add(panelRight, BorderLayout.EAST);
        dialog.add(setFooter(), BorderLayout.SOUTH);
        dialog.setBounds(350, 350, 500, 300);
        dialog.setVisible(true);
    }

    public void setTitle(String title) {
        dialog.setTitle("title");
    }

    private JScrollPane setFields() {
        final JPanel fields = new JPanel();
        final GridLayout fieldLayout = new GridLayout(0,1,10,10);
        fields.setLayout(fieldLayout);

        JTextField adminField = new JTextField();
        adminField.setText("Admin");
        adminField.setEnabled(false);
        textFields.add(adminField);
        fields.add(adminField, fieldLayout);

        if (project.getProjectMembers().size() > 0) {
            for(int i = 0; i < project.getProjectMembers().size(); i++) {
                JTextField memberInput = new JTextField();
                memberInput.setText(project.getProjectMembers().get(i));
                textFields.add(memberInput);
                fields.add(memberInput, fieldLayout);
            }

        } else {
            int count = 0;
            while (count < 3) {
                JTextField memberInput = new JTextField();
                textFields.add(memberInput);
                fields.add(memberInput, fieldLayout);
                count++;
            }
        }

        final JScrollPane scrollPane = new JScrollPane(fields,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(fields);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(true);

        final JButton addMemberButton = new JButton("Add Member");

        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField memField = new JTextField();
                memField.setPreferredSize(new Dimension(100, 30));
                int index = fields.getComponentCount();
                fields.remove(index-1);
                fields.add(memField, fieldLayout);
                fields.add(addMemberButton);
                fields.revalidate();
                fields.repaint();
                fields.setPreferredSize(new Dimension(fields.getWidth(), fields.getHeight()+60));

                textFields.add(memField);
            }
        });

        fields.add(addMemberButton, fieldLayout);

        return scrollPane;
    }

    private ArrayList getFields() {
        ArrayList<String> membersList = new ArrayList<>();
        for (int i = 0; i < textFields.size(); i++) {
            if(!textFields.get(i).getText().equals("")) {
                membersList.add(textFields.get(i).getText());
            }
        }

        return membersList;
    }

    private JPanel setFooter() {
        JPanel footer = new JPanel();
        JButton closeButton = new JButton("Cancel");
        JButton nextStepButton = new JButton("Create");

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
                dispose();
                project.setProjectMembers(getFields());
            }
        });

        footer.setLayout(new GridLayout());
        footer.add(new JPanel(), GridLayout.class);
        footer.add(new JPanel(), GridLayout.class);
        footer.add(closeButton, GridLayout.class);
        footer.add(nextStepButton, GridLayout.class);

        return footer;
    }
}
