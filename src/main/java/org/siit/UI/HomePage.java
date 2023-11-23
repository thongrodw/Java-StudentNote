package org.siit.UI;

import org.siit.Professor;
import org.siit.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class HomePage extends JFrame {

    private JTextField searchField;
    private JTextArea userDisplayArea;
    Integer professorID;

    public HomePage(Integer userId) {
        professorID = userId;
        initializeUI(Student.getAllStudents(userId));
    }

    private void initializeUI(List<Student> studentList) {
        setTitle("Home Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        addSearchBox(panel);
        addUserDisplayArea(panel, studentList);

        setVisible(true);
    }

    private void addSearchBox(Container container) {
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        JButton addUserButton = new JButton("Submit");

        searchPanel.add(searchField);
        searchPanel.add(addUserButton);

        container.add(searchPanel, BorderLayout.NORTH);

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
        StringBuilder displayText = new StringBuilder("List of Students:\n\n");

        for (Student student : studentList) {
            displayText.append("Name: ").append(student.getFirstname()).append("\n");
            displayText.append("\n");
        }
        userDisplayArea.setText(displayText.toString());
    }

    private void onSubmit(ActionEvent e) {
        String name = searchField.getText();
        addStudent(name);
        displayUsers(Student.getAllStudents(professorID));
    }
    private void addStudent(String name) {
        Professor professor = new Professor();
        professor.addStudent(name, professorID);
    }

}
