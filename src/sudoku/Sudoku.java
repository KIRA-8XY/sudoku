/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #4
 * 1 - 5026221177 - Muhammad Ariq Alwin
 * 2 - 5026231065 - Beh Siu Li
 * 3 - Student ID - Student Name 3
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
        private static final long serialVersionUID = 1L;  // to prevent serial warning

        // private variables
        GameBoardPanel board = new GameBoardPanel();
        JButton btnNewGame = new JButton("New Game");
        JButton btnResetGame = new JButton("Reset Game");
        JComboBox<String> difficultyComboBox = new JComboBox<>(new String[]{"Easy", "Intermediate", "Difficult"});

        // Constructor
        public Sudoku() {
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());

            countdownClock = new CountdownClock(getCountdownTime("Easy"));
            cp.add(countdownClock, BorderLayout.NORTH);

            cp.add(board, BorderLayout.CENTER);

            JMenuBar menuBar = new JMenuBar();

            JMenu fileMenu = new JMenu("File");
            JMenuItem newGameItem = new JMenuItem("New Game");
            JMenuItem resetGameItem = new JMenuItem("Reset Game");
            JMenuItem exitItem = new JMenuItem("Exit");

            // Add action listeners to the "File" menu items
            newGameItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                    board.newGame(selectedDifficulty);
                    countdownClock.resetTimer(getCountdownTime(selectedDifficulty));

                }
            });

            // Add a button to the south to re-start the game via board.newGame()


            // Add a button to the south to reset the game via board.resetGame()
            resetGameItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board.resetGame();
                    String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                countdownClock.resetTimer(getCountdownTime(selectedDifficulty));
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
            // Add options menu items here if needed

            // Create the "Help" menu
            JMenu helpMenu = new JMenu("Help");
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

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(new JLabel("Difficulty:"));
            buttonPanel.add(difficultyComboBox);
            cp.add(buttonPanel, BorderLayout.SOUTH);

            // Initialize the game board to start the game
            board.newGame("Easy");

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
    }
    
