package calendarProject;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DataModel{
	private ArrayList<Event> events;
	private ArrayList<ChangeListener> listeners;
	
	public DataModel() {
		events = new ArrayList<Event>();
		listeners = new ArrayList<ChangeListener>();
	}
	
	public void addEvent(Event event) {
		events.add(event);
		Collections.sort(events);
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener listener: listeners) {
			listener.stateChanged(e);
		}
	}
	
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Event> getEvents(){
		return (ArrayList<Event>) events.clone();
	}
}
