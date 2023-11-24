import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task6 extends JFrame implements ActionListener {
    private JPanel colorPanel;
    private JButton colorButton;

    public Task6() {
        setTitle("Task 6");
        setSize(300, 150);
        setLayout(new BorderLayout());
        colorPanel = new JPanel();
        colorButton = new JButton("Choose Color");
        add(colorPanel, BorderLayout.CENTER);
        add(colorButton, BorderLayout.SOUTH);
        colorButton.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color selectedColor = JColorChooser.showDialog(this, "Choose Color", colorPanel.getBackground());
        if (selectedColor != null) {
            colorPanel.setBackground(selectedColor);
        }
    }

    public static void main(String[] args) {
        new Task6();
    }
}