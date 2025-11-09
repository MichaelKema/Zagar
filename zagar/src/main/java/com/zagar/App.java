package com.zagar;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;   // or FlatLightLaf / FlatDarkLaf / FlatIntelliJLaf
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Set the look & feel BEFORE creating any Swing components
        FlatMacDarkLaf.setup();  // try FlatLightLaf.setup() if you prefer light

        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Hello FlatLaf");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Simple content
            f.add(new JLabel("Hello World!", SwingConstants.CENTER));

            f.setSize(360, 200);
            f.setLocationRelativeTo(null); // center on screen
            f.setVisible(true);
        });
    }
}
