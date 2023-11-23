package org.siit.UI;

import javax.swing.*;

public class App extends JFrame {

    public App() {
        new LoginPage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }

}


//public class App extends JFrame {
//
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//
//    public App() {
//        setTitle("Login Application");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel();
//        add(panel);
//        placeComponents(panel);
//
//        setVisible(true);
//    }
//
//    private void placeComponents(JPanel panel) {
//        panel.setLayout(null);
//
//        JLabel userLabel = new JLabel("Username:");
//        userLabel.setBounds(10, 20, 80, 25);
//        panel.add(userLabel);
//
//        usernameField = new JTextField(20);
//        usernameField.setBounds(100, 20, 165, 25);
//        panel.add(usernameField);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(10, 50, 80, 25);
//        panel.add(passwordLabel);
//
//        passwordField = new JPasswordField(20);
//        passwordField.setBounds(100, 50, 165, 25);
//        panel.add(passwordField);
//
//        JButton loginButton = new JButton("Login");
//        loginButton.setBounds(10, 80, 80, 25);
//        panel.add(loginButton);
//
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String password = new String(passwordField.getPassword());
//                User user = new User();
//
//                if (user.login(username, password)) {
//                    JOptionPane.showMessageDialog(App.this, "Login successful!");
//                    dispose();
//                    new HomePage();
//                } else {
//                    JOptionPane.showMessageDialog(App.this, "Invalid username or password");
//                }
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new App();
//            }
//        });
//    }
//}
