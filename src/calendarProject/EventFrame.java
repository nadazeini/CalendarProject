package calendarProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


// The main time interface, we must share the events and partial data of the two interfaces through one module
public class EventFrame extends JFrame implements ChangeListener {
    private DataModel dataModel;
    private CalendarFrame calendarFrame;
    private static final long serialVersionUID = 1L;
    private JPanel panel3 = new JPanel();
    private final int row = 300;
    private final int column = 500;

    public EventFrame(DataModel dataModel) {
        this.dataModel = dataModel;
        final Container contentPane = getContentPane();
        setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JButton dayButton = new JButton("Day");
        JButton weekButton = new JButton("Week");
        JButton monthButton = new JButton("Month");
        JButton agendaButton = new JButton("Agenda");
        JButton fileButton = new JButton("From File");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(new JFrame());
                fileDialog.setVisible(true);
                File[] files = fileDialog.getFiles();
                Scanner scanner = null;
                if (files.length > 0) {
                    try {
                        scanner = new Scanner(files[0]);
                        processFile(scanner);
                    } catch (FileNotFoundException exception) {
                        exception.printStackTrace();
                    } finally {
                        if (scanner != null) {
                            scanner.close();
                        }
                    }
                    fileButton.setBorderPainted(false);
                    fileButton.removeActionListener(fileButton.getActionListeners()[0]);
                }
            }
        };
        fileButton.addActionListener(listener);

        ActionListener filterListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == dayButton) {
                    showByDay();
                } else if (e.getSource() == weekButton) {
                    showByWeek();
                } else if (e.getSource() == monthButton) {
                    showByMouth();
                } else if (e.getSource() == agendaButton) {
                    showByAgenda();
                }
            }
        };
        dayButton.addActionListener(filterListener);
        weekButton.addActionListener(filterListener);
        monthButton.addActionListener(filterListener);
        agendaButton.addActionListener(filterListener);

        JPanel panel1 = new JPanel();
        panel1.add(dayButton);
        panel1.add(weekButton);
        panel1.add(monthButton);
        panel1.add(agendaButton);

        JPanel panel2 = new JPanel();
        panel2.add(fileButton);

        showByAgenda();
        add(panel1);
        add(panel2);
        add(panel3);

        setLocation(0, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void processFile(Scanner fileScanner) {
        while (fileScanner.hasNextLine()) {
            String events[] = fileScanner.nextLine().split(";");
            dataModel.addEvent(new Event(events[0], Integer.parseInt(events[1]), Integer.parseInt(events[2]), Integer.parseInt(events[3])
                    , events[4], Integer.parseInt(events[5]), Integer.parseInt(events[6])));
        }
    }

    private void showByDay() {

    }

    private void showByWeek() {

    }

    private void showByMouth() {
        panel3.removeAll();
        repaint();
    }

    private void showByAgenda() {
        panel3.removeAll();
        String events[] = new String[dataModel.getEvents().size()];
        for (int i = 0; i < dataModel.getEvents().size(); i++) {
            events[i] = "Days: "
                    + dataModel.getEvents().get(i).getDays().toString()
                    + "; StartingMonth: "
                    + dataModel.getEvents().get(i).getStartingMonth()
                    + "; EndingMonth: "
                    + dataModel.getEvents().get(i).getEndingMonth()
                    + "; Year: "
                    + dataModel.getEvents().get(i).getYear()
                    + "; Name: "
                    + dataModel.getEvents().get(i).getEventName();
        }
        JList eventList = new JList<>(events);
        JScrollPane eventScrollPane = new JScrollPane(eventList);
        eventScrollPane.setPreferredSize(new Dimension(column, row));
        panel3.add(eventScrollPane);
        panel3.revalidate();
        repaint();
    }

    public void setCalendarFrame(CalendarFrame frame) {
        this.calendarFrame = frame;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        LocalDate current = calendarFrame.getCurrentClick();
        ArrayList<Event> events = dataModel.getEvents();
        ArrayList<Event> eventsOnCurrentDay = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (current.getYear() == event.getYear()
                    && (current.getMonthValue() >= event.getStartingMonth() && current.getMonthValue() < event.getEndingMonth())
                    && event.getDays().contains(current.getDayOfWeek().getValue()))
                eventsOnCurrentDay.add(event);
            showByAgenda();
        }
    }
}
