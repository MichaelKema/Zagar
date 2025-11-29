package com.zagar.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;   // or FlatLightLaf / FlatDarkLaf / FlatIntelliJLaf
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.zagar.credientials.Login;

public class home {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static Login credentials;

    public static void main(String[] args) {
        // Set the look & feel BEFORE creating any Swing components
        FlatMacDarkLaf.setup();  // try FlatLightLaf.setup() if you prefer light

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zagar");
            JTextField text = new JTextField();
            JTextField server = new JTextField();
            JTextField host = new JTextField();
            JButton sendButton = new JButton("Send");
            
            Login credentials = new Login();
            
            sendButton.addActionListener(e -> {
                String Username = "/name " + text.getText();
                credentials.Username(Username);

            });


            addPlaceholder(text, "Username");
            addPlaceholder(server, "Server");
            addPlaceholder(host, "Host");

            JPanel panel = new JPanel();
            JLabel title = new JLabel("Zagar Networks ", SwingConstants.CENTER);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Simple content
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            if (shouldFill) {

                c.fill = GridBagConstraints.HORIZONTAL;
            }

            // Username textbox
            c.gridy = 0;
            c.gridx = 0;
            c.gridwidth = 3;   //2 columns wide
            c.insets = new Insets(60, 0, 0, 0);  //top padding
            panel.add(text, c);

            // Server textbox (below Username)
            c.gridy = 1;
            c.insets = new Insets(20, 0, 0, 0);  //gap between fields
            panel.add(server, c);

            // Host textbox (below Server)
            c.gridy = 2;
            c.insets = new Insets(20, 0, 0, 0);  //gap between fields
            panel.add(host, c);

            c.gridy = 3;
            c.insets = new Insets(20, 0, 0, 0);
            panel.add(sendButton,c );

            sendButton.setPreferredSize(new Dimension(50,50));
            sendButton.setFont(new Font("Monsterrat", Font.PLAIN, 35));

            text.setSize(400, 400);
            title.setFont(new Font("Monsterrat", Font.PLAIN, 60));

            frame.setLayout(new BorderLayout());
            frame.add(title, BorderLayout.NORTH);     // top center
            frame.add(panel, BorderLayout.CENTER);    // rest of content
            
            text.setColumns(20); 
            text.setFont(text.getFont().deriveFont(24f));   // bigger font 
            server.setColumns(20);
            host.setColumns(20);
            server.setFont(text.getFont());
            host.setFont(text.getFont());

            frame.setSize(800, 800);
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);

        });
    }

    private static void addPlaceholder(JTextField field, String placeholder) {
        Color normalColor = field.getForeground();
        Color placeholderColor = Color.GRAY;

        field.setText(placeholder);
        field.setForeground(placeholderColor);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(normalColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(placeholderColor);
                    field.setText(placeholder);
                }
            }
        });
    }
    
}
