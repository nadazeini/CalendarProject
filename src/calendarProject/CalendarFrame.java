package calendarProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalendarFrame extends JFrame{
	private LocalDate firstDay;
	private LocalDate click;
	private EventFrame eventFrame;
	private CalendarFrame cf;
	private DataModel dataModel;
	private JPanel panel4;
	private JButton dateButton;
	public static final String DAY_OF_WEEK = "SMTWTFA";
	
	private static final long serialVersionUID = 1L;

	public CalendarFrame(DataModel dataModel) {
		this.dataModel = dataModel;
		click = LocalDate.now();
		firstDay = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
		dateButton = new JButton();
		panel4 = new JPanel();
		
		JButton todayButton = new JButton("Today");
		JButton createButton = new JButton("CREATE EVENT");
		createButton.setOpaque(true);
		createButton.setBorderPainted(false);
		createButton.setBackground(Color.RED);
		createButton.setForeground(Color.WHITE);
		
		JButton nextMonthButton = new JButton("Next Month");
		JButton previousMonthButton = new JButton("Previous Month");
		JButton previousDayButton = new JButton("Previous Day");
		JButton nextDayButton = new JButton("Next Day");
		
	    final Container contentPane = getContentPane();
	    setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	    
		JPanel panel1 = new JPanel();
		panel1.add(todayButton, BorderLayout.WEST);
		panel1.add(nextDayButton, BorderLayout.EAST);
		panel1.add(previousDayButton, BorderLayout.EAST);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(createButton);
		
		JPanel panel3 = new JPanel();
		dateButton.setText(firstDay.getMonth().toString() + " " + Integer.toString(firstDay.getYear()));
		dateButton.setBorderPainted(false);
		panel3.add(dateButton);
		panel3.add(previousMonthButton);
		panel3.add(nextMonthButton);
	
		panel4.setLayout(new GridLayout(7, 7));
		
		setDate();
		
		ActionListener eventListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton)e.getSource()) == previousMonthButton) {
					firstDay = firstDay.minusMonths(1);
					firstDay = LocalDate.of(firstDay.getYear(), firstDay.getMonth(), 1);
				}
				else if(((JButton)e.getSource()) == nextMonthButton) {
					firstDay = firstDay.plusMonths(1);
					firstDay = LocalDate.of(firstDay.getYear(), firstDay.getMonth(), 1);
				}
				setDate();
			}
			
		};
		previousMonthButton.addActionListener(eventListener);
		nextMonthButton.addActionListener(eventListener);
		
		MouseListener mouseListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				createButton.setBackground(Color.BLUE);
				createButton.setForeground(Color.BLACK);
				mouseReleased(e);
				createEvent(createButton, createButton.getMouseListeners());
				while(createButton.getMouseListeners().length != 0) {
					createButton.removeMouseListener(createButton.getMouseListeners()[0]);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				createButton.setBackground(Color.BLUE);
				createButton.setForeground(Color.BLACK);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				createButton.setBackground(Color.RED);
				createButton.setForeground(Color.WHITE);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				createButton.setBackground(Color.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createButton.setBackground(Color.RED);
			}
			
		};
		createButton.addMouseListener(mouseListener);
		
		todayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firstDay = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
				click = LocalDate.now();
				setDate();
				eventFrame.stateChanged(null);
			}
			
		});
		
		ActionListener dayListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()) == previousDayButton) {
					click = click.minusDays(1);
				}
				else if(((JButton) e.getSource()) == nextDayButton) {
					click = click.plusDays(1);
				}
				firstDay = LocalDate.of(click.getYear(), click.getMonth(), 1);
				setDate();
			}
			
		};
		previousDayButton.addActionListener(dayListener);
		nextDayButton.addActionListener(dayListener);
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		
		setTitle("Calendar");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	}

	public void setDate() {
		panel4.removeAll();
		for(int i = 0; i < DAY_OF_WEEK.length(); i++) {
			JButton a = new JButton(DAY_OF_WEEK.substring(i, i + 1));
			a.setBorderPainted(false);
			panel4.add(a);
		}
		ArrayList<JButton> buttons = new ArrayList<>();
		int firstDayOfMonth = firstDay.getDayOfWeek().getValue();
		if(firstDayOfMonth != 7) {
			for(int i = 0; i < firstDayOfMonth; i++) {
				firstDay = firstDay.minusDays(1);
			}
			for(int i = 0; i < firstDayOfMonth; i++) {
				JButton a = new JButton(Integer.toString(firstDay.getDayOfMonth()));
				a.setForeground(Color.GRAY);
				a.setBorderPainted(false);
				panel4.add(a);
				firstDay = firstDay.plusDays(1);
				buttons.add(a);
			}
		}
		int currentMonthLength = firstDay.getMonth().length(firstDay.isLeapYear());
		for(int i = 0; i < currentMonthLength; i++) {
			JButton a = new JButton(Integer.toString(firstDay.getDayOfMonth()));
			if(firstDay.equals(LocalDate.now())) {
				a.setForeground(Color.RED);
				a.setFont(new Font("ROMAN_BASELINE", Font.ITALIC, 12));
			}
			else {
				a.setBorderPainted(false);
			}
			panel4.add(a);
			firstDay = firstDay.plusDays(1);
			buttons.add(a);
		}
		int leftDays = 0;
		if(firstDayOfMonth != 7)
			leftDays = 49 - currentMonthLength - firstDayOfMonth - 7;
		else
			leftDays = 49 - currentMonthLength - 7;
		for(int i = 0; i < leftDays; i++) {
			JButton a = new JButton(Integer.toString(firstDay.getDayOfMonth()));
			a.setForeground(Color.GRAY);
			a.setBorderPainted(false);
			panel4.add(a);
			firstDay = firstDay.plusDays(1);
			buttons.add(a);
		}
		
		firstDay = firstDay.minusMonths(1);
		dateButton.setText(firstDay.getMonth().toString() + " " + Integer.toString(firstDay.getYear()));
		if(!click.equals(LocalDate.now())) {
			if(firstDay.getYear() == click.getYear() 
			&& firstDay.getMonthValue() == click.getMonthValue()) {
				for(JButton button: buttons) {
					if(button.getForeground().equals(Color.BLACK) 
					&& Integer.parseInt(button.getText()) == click.getDayOfMonth()) {
						button.setForeground(Color.BLUE);
						button.setBorderPainted(true);
						break;
					}
				}
			}
		}
		
		for(JButton button: buttons) {
			button.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(!button.getForeground().equals(Color.GRAY)) {
						for(JButton button: buttons) {
							if(button.getForeground().equals(Color.blue)) {
								button.setForeground(Color.BLACK);
								button.setBorderPainted(false);
								break;
							}
						}
						if(!(firstDay.getYear() == LocalDate.now().getYear() 
						&& firstDay.getMonthValue() == LocalDate.now().getMonthValue() 
						&& Integer.parseInt(button.getText()) == LocalDate.now().getDayOfMonth())) {
							button.setForeground(Color.BLUE);
							button.setBorderPainted(true);
						}	
						click = LocalDate.of(firstDay.getYear(), firstDay.getMonth(), Integer.parseInt(button.getText()));
					}
					else {
						int day = Integer.parseInt(button.getText());
						if(day > 22) {
							firstDay = firstDay.minusMonths(1);
						}
						else {
							firstDay = firstDay.plusMonths(1);
						}
						firstDay = LocalDate.of(firstDay.getYear(), firstDay.getMonth(), 1);
						click = LocalDate.of(firstDay.getYear(), firstDay.getMonth(), Integer.parseInt(button.getText()));
						setDate();
					}
					eventFrame.stateChanged(null);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					button.setBorderPainted(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if(!((firstDay.getYear() == LocalDate.now().getYear() 
					   && firstDay.getMonthValue() == LocalDate.now().getMonthValue() 
					   && Integer.parseInt(button.getText()) == LocalDate.now().getDayOfMonth()) 
				     || ((firstDay.getYear() == click.getYear() 
					   && firstDay.getMonthValue() == click.getMonthValue() 
					   && Integer.parseInt(button.getText()) == click.getDayOfMonth()))))
						button.setBorderPainted(false);
				}
			});
		}
		panel4.revalidate();
		panel4.repaint();
	}
	
	private void createEvent(JButton createButton, MouseListener[] mouseListeners) {
		JFrame frame = new JFrame("Create Event");
		frame.setLocation(550, 0);
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
            public void windowClosing(WindowEvent e)
            {
				for(MouseListener listener: mouseListeners)
					createButton.addMouseListener(listener);
            }
		});
		final Container contentPane2 = frame.getContentPane();
		frame.setLayout(new BoxLayout(contentPane2, BoxLayout.Y_AXIS));
		JPanel namePanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JLabel nameLabel = new JLabel("Add title: ");
		JTextField nameField = new JTextField(10);
		JButton date = new JButton();
		JTextArea errorTextArea = new JTextArea();
		errorTextArea.setForeground(Color.RED);
		errorTextArea.setEditable(false);
		date.setText(click.toString());
		date.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateEventFrame cef = new CreateEventFrame(click, cf, date);
				cef.setCloseReference(cef);
				cef.addWindowFocusListener(new WindowFocusListener() {

					@Override
					public void windowGainedFocus(WindowEvent e) {
						
					}

					@Override
					public void windowLostFocus(WindowEvent e) {
						cef.dispose();
					}
					
				});
			}
			
		});
		Integer hour[] = new Integer[24];
		for(int i = 0; i < hour.length; i++) {
			hour[i] = i;
		}
		JLabel startingTimeLabel = new JLabel("Staring Time:");
		JComboBox<Integer> startingTimeBox = new JComboBox<>(hour);
		JLabel endingTimeLabel = new JLabel("Ending Time:");
		JComboBox<Integer> endingTimeBox = new JComboBox<>(hour);
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int start = (Integer) startingTimeBox.getSelectedItem();
				int end = (Integer) endingTimeBox.getSelectedItem();
				
				String name = nameField.getText();
				if(name.length() == 0) {
					errorTextArea.setText("Please enter a event name.");
					errorPanel.setVisible(true);
				}
				else if(start == end) {
					errorTextArea.setText("Starting time and ending time can't be equal.");
					errorPanel.setVisible(true);
				}
				else if(start > end && end != 0){
					errorTextArea.setText("Starting time can't be greater than ending time if ending time is not zero.");
					errorPanel.setVisible(true);
				}
				else {
					Event newEvent = new Event(name, click.getYear(), click.getMonthValue(), 0
							                 , Integer.toString(click.getDayOfMonth()), start, end);
					if(dataModel.checkConflict(newEvent)) {
						errorTextArea.setText("Time conflict, please reenter the time.");
						errorPanel.setVisible(true);
					}
					else {
						dataModel.addEvent(newEvent);
						errorPanel.setVisible(false);
						for(MouseListener listener: mouseListeners)
							createButton.addMouseListener(listener);
						frame.dispose();
					}
				}
			}
		});
		
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		datePanel.add(date);
		datePanel.add(startingTimeLabel);
		datePanel.add(startingTimeBox);
		datePanel.add(endingTimeLabel);
		datePanel.add(endingTimeBox);
		errorPanel.add(errorTextArea);
		errorPanel.setVisible(false);
		buttonPanel.add(addButton);
		
		frame.setPreferredSize(new Dimension(500, 250));
		frame.add(namePanel);
		frame.add(datePanel);
		frame.add(errorPanel);
		frame.add(buttonPanel);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public void setEventFrame(EventFrame frame) {
		eventFrame = frame;
		eventFrame.stateChanged(null);
	}
	
	public EventFrame getEventFrame() {
		return eventFrame;
	}
	
	public LocalDate getCurrentClick() {
		return click;
	}
	
	public void setCurrentClick(LocalDate click) {
		this.click = click;
	}
	
	public void setFirstDay(LocalDate firstDay) {
		this.firstDay = firstDay;
	}
	
	public void setCalendarReference(CalendarFrame cf) {
		this.cf = cf;
	}
}
