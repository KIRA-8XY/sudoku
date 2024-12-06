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
     private static final long serialVersionUID = 1L;  // to prevent serial warning
 
     // private variables
     GameBoardPanel board = new GameBoardPanel();
     JComboBox<String> difficultyComboBox = new JComboBox<>(new String[]{"Easy", "Intermediate", "Difficult"});
     JTextField statusBar = new JTextField("Welcome to Sudoku!");
     private int hintsUsed = 0;
     private static final int MAX_HINTS = 3;
 
     // Constructor
     public Sudoku() {
         Container cp = getContentPane();
         cp.setLayout(new BorderLayout());
 
         cp.add(board, BorderLayout.CENTER);
 
         // Create the menu bar
         JMenuBar menuBar = new JMenuBar();
 
         // Create the "File" menu
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
                 hintsUsed = 0; // Reset hints used
                 updateStatusBar();
             }
         });
 
         resetGameItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 board.resetGame();
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
 
         // Add the difficulty selection and buttons to the south
         JPanel buttonPanel = new JPanel();
         buttonPanel.add(new JLabel("Difficulty:"));
         buttonPanel.add(difficultyComboBox);
         JButton hintButton = new JButton("Hint");
         buttonPanel.add(hintButton);
         cp.add(buttonPanel, BorderLayout.NORTH);
 
         // Add the status bar to the south
         statusBar.setEditable(false);
         cp.add(statusBar, BorderLayout.SOUTH);

         difficultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                board.newGame(selectedDifficulty);
                hintsUsed = 0; // Reset hints used
                updateStatusBar();
            }
        });

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
 
         // Initialize the game board to start the game
         board.newGame("Easy");
         updateStatusBar();
 
         pack();     // Pack the UI components, instead of using setSize()
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
         setTitle("Sudoku");
         setVisible(true);
     }
 
     private void updateStatusBar() {
         int remainingCells = board.getRemainingCells();
         statusBar.setText("Cells remaining: " + remainingCells);
     }
 }
