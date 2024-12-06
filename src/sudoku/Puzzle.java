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

import java.util.Random;

public class Puzzle {
    // All variables have package access
    // The numbers on the puzzle
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    // The clues - isGiven (no need to guess) or need to guess
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    private static final Random random = new Random();

    // Constructor
    public Puzzle() {
        super();
    }

    // Generate a new puzzle given the difficulty level.
    // This method shall set (or update) the arrays numbers and isGiven
    public void newPuzzle(String difficulty) {
        int[][][] hardcodedNumbers;
        boolean[][][] hardcodedIsGiven;

        switch (difficulty) {
            case "Easy":
                hardcodedNumbers = new int[][][]{
                    {
                        {5, 3, 4, 6, 7, 8, 9, 1, 2},
                        {6, 7, 2, 1, 9, 5, 3, 4, 8},
                        {1, 9, 8, 3, 4, 2, 5, 6, 7},
                        {8, 5, 9, 7, 6, 1, 4, 2, 3},
                        {4, 2, 6, 8, 5, 3, 7, 9, 1},
                        {7, 1, 3, 9, 2, 4, 8, 5, 6},
                        {9, 6, 1, 5, 3, 7, 2, 8, 4},
                        {2, 8, 7, 4, 1, 9, 6, 3, 5},
                        {3, 4, 5, 2, 8, 6, 1, 7, 9}
                    },
                    {
                        {8, 2, 7, 1, 5, 4, 3, 9, 6},
                        {9, 6, 5, 3, 2, 7, 1, 4, 8},
                        {3, 4, 1, 6, 8, 9, 7, 5, 2},
                        {5, 9, 3, 4, 6, 8, 2, 7, 1},
                        {4, 7, 2, 5, 1, 3, 6, 8, 9},
                        {6, 1, 8, 9, 7, 2, 4, 3, 5},
                        {7, 8, 6, 2, 3, 5, 9, 1, 4},
                        {1, 5, 4, 7, 9, 6, 8, 2, 3},
                        {2, 3, 9, 8, 4, 1, 5, 6, 7}
                    },
                    {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {4, 5, 6, 7, 8, 9, 1, 2, 3},
                        {7, 8, 9, 1, 2, 3, 4, 5, 6},
                        {2, 3, 4, 5, 6, 7, 8, 9, 1},
                        {5, 6, 7, 8, 9, 1, 2, 3, 4},
                        {8, 9, 1, 2, 3, 4, 5, 6, 7},
                        {3, 4, 5, 6, 7, 8, 9, 1, 2},
                        {6, 7, 8, 9, 1, 2, 3, 4, 5},
                        {9, 1, 2, 3, 4, 5, 6, 7, 8}
                    }
                };
                hardcodedIsGiven = new boolean[][][]{
                    {
                        {true, true, false, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, false, true, true, true, true},
                        {true, true, true, true, true, true, true, false, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, false, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, false, true, true}
                    },
                    {
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, false, true, true, true, true, true, true, true},
                        {true, true, true, false, true, true, true, true, true},
                        {true, true, true, true, true, true, true, false, true},
                        {true, true, true, true, false, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, false, true, true, true, false, true, true, true},
                        {true, true, true, true, true, true, true, true, true}
                    },
                    {
                        {true, false, true, true, true, true, true, true, true},
                        {true, true, false, true, true, true, true, true, false},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, false, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, false, false, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {false, true, true, true, true, false, true, true, true}
                    }
                };
                break;
            case "Intermediate":
                hardcodedNumbers = new int[][][]{
                    {
                        {5, 3, 0, 0, 7, 0, 0, 0, 0},
                        {6, 0, 0, 1, 9, 5, 0, 0, 0},
                        {0, 9, 8, 0, 0, 0, 0, 6, 0},
                        {8, 0, 0, 0, 6, 0, 0, 0, 3},
                        {4, 0, 0, 8, 0, 3, 0, 0, 1},
                        {7, 0, 0, 0, 2, 0, 0, 0, 6},
                        {0, 6, 0, 0, 0, 0, 2, 8, 0},
                        {0, 0, 0, 4, 1, 9, 0, 0, 5},
                        {0, 0, 0, 0, 8, 0, 0, 7, 9}
                    },
                    {
                        {0, 0, 0, 6, 0, 0, 4, 0, 0},
                        {7, 0, 0, 0, 0, 3, 6, 0, 0},
                        {0, 0, 0, 0, 9, 1, 0, 8, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 5, 0, 1, 8, 0, 0, 0, 3},
                        {0, 0, 0, 3, 0, 6, 0, 4, 5},
                        {0, 4, 0, 2, 0, 0, 0, 6, 0},
                        {9, 0, 3, 0, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 0, 1, 0, 0}
                    },
                    {
                        {5, 0, 0, 0, 8, 0, 0, 0, 0},
                        {0, 3, 0, 0, 0, 2, 0, 9, 0},
                        {0, 0, 9, 8, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 3, 0, 0, 0, 9},
                        {0, 9, 0, 0, 0, 0, 0, 8, 0},
                        {0, 0, 0, 0, 0, 9, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 3, 0, 0},
                        {8, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 9, 0, 0, 0, 0, 8}
                    }
                };
                hardcodedIsGiven = new boolean[][][]{
                    {
                        {true, true, false, false, true, false, false, false, false},
                        {true, false, false, true, true, true, false, false, false},
                        {false, true, true, false, false, false, false, true, false},
                        {true, false, false, false, true, false, false, false, true},
                        {true, false, false, true, false, true, false, false, true},
                        {true, false, false, false, true, false, false, false, true},
                        {false, true, false, false, false, false, true, true, false},
                        {false, false, false, true, true, true, false, false, true},
                        {false, false, false, false, true, false, false, true, true}
                    },
                    {
                        {false, false, false, true, false, false, true, false, false},
                        {true, false, false, false, false, true, true, false, false},
                        {false, false, false, false, true, true, false, true, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, true, false, true, true, false, false, false, true},
                        {false, false, false, true, false, true, false, true, true},
                        {false, true, false, true, false, false, false, true, false},
                        {true, false, true, false, false, false, false, false, false},
                        {false, true, false, false, false, false, true, false, false}
                    },
                    {
                        {true, false, true, false, true, true, false, true, true},
                        {true, true, false, true, false, true, true, true, false},
                        {false, true, true, true, false, true, true, false, true},
                        {true, false, true, false, true, false, true, true, false},
                        {false, true, false, true, true, true, false, true, true},
                        {true, true, false, false, true, false, true, true, true},
                        {false, false, true, true, false, true, true, false, true},
                        {true, true, true, false, false, true, false, true, false},
                        {false, true, false, true, true, false, true, true, true}
                    }
                };
                break;
            case "Difficult":
                hardcodedNumbers = new int[][][]{
                    {
                        {8, 2, 7, 1, 5, 4, 3, 9, 6},
                        {9, 6, 5, 3, 2, 7, 1, 4, 8},
                        {3, 4, 1, 6, 8, 9, 7, 5, 2},
                        {5, 9, 3, 4, 6, 8, 2, 7, 1},
                        {4, 7, 2, 5, 1, 3, 6, 8, 9},
                        {6, 1, 8, 9, 7, 2, 4, 3, 5},
                        {7, 8, 6, 2, 3, 5, 9, 1, 4},
                        {1, 5, 4, 7, 9, 6, 8, 2, 3},
                        {2, 3, 9, 8, 4, 1, 5, 6, 7}
                    },
                    {

                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {4, 5, 6, 7, 8, 9, 1, 2, 3},
                        {7, 8, 9, 1, 2, 3, 4, 5, 6},
                        {2, 3, 4, 5, 6, 7, 8, 9, 1},
                        {5, 6, 7, 8, 9, 1, 2, 3, 4},
                        {8, 9, 1, 2, 3, 4, 5, 6, 7},
                        {3, 4, 5, 6, 7, 8, 9, 1, 2},
                        {6, 7, 8, 9, 1, 2, 3, 4, 5},
                        {9, 1, 2, 3, 4, 5, 6, 7, 8}
                    }
                };
                hardcodedIsGiven = new boolean[][][]{
                    {
                        {false, false, false, false, true, false, false, false, false},
                        {false, true, false, false, false, false, false, true, false},
                        {false, false, false, true, false, false, false, false, false},
                        {false, false, true, false, false, false, false, false, false},
                        {false, false, false, false, false, true, false, false, false},
                        {false, false, false, false, false, false, false, false, true},
                        {false, true, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, true, false, false},
                        {false, false, false, false, false, false, false, false, false}
                    },
                    { // Difficult 2
                        {false, false, true, false, false, false, false, false, false},
                        {false, false, false, false, true, false, false, false, false},
                        {false, false, false, false, false, false, true, false, false},
                        {false, false, false, false, false, false, false, false, true},
                        {false, true, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, true, false},
                        {false, false, false, false, false, true, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false}
                    }
                };
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        }

        // Randomly select one of the puzzles for the given difficulty
        int index = random.nextInt(hardcodedNumbers.length);
        int[][] selectedNumbers = hardcodedNumbers[index];
        boolean[][] selectedIsGiven = hardcodedIsGiven[index];

        // Copy from selectedNumbers into the array "numbers"
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = selectedNumbers[row][col];
                isGiven[row][col] = selectedIsGiven[row][col];
            }
        }
    }
}
