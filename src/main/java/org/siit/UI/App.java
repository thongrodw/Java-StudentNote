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
