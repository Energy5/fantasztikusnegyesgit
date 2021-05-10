package hu.fantasztik.szofttech.state;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerLabel extends JPanel implements ActionListener {

    private JLabel timeLabel;
    public int time = 0;
    Timer timer;

    public TimerLabel() {
        timeLabel = new JLabel("00:00");
        timeLabel.setFont(new Font("Monaco", Font.PLAIN, 35));
        add(timeLabel);

        timer = new Timer(1000, this);
        timer.setInitialDelay(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        timeLabel.setText(secondsToString());
    }

    private String secondsToString() {
        return String.format("%02d:%02d", time / 60, time % 60);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void setTimeToZero() {
        this.time = 0;
    }

    public void setTimeLabelToZeroZero() {
        timeLabel.setText("00:00");
    }
}