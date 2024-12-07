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

/**
 * The main Sudoku program
 */
public class Sudoku extends JFrame {
    private static final long serialUID = 1L;  // to prevent serial warning

    // private variables
    GameBoardPanel board = new GameBoardPanel();
    CountdownClock countdownClock;
    JTextField statusBar = new JTextField("Welcome to Sudoku!");
    private int hintsUsed = 0;
    private static final int MAX_HINTS = 3;
    private JLabel hintsRemainingLabel = new JLabel("Hints remaining: " + (MAX_HINTS - hintsUsed));

    // Constructor
    public Sudoku(String difficulty) {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.setBackground(Color.BLACK); // Set background to black

        countdownClock = new CountdownClock(getCountdownTime(difficulty));
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK); // Set background to black
        
        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timerPanel.setBackground(Color.BLACK); // Set background to black
        timerPanel.add(countdownClock);
        JButton pauseButton = createNeonButton("❚❚", Color.YELLOW);
        JButton resumeButton = createNeonButton("▶", Color.YELLOW);
        timerPanel.add(pauseButton);
        timerPanel.add(resumeButton);
        topPanel.add(timerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK); // Set background to black
        JLabel difficultyLabel = new JLabel("Difficulty: " + difficulty);
        difficultyLabel.setForeground(Color.CYAN); // Neon blue text
        buttonPanel.add(difficultyLabel);
        JButton hintButton = createNeonButton("Hint", Color.YELLOW);
        buttonPanel.add(hintButton);
        hintsRemainingLabel.setForeground(Color.CYAN); // Neon blue text
        buttonPanel.add(hintsRemainingLabel); // Move hints remaining label next to hint button
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        cp.add(topPanel, BorderLayout.NORTH);
        cp.add(board, BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.BLACK); // Set background to black
        menuBar.setForeground(Color.YELLOW); // Neon yellow text

        // Create the "File" menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.CYAN); // Neon yellow text
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem resetGameItem = new JMenuItem("Reset Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add action listeners to the "File" menu items
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.newGame(difficulty);
                countdownClock.resetTimer(getCountdownTime(difficulty));
                hintsUsed = 0; // Reset hints used
                updateStatusBar();
            }
        });

        resetGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetGame();
                countdownClock.resetTimer(getCountdownTime(difficulty));
                hintsUsed = 0; // Reset hints used
                updateStatusBar();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add menu items to the "File" menu
        fileMenu.add(newGameItem);
        fileMenu.add(resetGameItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Create the "Options" menu
        JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setForeground(Color.CYAN); // Neon blue text
        // Add options menu items here if needed

        // Create the "Help" menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setForeground(Color.CYAN); // Neon blue text
        JMenuItem aboutItem = new JMenuItem("About");

        // Add action listener to the "About" menu item
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Sudoku.this, "Sudoku Game\nVersion 1.0\nDeveloped by Group #4", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add menu items to the "Help" menu
        helpMenu.add(aboutItem);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Add the status bar to the south
        statusBar.setEditable(false);
        statusBar.setBackground(Color.BLACK); // Set background to black
        statusBar.setForeground(Color.YELLOW); // Neon yellow text
        cp.add(statusBar, BorderLayout.SOUTH);

        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hintsUsed < MAX_HINTS) {
                    board.revealHint();
                    hintsUsed++;
                    updateStatusBar();
                } else {
                    JOptionPane.showMessageDialog(Sudoku.this, "You have used all your hints.", "No more hints", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownClock.pauseTimer();
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownClock.resumeTimer();
            }
        });

        // Initialize the game board to start the game
        board.newGame(difficulty);
        updateStatusBar();

        pack();     // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
        setTitle("Sudoku");
        setVisible(true);
    }

    private int getCountdownTime(String difficulty) {
        switch (difficulty) {
            case "Easy":
                return 600; // 10 minutes
            case "Intermediate":
                return 300; // 5 minutes
            case "Difficult":
                return 180; // 3 minutes
            default:
                return 600; // Default to 10 minutes
        }
    }

    private void updateStatusBar() {
        int remainingCells = board.getRemainingCells();
        statusBar.setText("Cells remaining: " + remainingCells + " | Hints remaining: " + (MAX_HINTS - hintsUsed));
        hintsRemainingLabel.setText("Hints remaining: " + (MAX_HINTS - hintsUsed));
    }

    private JButton createNeonButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setForeground(color); // Neon text
        button.setBackground(Color.BLACK); // Black background
        button.setBorder(BorderFactory.createLineBorder(color, 2)); // Neon border
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 30)); // Add height to the button
        button.setMargin(new Insets(5, 10, 5, 10)); // Add padding
        return button;
    }
}