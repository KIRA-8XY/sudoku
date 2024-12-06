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

    public CountdownClock(int seconds) {
        countdownTime = seconds;
        label = new JLabel(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
        label.setFont(new Font("Consolas", Font.BOLD, 20));
        add(label);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownTime--;
                if (countdownTime < 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time is up! You have failed.", "Failure", JOptionPane.ERROR_MESSAGE);
                } else {
                    label.setText(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
                }
            }
        });
        timer.start();
    }

    public void resetTimer(int seconds) {
        timer.stop();
        countdownTime = seconds;
        label.setText(String.format("%02d:%02d", countdownTime / 60, countdownTime % 60));
        timer.start();
    }
}