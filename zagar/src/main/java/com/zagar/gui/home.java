package com.zagar.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;   // or FlatLightLaf / FlatDarkLaf / FlatIntelliJLaf
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class home {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void main(String[] args) {
        // Set the look & feel BEFORE creating any Swing components
        FlatMacDarkLaf.setup();  // try FlatLightLaf.setup() if you prefer light

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zagar");
            JTextField text = new JTextField();
            JPanel panel = new JPanel();
            JLabel title = new JLabel("Zagar Networks ", SwingConstants.CENTER);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Simple content
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            if (shouldFill) {

                c.fill = GridBagConstraints.HORIZONTAL;
            }
            // title position
            c.anchor = GridBagConstraints.PAGE_START;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 40;      //make this component tall
            c.weightx = 0.0;
            c.gridwidth = 3;
            c.gridx = 0;
            c.gridy = 0;

            panel.add(title, c);

            // textbox
            c.anchor = GridBagConstraints.PAGE_END;
            c.gridy = 2;
            c.gridx = 2;
            c.gridwidth = 3;   //2 columns wide
            c.insets = new Insets(60, 0, 0, 0);  //top padding
            panel.add(text, c);

            text.add(new JTextField());
            text.setSize(400, 50);
            title.setFont(new Font("Monsterrat", Font.PLAIN, 60));

            frame.setSize(800, 800);
            frame.add(panel);
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);
        });
    }
}
