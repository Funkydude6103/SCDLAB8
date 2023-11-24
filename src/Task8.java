import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task8 extends JFrame implements ActionListener {
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField, passwordField;
    private JButton loginButton;

    public Task8() {
        setTitle("Task 8");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 5, 10));
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("123")) {
            JOptionPane.showMessageDialog(this, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task8());
    }
}
