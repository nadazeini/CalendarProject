package calendarProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


// The main time interface, we must share the events and partial data of the two interfaces through one module
public class EventFrame extends JFrame implements ChangeListener {
	
	private CalendarFrame calendarFrame;
	// private static final long serialVersionUID = 1L;
	private JPanel panel3 = new JPanel();
	private final int row = 300;
	private final int column = 500;

	private static HashMap<LocalDate, Event> hashMap = new HashMap<>();
	private static DataModel dataModel = new DataModel(hashMap);

	public EventFrame() {
	
	
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
						try {
							processFile(scanner);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
				//	showByAgenda();
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

	//	showByAgenda();
		add(panel1);
		add(panel2);
		add(panel3);

		setLocation(0, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * method to get all dates between 2 dates returns ArrayList of LocalDates
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static ArrayList<LocalDate> datesBetween(LocalDate startDate, LocalDate endDate) throws ParseException {
		
		ArrayList<LocalDate> listDatesBetween = new ArrayList<>();

		while (!startDate.isAfter(endDate)) {
			listDatesBetween.add(startDate);
			startDate = startDate.plusDays(1);
		}
		return listDatesBetween;

	}
	/**
	 * gets last day of given month and year
	 * 
	 */
	public static int monthLength(String monthYear) throws ParseException{
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("M/yyyy");   
	YearMonth ym = YearMonth.parse(monthYear,pattern);
		LocalDate date = ym.atEndOfMonth();
		return date.lengthOfMonth();
	}
	/**
	 * get all dates with events happening regularly
	 * 
	 * @param day: occurrence of events such as "MTWRFSSu"
	 * @param totalDates
	 * @return regularDates ArrayList of Dates with regular events
	 */
	public static ArrayList<LocalDate> datesWithEvents(String day, ArrayList<LocalDate> totalDates) {

		ArrayList<LocalDate> regularDates = new ArrayList<LocalDate>();
		for (int i = 0; i < totalDates.size(); i++) {
			if (day.contains("M")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("monday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("T")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("tuesday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("W")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("wednesday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("H")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("thursday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("F")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("friday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("A")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("saturday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("S")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("sunday")) {
					regularDates.add(totalDates.get(i));
				}
			}
		}

		return regularDates;
	}
	/**
	 * goes through file
	 * 
	 * @param fileScanner
	 * @throws Exception 
	 */
	public void processFile(Scanner fileScanner) throws Exception {
		while (fileScanner.hasNextLine()) {
			String token[] = fileScanner.nextLine().split(";");
			
			String eventName = token[0];
			int year = Integer.parseInt(token[1]);
			int startingMonth = Integer.parseInt(token[2]);
			int endingMonth = Integer.parseInt(token[3]);
			String occursOn = token[4];
			
			String monthYear = token[3]+"/"+token[1];
			LocalTime startTime = LocalTime.parse(token[5]);
			LocalTime endTime = LocalTime.parse(token[6]);
			TimeInterval timeInterval = new TimeInterval(startTime,endTime);
			LocalDate startDate = LocalDate.of(year, startingMonth, 1);
			LocalDate endDate = LocalDate.of(year, endingMonth, monthLength(monthYear));
			
			
			ArrayList<LocalDate> datesWithEventsFile =  datesWithEvents(occursOn, datesBetween(startDate,endDate));
			for (int i = 0; i < datesWithEventsFile.size(); i++) {
				Event event = new Event(eventName, timeInterval);
				if(dataModel.allEvents.containsKey(datesWithEventsFile.get(i))){
					
					ArrayList<Event> eventsOnDate = dataModel.allEvents.get(datesWithEventsFile.get(i));
					eventsOnDate.add(event);
					
				}
				else {
					ArrayList<Event> newEventList = new ArrayList<Event>();
					newEventList.add(event);
					dataModel.allEvents.put(datesWithEventsFile.get(i), newEventList);
			
				}
			}
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
		String events[] = new String[dataModel.allEvents.size()];
		for (int i = 0; i < dataModel.allEvents.size(); i++) {
			events[i] = "Days: " + dataModel.allEvents.get(key) + "; StartingMonth: "
					+ dataModel.allEvents.get(i).getStartingMonth() + "; EndingMonth: "
					+ dataModel.allEvents.get(i).getEndingMonth() + "; Year: "
					+ dataModel.allEvents.get(i).getYear() + "; Name: " + dataModel.allEvents.get(i).getEventName();
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
		ArrayList<Event> events = dataModel.allEvents;
		ArrayList<Event> eventsOnCurrentDay = new ArrayList<>();
		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			if (current.getYear() == event.getYear()
					&& (current.getMonthValue() >= event.getStartingMonth()
							&& current.getMonthValue() < event.getEndingMonth())
					&& event.getDays().contains(current.getDayOfWeek().getValue()))
				eventsOnCurrentDay.add(event);
			showByAgenda();
		}
	}
}
