import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Task7 extends JFrame {
    private Canvas canvas;
    private JButton lineButton, rectangleButton, ellipseButton, clearButton;
    private List<Shape> shapes;
    private Shape currentShape;
    private Color canvasColor;
    private Point startDrag, endDrag;

    public Task7() {
        setTitle("Task 7");
        setSize(700, 400);
        setLayout(new BorderLayout());
        canvas = new Canvas();
        canvas.addMouseListener(new CanvasMouseListener());
        canvas.addMouseMotionListener(new CanvasMouseMotionListener());
        shapes = new ArrayList<>();
        canvasColor = Color.WHITE;
        lineButton = createStyledButton("Line");
        rectangleButton = createStyledButton("Rectangle");
        ellipseButton = createStyledButton("Ellipse");
        clearButton = createStyledButton("Clear");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(lineButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(ellipseButton);
        buttonPanel.add(clearButton);
        add(canvas, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        lineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentShape = new Line2D.Double();
            }
        });
        rectangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentShape = new Rectangle2D.Double();
            }
        });
        ellipseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentShape = new Ellipse2D.Double();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                canvas.repaint();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JButton createStyledButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        return button;
    }
    private class Canvas extends JPanel
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(canvasColor);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.BLACK);
            for (Shape shape : shapes) {
                g2d.draw(shape);
            }
            if (currentShape != null) {
                g2d.draw(currentShape);
            }
        }
    }
    private class CanvasMouseListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e) {
            startDrag = new Point(e.getX(), e.getY());
            endDrag = startDrag;

            if (currentShape != null) {
                currentShape = makeShape(startDrag, endDrag);
            }
        }

        public void mouseReleased(MouseEvent e) {
            endDrag = new Point(e.getX(), e.getY());

            if (currentShape != null) {
                shapes.add(currentShape);
                currentShape = null;
                canvas.repaint();
            }
        }
    }

    private class CanvasMouseMotionListener extends MouseAdapter {
        public void mouseDragged(MouseEvent e) {
            endDrag = new Point(e.getX(), e.getY());

            if (currentShape != null)
            {
                currentShape = makeShape(startDrag, endDrag);
                canvas.repaint();
            }
        }
    }
    private Shape makeShape(Point start, Point end)
    {
        if (currentShape instanceof Line2D) {
            return new Line2D.Double(start, end);
        } else if (currentShape instanceof Rectangle2D) {
            return makeRectangle(start, end);
        } else if (currentShape instanceof Ellipse2D) {
            return makeEllipse(start, end);
        } else {
            return currentShape;
        }
    }

    private Rectangle2D makeRectangle(Point start, Point end)
    {
        return new Rectangle2D.Double(
                Math.min(start.getX(), end.getX()),
                Math.min(start.getY(), end.getY()),
                Math.abs(start.getX() - end.getX()),
                Math.abs(start.getY() - end.getY())
        );
    }

    private Ellipse2D makeEllipse(Point start, Point end)
    {
        return new Ellipse2D.Double(
                Math.min(start.getX(), end.getX()),
                Math.min(start.getY(), end.getY()),
                Math.abs(start.getX() - end.getX()),
                Math.abs(start.getY() - end.getY())
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task7());
    }
}
