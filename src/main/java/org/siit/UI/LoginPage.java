package org.siit.UI;

import org.siit.Student;
import org.siit.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame{

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        add(panel);

        addComponents(panel);

        setVisible(true);
    }

    private void addComponents(JPanel panel) {
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::onLogin);

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);
    }

    private void onLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        User user = new User();

        if (user.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new HomePage(user.getUserID());
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }
}
