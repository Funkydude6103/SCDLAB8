import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Task5 extends JFrame implements ActionListener {
    private JLabel imageLabel;
    private JButton prevButton, nextButton, openButton;
    private File[] imageFiles;
    private int currentIndex;

    public Task5() {
        setTitle("Task 5");
        setSize(400, 400);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        prevButton = new JButton("Prev");
        nextButton = new JButton("Next");
        openButton = new JButton("Open Image");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(openButton);
        add(imageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        prevButton.addActionListener(this);
        nextButton.addActionListener(this);
        openButton.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        if (buttonText.equals("Prev")) {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = imageFiles.length - 1;
            }
            displayImage(imageFiles[currentIndex]);
        } else if (buttonText.equals("Next")) {
            currentIndex++;
            if (currentIndex >= imageFiles.length) {
                currentIndex = 0;
            }
            displayImage(imageFiles[currentIndex]);
        } else if (buttonText.equals("Open Image")) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);

            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    File directory = selectedFile.getParentFile();
                    imageFiles = directory.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"));
                    if (imageFiles != null && imageFiles.length > 0) {
                        currentIndex = 0;
                        displayImage(imageFiles[currentIndex]);
                    }
                }
            }
        }
    }


    private void displayImage(File file) {
        try {
            BufferedImage originalImage = ImageIO.read(file);
            int labelWidth = imageLabel.getWidth();
            int labelHeight = imageLabel.getHeight();

            if (labelWidth > 0 && labelHeight > 0) {
                Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(imageIcon);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Task5();
    }
}
