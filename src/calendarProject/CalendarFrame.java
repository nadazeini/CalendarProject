package calendarProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalendarFrame extends JFrame{
	private static LocalDate firstDay;
	private static LocalDate click;
	private EventFrame eventFrame;
	private DataModel dataModel;
	public static final String DAY_OF_WEEK = "SMTWTFA";
	
	private static final long serialVersionUID = 1L;

	public CalendarFrame(DataModel dataModel) {
		this.dataModel = dataModel;
		click = LocalDate.now();
		firstDay = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
		
		JButton todayButton = new JButton("Today");
		JButton createButton = new JButton("CREATE EVENT");
		createButton.setOpaque(true);
		createButton.setBorderPainted(false);
		createButton.setBackground(Color.RED);
		createButton.setForeground(Color.WHITE);
		JButton dateButton = new JButton();
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
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(7, 7));
		
		setDate(panel4, dateButton);
		
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
				setDate(panel4, dateButton);
			}
			
		};
		previousMonthButton.addActionListener(eventListener);
		nextMonthButton.addActionListener(eventListener);
		
		createButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				createButton.setBackground(Color.BLUE);
				createButton.setForeground(Color.BLACK);
				mouseReleased(e);
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		todayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firstDay = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
				click = LocalDate.now();
				setDate(panel4, dateButton);
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
				setDate(panel4, dateButton);
			}
			
		};
		previousDayButton.addActionListener(dayListener);
		nextDayButton.addActionListener(dayListener);
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	}

	public void setDate(JPanel panel4, JButton dateButton) {
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
			if(firstDay.equals(LocalDate.now()) {
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
					&& !button.getForeground().equals(Color.RED)
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
						setDate(panel4, dateButton);
					}
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
	
	public void setEventFrame(EventFrame frame) {
		eventFrame = frame;
	}
	
	public LocalDate getCurrentClick() {
		return click;
	}
}
