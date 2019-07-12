package calendarProject;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class CalendarTester {
	public static void main(String[] args) {
		CalendarFrame calendarFrame = new CalendarFrame();
	    
		JFrame frame2 = new JFrame();
	    final Container contentPane2 = frame2.getContentPane();
	    frame2.setLayout(new BoxLayout(contentPane2, BoxLayout.Y_AXIS));
	}
}
