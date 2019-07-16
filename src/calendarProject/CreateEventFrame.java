package calendarProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

public class CreateEventFrame extends JFrame{
	private LocalDate firstDay;
	private LocalDate click;
	private CalendarFrame cf;
	@SuppressWarnings("unused")
	private DataModel dataModel;
	public static final String DAY_OF_WEEK = "SMTWTFA";
	
	private static final long serialVersionUID = 1L;

	public CreateEventFrame(LocalDate click, CalendarFrame cf, DataModel dataModel, JButton createButton, MouseListener[] mouseListeners) {
	
	}
}
