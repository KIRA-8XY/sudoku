/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #4
 * 1 - 5026221177 - Muhammad Ariq Alwin
 * 2 - 5026231065 - Beh Siu Li
 * 3 - 5026231168 - Okky Priscila Putri
 */

package sudoku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomeScreen extends JFrame {
    private static final long serialVersionUID = 1L;

    public HomeScreen() {
        setTitle("Sudoku Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.setBackground(Color.BLACK); // Set background to black

        // Title
        JLabel titleLabel = new JLabel("Sudoku Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.YELLOW); // Neon yellow text
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0)); // Add margin to center the title
        cp.add(titleLabel, BorderLayout.NORTH);

        // Description
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center; color: #00FFFF;'>Welcome to the Sudoku Game!<br>Select a difficulty level to start playing.</div></html>", JLabel.CENTER);
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cp.add(descriptionLabel, BorderLayout.CENTER);

        // Difficulty buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        buttonPanel.setBackground(Color.BLACK); // Set background to black

        JButton easyButton = createNeonButton("Easy");
        JButton intermediateButton = createNeonButton("Intermediate");
        JButton difficultButton = createNeonButton("Difficult");

        buttonPanel.add(easyButton);
        buttonPanel.add(intermediateButton);
        buttonPanel.add(difficultButton);
        cp.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Sudoku("Easy");
                dispose();
            }
        });

        intermediateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Sudoku("Intermediate");
                dispose();
            }
        });

        difficultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Sudoku("Difficult");
                dispose();
            }
        });

        setVisible(true);
    }

    private JButton createNeonButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.YELLOW); // Neon yellow text
        button.setBackground(Color.BLACK); // Black background
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); // Neon yellow border
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50)); // Add height to the button
        button.setMargin(new Insets(10, 20, 10, 20)); // Add padding
        return button;
    }
}