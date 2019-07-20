package calendarProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateEventFrame extends JDialog {
    private boolean isAdd = false;
    private JTextField eventName = new JTextField();
    private JTextField year = new JTextField();
    private JTextField startMonth = new JTextField();
    private JTextField endMonth = new JTextField();
    private JTextField day = new JTextField();
    private JTextField startTime = new JTextField();
    private JTextField endTime = new JTextField();

    public CreateEventFrame(JFrame parent) {
        setLocationRelativeTo(parent);
        setTitle("Create new event");
        setSize(400, 480);

        JLabel eventNameLabel = new JLabel("Event Name:");
        JLabel yearLabel = new JLabel("Year:");
        JLabel startMonthLabel = new JLabel("Start Month:");
        JLabel endMonthLabel = new JLabel("End Month:");
        JLabel dayLabel = new JLabel("Day:");
        JLabel startTimeLabel = new JLabel("Start Time:");
        JLabel endTimeLabel = new JLabel("End Time:");

        setLayout(null);
        eventNameLabel.setBounds(50, 40, 100, 25);
        eventName.setBounds(180, 42, 150, 25);
        this.add(eventNameLabel);
        this.add(eventName);

        yearLabel.setBounds(50, 80, 100, 25);
        year.setBounds(180, 82, 150, 25);
        year.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (year.getText().length() >= 4 || e.getKeyChar() > '9' || e.getKeyChar() < '0') {
                    e.consume();
                }
            }
        });
        this.add(yearLabel);
        this.add(year);

        startMonthLabel.setBounds(50, 120, 100, 25);
        startMonth.setBounds(180, 122, 150, 25);
        this.add(startMonthLabel);
        this.add(startMonth);

        endMonthLabel.setBounds(50, 160, 100, 25);
        endMonth.setBounds(180, 162, 150, 25);
        this.add(endMonthLabel);
        this.add(endMonth);

        dayLabel.setBounds(50, 200, 100, 25);
        day.setBounds(180, 202, 150, 25);
        this.add(dayLabel);
        this.add(day);

        startTimeLabel.setBounds(50, 240, 100, 25);
        startTime.setBounds(180, 242, 150, 25);
        this.add(startTimeLabel);
        this.add(startTime);

        endTimeLabel.setBounds(50, 280, 100, 25);
        endTime.setBounds(180, 282, 150, 25);
        this.add(endTimeLabel);
        this.add(endTime);

        JDialog crateEventFrame = this;
        JButton sure = new JButton("Confirm");
        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAdd = true;
                crateEventFrame.setModal(false);
                crateEventFrame.setVisible(false);
            }
        });
        sure.setBounds(150, 350, 100, 30);
        this.add(sure);
        setModal(true);
        setVisible(true);
    }

    public boolean isAdd() {
        return isAdd;
    }

    public String getEventName() {
        return eventName.getText();
    }

    public String getYear() {
        return year.getText();
    }

    public String getDay() {
        return day.getText();
    }

    public String getEndMonth() {
        return endMonth.getText();
    }

    public String getStartMonth() {
        return startMonth.getText();
    }

    public String getEndTime() {
        return endTime.getText();
    }

    public String getStartTime() {
        return startTime.getText();
    }
}
