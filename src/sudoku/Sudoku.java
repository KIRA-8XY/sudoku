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

        // Constructor
        public Sudoku() {
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());

            cp.add(board, BorderLayout.CENTER);

            // Add a button to the south to re-start the game via board.newGame()
            btnNewGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board.newGame();
                }
            });

            // Add a button to the south to reset the game via board.resetGame()
            btnResetGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board.resetGame();
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(btnNewGame);
            buttonPanel.add(btnResetGame);
            cp.add(buttonPanel, BorderLayout.SOUTH);

            // Initialize the game board to start the game
            board.newGame();

            pack();     // Pack the UI components, instead of using setSize()
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
            setTitle("Sudoku");
            setVisible(true);
        }
    }
