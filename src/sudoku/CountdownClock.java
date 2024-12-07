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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownClock extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel label;
    private Timer timer;
    private int countdownTime;
    private boolean isPaused = false;

    public CountdownClock(int seconds) {
        countdownTime = seconds;
        label = new JLabel(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
        label.setFont(new Font("Consolas", Font.BOLD, 20));
        label.setForeground(Color.MAGENTA);
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(label);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    countdownTime--;
                    if (countdownTime < 0) {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "Time is up! You have failed.", "Failure", JOptionPane.ERROR_MESSAGE);
                    } else {
                        label.setText(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
                    }
                }
            }
        });
        timer.start();
    }

    public void resetTimer(int seconds) {
        timer.stop();
        countdownTime = seconds;
        label.setText(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
        isPaused = false;
        timer.start();
    }

    public void pauseTimer() {
        isPaused = true;
    }

    public void resumeTimer() {
        isPaused = false;
    }
}