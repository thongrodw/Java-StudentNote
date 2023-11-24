package org.siit.UI.Components;

import org.siit.UI.LoginPage;

import javax.swing.*;

public class LogoutComponent {

    public static void logout(JFrame frame) {
        int result = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to log out?", "Logout",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }
}