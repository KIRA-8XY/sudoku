/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #1
 * 1 - 5026221177 - Muhammad Ariq Alwin
 * 2 - 5026231065 - Beh Siu Li
 * 3 - Student ID - Student Name 3
 */



package sudoku;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sudoku();
            }
        });
    }
}