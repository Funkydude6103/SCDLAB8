import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Task 1");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel label = new JLabel("0");
        label.setFont(new Font("Arial", Font.BOLD, 36));
        JButton button = new JButton("Click Me!");
        panel.add(label);
        panel.add(button);
        frame.add(panel);
        button.addActionListener(new ActionListener() {
            int counter = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                label.setText("  " + counter);
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
