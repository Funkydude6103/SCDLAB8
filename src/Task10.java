import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task10 extends JFrame implements ActionListener {
    private JTable calendarTable;
    private DefaultTableModel tableModel;
    private JButton addButton, deleteButton, editButton, prevButton, nextButton;
    private List<Event> events;
    private YearMonth currentYearMonth;

    public Task10() {
        setTitle("Monthly Calendar");
        setSize(800, 600);
        setLayout(new BorderLayout());
        calendarTable = new JTable();
        addButton = new JButton("Add Event");
        deleteButton = new JButton("Delete Event");
        editButton = new JButton("Edit Event");
        prevButton = new JButton("<<");
        nextButton = new JButton(">>");
        add(new JScrollPane(calendarTable), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);
        JPanel navPanel = new JPanel(new FlowLayout());
        navPanel.add(prevButton);
        navPanel.add(nextButton);
        add(navPanel, BorderLayout.NORTH);
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        editButton.addActionListener(this);
        prevButton.addActionListener(this);
        nextButton.addActionListener(this);
        events = new ArrayList<>();
        currentYearMonth = YearMonth.now();
        String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        tableModel = new DefaultTableModel(null, daysOfWeek);
        calendarTable.setModel(tableModel);
        calendarTable.setRowHeight(50);
        calendarTable.getTableHeader().setReorderingAllowed(false);
        calendarTable.getTableHeader().setResizingAllowed(false);
        calendarTable.setCellSelectionEnabled(true);
        calendarTable.setColumnSelectionAllowed(true);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        updateCalendar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        if (buttonText.equals("Add Event")) {
            LocalDate selectedDate = getSelectedDate();
            if (selectedDate != null) {
                String eventName = JOptionPane.showInputDialog(this, "Enter event name:");
                if (eventName != null && !eventName.isEmpty()) {
                    events.add(new Event(selectedDate, eventName));
                    updateCalendar();
                }
            }
        } else if (buttonText.equals("Delete Event")) {
            int selectedRow = calendarTable.getSelectedRow();
            int selectedColumn = calendarTable.getSelectedColumn();
            if (selectedRow != -1 && selectedColumn != -1) {
                LocalDate selectedDate = currentYearMonth.atDay(1).plusDays(selectedRow * 7 + selectedColumn);
                events.removeIf(event -> event.getDate().equals(selectedDate));
                updateCalendar();
            }
        } else if (buttonText.equals("Edit Event")) {
            int selectedRow = calendarTable.getSelectedRow();
            int selectedColumn = calendarTable.getSelectedColumn();
            if (selectedRow != -1 && selectedColumn != -1) {
                LocalDate selectedDate = currentYearMonth.atDay(1).plusDays(selectedRow * 7 + selectedColumn);
                Event selectedEvent = events.stream().filter(event -> event.getDate().equals(selectedDate)).findFirst().orElse(null);
                if (selectedEvent != null) {
                    String eventName = JOptionPane.showInputDialog(this, "Enter event name:", selectedEvent.getName());
                    if (eventName != null && !eventName.isEmpty()) {
                        selectedEvent.setName(eventName);
                        updateCalendar();
                    }
                }
            }
        } else if (buttonText.equals("<<")) {
            currentYearMonth = currentYearMonth.minusMonths(1);
            updateCalendar();
        } else if (buttonText.equals(">>")) {
            currentYearMonth = currentYearMonth.plusMonths(1);
            updateCalendar();
        }
    }

    private void updateCalendar() {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(7);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM");
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int daysInMonth = currentYearMonth.lengthOfMonth();
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue() % 7;
        int row = 0;
        int column = dayOfWeek;

        for (int i = 1; i <= daysInMonth; i++) {
            if (row == tableModel.getRowCount()) {
                tableModel.addRow(new Object[7]);
            }
            LocalDate date = firstDayOfMonth.plusDays(i - 1);
            String cellValue = date.format(dateFormatter);

            for (Event event : events) {
                if (event.getDate().equals(date)) {
                    cellValue += "\n" + event.getName();
                }
            }

            tableModel.setValueAt(cellValue, row, column);
            column = (column + 1) % 7;
            if (column == 0) {
                row++;
            }
        }
        String monthYear = currentYearMonth.format(monthFormatter) + " " + currentYearMonth.format(yearFormatter);
        setTitle(monthYear);
        calendarTable.repaint();
    }


    private LocalDate getSelectedDate() {
        int selectedRow = calendarTable.getSelectedRow();
        int selectedColumn = calendarTable.getSelectedColumn();
        if (selectedRow != -1 && selectedColumn != -1) {
            return currentYearMonth.atDay(1).plusDays(selectedRow * 7 + selectedColumn);
        }
        return null;
    }

    private static class Event {
        private LocalDate date;
        private String name;

        public Event(LocalDate date, String name) {
            this.date = date;
            this.name = name;
        }

        public LocalDate getDate() {
            return date;
        }
        public String getName() {
            return name;
        }

        public void setName(String eventName) {
            this.name=eventName;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task10());
    }
}