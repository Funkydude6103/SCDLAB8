import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager.*;

public class Task9 extends JFrame implements ActionListener {
    private JProgressBar progressBar;
    private JButton startButton, resetButton;
    private Timer timer;
    private int progress;

    public Task9() {
        setTitle("Progress Bar");
        setSize(300, 75);
        setLayout(new BorderLayout());
        UIManager.put("ProgressBar.selectionBackground", Color.GREEN);
        progressBar = new JProgressBar(0, 100);
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        startButton.setBackground(new Color(0, 128, 0)); // Green
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(255, 51, 51)); // Red
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        add(progressBar, BorderLayout.CENTER);
        add(startButton, BorderLayout.WEST);
        add(resetButton, BorderLayout.EAST);
        startButton.addActionListener(this);
        resetButton.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        if (buttonText.equals("Start")) {
            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    progress+=1;
                    progressBar.setValue(progress);
                    if (progress == 100) {
                        timer.stop();
                    }
                }
            });
            timer.start();
        } else if (buttonText.equals("Reset")) {
            progress = 0;
            progressBar.setValue(progress);
            if (timer != null) {
                timer.stop();
            }
        }
    }

    public static void main(String[] args) {
        new Task9();
    }
}
