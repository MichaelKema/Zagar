package com.zagar.gui;
import java.awt.Font;

import javax.swing.JFrame;   // or FlatLightLaf / FlatDarkLaf / FlatIntelliJLaf
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
public class home {
        public static void main(String[] args) {
        // Set the look & feel BEFORE creating any Swing components
        FlatMacDarkLaf.setup();  // try FlatLightLaf.setup() if you prefer light

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zagar");
            JTextField text = new JTextField();
            JPanel panel = new JPanel();
            JLabel title = new JLabel();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Simple content
            panel.add(new JLabel("Zagar"));
            text.add(new JTextField());
            text.setSize(400, 50);
            title.setFont(new Font("Monsterrat", Font.PLAIN,60));
            title.setSize(100,100);
            text.setLocationRelativeTo(null);


            panel.add(text);
            panel.add(title);
            frame.add(panel);
            frame.setSize(800, 800);
            frame.add(panel);
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);
        });
    }
}
