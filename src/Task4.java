import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task4 extends JFrame implements ActionListener {
    private JTextField inputField, outputField;
    private JComboBox<String> sourceUnitBox, targetUnitBox;
    private JButton convertButton;

    public Task4() {
        setTitle("Task 4");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputField = new JTextField();
        outputField = new JTextField();
        outputField.setEditable(false);
        sourceUnitBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        targetUnitBox = new JComboBox<>(new String[]{"Fahrenheit", "Celsius"});
        convertButton = new JButton("Convert");
        mainPanel.add(new JLabel("Input:"));
        mainPanel.add(inputField);
        mainPanel.add(new JLabel("Source Unit:"));
        mainPanel.add(sourceUnitBox);
        mainPanel.add(new JLabel("Output:"));
        mainPanel.add(outputField);
        mainPanel.add(new JLabel("Target Unit:"));
        mainPanel.add(targetUnitBox);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.add(convertButton);
        convertButton.addActionListener(this);
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = inputField.getText();
        String sourceUnit = (String) sourceUnitBox.getSelectedItem();
        String targetUnit = (String) targetUnitBox.getSelectedItem();

        if (!inputText.isEmpty()) {
            double input = Double.parseDouble(inputText);
            double output;

            if (sourceUnit.equals("Celsius") && targetUnit.equals("Fahrenheit")) {
                output = (input * 1.8) + 32;
            } else if (sourceUnit.equals("Fahrenheit") && targetUnit.equals("Celsius")) {
                output = (input - 32) / 1.8;
            } else {
                output = input;
            }

            outputField.setText(Double.toString(output));
        }
    }

    public static void main(String[] args) {
        new Task4();
    }
}
