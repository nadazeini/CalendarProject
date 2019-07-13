package calendarProject;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EventFrame extends JFrame{
	
	public EventFrame(CalendarFrame calendarFrame) {
	    final Container contentPane = getContentPane();
	    setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	    
	    JButton dayButton = new JButton("Day");
	    JButton weekButton = new JButton("Week");
	    JButton monthButton = new JButton("Month");
	    JButton agendaButton = new JButton("Agenda");
	    final JButton fileButton = new JButton("From File"); //why final?
	fileButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				fileButton.setBorderPainted(false);
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
		
	    fileButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (Desktop.isDesktopSupported()) {
	    		    try {
	    		    	if(System.getProperty("os.name").toUpperCase().contains("MAC"))
						Desktop.getDesktop().open(new File("/users/"));
	    		    	else
	    		    		Desktop.getDesktop().open(new File("C:\\"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
	    	}
	    });
	    JPanel panel1 = new JPanel();
	    panel1.add(dayButton);
	    panel1.add(weekButton);
	    panel1.add(monthButton);
	    panel1.add(agendaButton);
	    
	    JPanel panel2 = new JPanel();
	    panel2.add(fileButton);
	    
	    final int row = 30;
	    final int column = 50;
	    final JTextArea textArea = new JTextArea(row, column);
	    
	    JPanel panel3 = new JPanel();
	    panel3.add(textArea);
	    
	    add(panel1);
	    add(panel2);
	    add(panel3);
	    
	    setLocation(550, 0);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	}
}
