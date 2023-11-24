package org.siit.UI;

import org.siit.Professor;
import org.siit.Student;
import org.siit.UI.Components.LogoutComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class HomePage extends JFrame {

    private JTextField searchField;
    private JTextArea userDisplayArea;

    private Integer professorID;
    private List<Student> studentList;

    public HomePage(Integer userId) {
        this.professorID = userId;
        this.studentList = Student.getAllStudents(userId);

        initializeUI(studentList);
    }

    private void initializeUI(List<Student> studentList) {
        setTitle("Home Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        JPanel topPanel = new JPanel(new BorderLayout()); // Container for logout and search
        addLogoutButton(topPanel, BorderLayout.WEST); // Add logoutButton to the left
        addSearchBox(topPanel, BorderLayout.EAST); // Add searchPanel to the right

        panel.add(topPanel, BorderLayout.NORTH);

        addUserDisplayArea(panel, studentList);

        setVisible(true);
    }

    private void addLogoutButton(Container container, Object constraints) {
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            LogoutComponent.logout(this);
            new LoginPage();
        });
        container.add(logoutButton, constraints);
    }

    private void addSearchBox(Container container, Object constraints) {
        JPanel searchPanel = new JPanel(new FlowLayout());

        JLabel addNewLabel = new JLabel("Add new student: ");
        searchPanel.add(addNewLabel);

        searchField = new JTextField(20);
        JButton addUserButton = new JButton("Submit");

        searchPanel.add(searchField);
        searchPanel.add(addUserButton);

        container.add(searchPanel, constraints);

        addUserButton.addActionListener(this::onSubmit);
    }

    private void addUserDisplayArea(Container container, List<Student> studentList) {
        JPanel displayPanel = new JPanel(new BorderLayout());

        userDisplayArea = new JTextArea(10, 30);
        userDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(userDisplayArea);

        displayPanel.add(scrollPane, BorderLayout.CENTER);

        container.add(displayPanel, BorderLayout.CENTER);

        displayUsers(studentList);
    }

    private void displayUsers(List<Student> studentList) {
        JPanel studentPanel = new JPanel(new GridLayout(studentList.size(), 2));

        for (Student student : studentList) {
            JPanel rowPanel = new JPanel(new BorderLayout());

            JLabel nameLabel = new JLabel("Name: ");
            JLabel nameValueLabel = new JLabel(student.getName());

            JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            textPanel.add(nameLabel);
            textPanel.add(nameValueLabel);

            rowPanel.add(textPanel, BorderLayout.WEST);

            JButton studentButton = new JButton("View Notes");
            studentButton.addActionListener(e -> openNotePage(student.getStudentId()));

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(studentButton);

            rowPanel.add(buttonPanel, BorderLayout.EAST);

            studentPanel.add(rowPanel);
        }

        userDisplayArea.setText("");

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(studentPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(containerPanel);

        userDisplayArea.setLayout(new BorderLayout());
        userDisplayArea.add(scrollPane, BorderLayout.CENTER);
        userDisplayArea.revalidate();
    }

    private void onSubmit(ActionEvent e) {
        String name = searchField.getText();
        addStudent(name);
        new HomePage(professorID);
    }

    private void openNotePage(int studentId) {
        System.out.println("Opening NotePage for Student ID: " + studentId);
        new NotePage(studentId, professorID).setVisible(true);
    }

    private void addStudent(String name) {
        Professor professor = new Professor();
        if(studentList.stream().filter(student -> student.getName().equals(name)).count() > 0) {
            JOptionPane.showMessageDialog(this, "Student already exists!");
            return;
        }
        professor.addStudent(name, professorID);
        JOptionPane.showMessageDialog(this, "You've added a new student!");
    }
}
