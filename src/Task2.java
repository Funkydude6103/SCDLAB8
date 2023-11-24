import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Task2 extends JFrame implements ActionListener {
    private JTextField inputField, outputField, out;
    private double num1, num2, result;
    private String operator;

    public Task2() {
        setTitle("Task 2");
        setSize(300, 400);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        outputField = new JTextField();
        out = new JTextField("Answer: ");
        out.setEditable(false);
        outputField.setEditable(false);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "=", "+"
        };

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(out, BorderLayout.WEST);
        topPanel.add(outputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(inputField, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        if (buttonText.matches("[0-9]")) {
            inputField.setText(inputField.getText() + buttonText);
        } else if (buttonText.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(inputField.getText());
            operator = buttonText;
            inputField.setText("");
        } else if (buttonText.equals("=")) {
            num2 = Double.parseDouble(inputField.getText());

            switch (operator) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "*" -> result = num1 * num2;
                case "/" -> result = num1 / num2;
            }

            outputField.setText(Double.toString(result));
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        new Task2();
    }
}
