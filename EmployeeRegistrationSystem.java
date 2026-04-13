import com.toedter.calendar.JCalendar;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeRegistrationSystem extends JFrame {

    private JTextField fullNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> departmentBox;
    private JCalendar dobCalendar;
    private JTree orgTree;

    public EmployeeRegistrationSystem() {
        setTitle("Employee Registration System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Full Name
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        fullNameField = new JTextField(20);
        panel.add(fullNameField, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Email Address:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        departmentBox = new JComboBox<>(new String[]{"IT", "Finance", "HR", "Marketing"});
        panel.add(departmentBox, gbc);

        // Date of Birth
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Date of Birth:"), gbc);
        gbc.gridx = 1;
        dobCalendar = new JCalendar();
        panel.add(dobCalendar, gbc);

        // Organization Structure (JTree)
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Organization Structure:"), gbc);
        gbc.gridx = 1;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Company");
        DefaultMutableTreeNode deptIT = new DefaultMutableTreeNode("IT Department");
        deptIT.add(new DefaultMutableTreeNode("Development Team"));
        deptIT.add(new DefaultMutableTreeNode("Support Team"));
        DefaultMutableTreeNode deptHR = new DefaultMutableTreeNode("HR Department");
        deptHR.add(new DefaultMutableTreeNode("Recruitment Team"));
        deptHR.add(new DefaultMutableTreeNode("Training Team"));
        root.add(deptIT);
        root.add(deptHR);
        orgTree = new JTree(root);
        JScrollPane treeScroll = new JScrollPane(orgTree);
        treeScroll.setPreferredSize(new Dimension(200, 100));
        panel.add(treeScroll, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        panel.add(buttonPanel, gbc);

        // Add panel to frame
        add(panel);

        // Action Listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClear();
            }
        });
    }

    private void handleSubmit() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        char[] password = passwordField.getPassword();
        String department = (String) departmentBox.getSelectedItem();
        java.util.Date dob = dobCalendar.getDate();

        if (fullName.isEmpty() || email.isEmpty() || password.length == 0 || dob == null) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String summary = "Employee Registration Summary:\n"
                + "Full Name: " + fullName + "\n"
                + "Email: " + email + "\n"
                + "Password: ******\n"
                + "Department: " + department + "\n"
                + "Date of Birth: " + dob.toString();

        JOptionPane.showMessageDialog(this, summary, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleClear() {
        fullNameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        departmentBox.setSelectedIndex(0);
        dobCalendar.setDate(new java.util.Date());
    }

}
