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
import java.awt.event.*;
import javax.swing.*;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60;   // Cell width/height in pixels
    public static final int BOARD_WIDTH  = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;


    // Board width/height in pixels

    // Define properties
    Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();

    /** Constructor */
    public GameBoardPanel() {
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));  // JPanel

        // Allocate the 2D array of Cell, and added into JPanel.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);   // JPanel
            }
        }

        CellInputListener listener = new CellInputListener();

        for (int row=0;row<9;row++) {
            for (int col=0;col<9;col++) {
                if (cells[row][col].isEditable()) {
                    cells[row][col].addActionListener(listener);   // For all editable rows and cols
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }
    

    public void newGame(String difficulty) {
        // Generate a new puzzle based on the selected difficulty
        puzzle.newPuzzle(difficulty);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
                    for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                        cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
                    }
                }
                revalidate();
                repaint();
            }
        });
    }

    public void resetGame() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (!puzzle.isGiven[row][col]) {
                    cells[row][col].setText("");
                    cells[row][col].setStatus(CellStatus.TO_GUESS);
                    cells[row][col].setBackground(Cell.BG_TO_GUESS); // Reset background color
                    cells[row][col].paint();
                } else {
                    cells[row][col].setBackground(Cell.BG_GIVEN); // Reset background color for given cells
                }
            }
        }
    }

    public void revealHint() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS) {
                    cells[row][col].setText(String.valueOf(puzzle.numbers[row][col]));
                    cells[row][col].setStatus(CellStatus.CORRECT_GUESS);
                    cells[row][col].setBackground(Cell.BG_CORRECT_GUESS);
                    cells[row][col].paint();
                    return; // Reveal only one hint
                }
            }
        }
    }

    
    public int getRemainingCells() {
        int remainingCells = 0;
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS) {
                    remainingCells++;
                }
            }
        }
        return remainingCells;
    }

    /**
     * Return true if the puzzle is solved
     * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
     */
    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getRemainingCells() {
        int remainingCells = 0;
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS) {
                    remainingCells++;
                }
            }
        }
        return remainingCells;
    }

    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get a reference of the JTextField that triggers this action event
            Cell sourceCell = (Cell) e.getSource();
            // Retrieve the int entered
            int numberIn = Integer.parseInt(sourceCell.getText());
            // For debugging
            System.out.println("You entered " + numberIn);

            // Check for conflicts
            boolean conflict = checkForConflicts(sourceCell, numberIn);

            if (!conflict) {
                sourceCell.status = CellStatus.CORRECT_GUESS;
            } else {
                sourceCell.status = CellStatus.WRONG_GUESS;
            }
            sourceCell.paint(); // re-paint this cell based on its status
            if (isSolved()) {
                JOptionPane.showMessageDialog(null, "Congratulations! You've solved the puzzle!");
            }
        }

        private boolean checkForConflicts(Cell sourceCell, int numberIn) {
            boolean conflict = false;
            int row = sourceCell.getRow();
            int col = sourceCell.getCol();

            // Clear previous conflict highlights
            clearConflictHighlights();

            // Check row and column
            for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
                if (cells[row][i].getNumber() == numberIn && i != col) {
                    conflict = true;
                    cells[row][i].setBackground(Cell.BG_WRONG_GUESS);
                    sourceCell.setBackground(Cell.BG_WRONG_GUESS);
                }
                if (cells[i][col].getNumber() == numberIn && i != row) {
                    conflict = true;
                    cells[i][col].setBackground(Cell.BG_WRONG_GUESS);
                    sourceCell.setBackground(Cell.BG_WRONG_GUESS);
                }
            }

            // Check sub-grid
            int subGridRowStart = (row / SudokuConstants.SUBGRID_SIZE) * SudokuConstants.SUBGRID_SIZE;
            int subGridColStart = (col / SudokuConstants.SUBGRID_SIZE) * SudokuConstants.SUBGRID_SIZE;
            for (int r = subGridRowStart; r < subGridRowStart + SudokuConstants.SUBGRID_SIZE; r++) {
                for (int c = subGridColStart; c < subGridColStart + SudokuConstants.SUBGRID_SIZE; c++) {
                    if (cells[r][c].getNumber() == numberIn && (r != row || c != col)) {
                        conflict = true;
                        cells[r][c].setBackground(Cell.BG_WRONG_GUESS);
                        sourceCell.setBackground(Cell.BG_WRONG_GUESS);
                    }
                }
            }

            return conflict;
        }

        private void clearConflictHighlights() {
            for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
                for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                    if (cells[row][col].status == CellStatus.WRONG_GUESS) {
                        cells[row][col].setBackground(Cell.BG_TO_GUESS);
                    }
                }
            }
        }
    }
}
