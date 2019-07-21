 package calendarProject;

 import java.util.Collections;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

	/*
	 *defines hashMap as underlying data structure 
	 *to retrieve dates of events and vice versa easily
	 */
	public class DataModel{
		

		HashMap<LocalDate,ArrayList> allEvents= new HashMap<>();
		private ArrayList<ChangeListener> listeners;

		public DataModel (HashMap allEvents) {
			this.allEvents=allEvents;
			listeners = new ArrayList<ChangeListener>();
		}
		/**
		 * adds events to hashMap allEvents
		 * @param date
		 * @param events
		 */
		public void addEvents(LocalDate date,ArrayList events) {
			allEvents.put(date, events)
	;		
			
		}
		/**
		 * removes event from hashMap
		 * @param dateOfEvent
		 * @param eventToRemove
		 */
		public void removeEvent (LocalDate dateOfEvent,Event eventToRemove) {
		allEvents.remove(dateOfEvent, eventToRemove);

	}
	
	/**
	 * 
	 * @return hashMap
	 */
	public HashMap getHashMap() {
		return allEvents;
	}
	
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	}
	
	
	
	/*private ArrayList<Event> events;
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
	
	public String format(ArrayList<Event> events, EventFormatter formatter, LocalDate currentDay) {
		String result = "";
		result += formatter.formatHeader(currentDay);
		if(events.size() == 0)
			result += "\n";
		else {
			for(Event event: events) {
				result += formatter.formatEvent(event);
			}
			result += formatter.formatFooter();
		}
		return result;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean checkConflict(Event event) {
		boolean hasConflict = false;
		LocalDate oneTimeEvent = LocalDate.of(event.getYear(), event.getStartingMonth(), event.getDays().get(0));
		for(Event e: events) {
			if(e.getYear() == event.getYear()) {
				if(e.getEndingMonth() == 0) {
					if(e.getStartingMonth() == event.getStartingMonth() && (int) e.getDays().get(0) == (int) event.getDays().get(0)) {
						if(checkTime(e, event)) {
							hasConflict = true;
							break;
						}
					}
				}
				else {
					if((event.getStartingMonth() >= e.getStartingMonth() && event.getStartingMonth() <= e.getEndingMonth()) 
					 && e.getDays().contains(oneTimeEvent.getDayOfWeek())) {
						if(checkTime(e, event)) {
							hasConflict = true;
							break;
						}
					}
				}
			}
		}
		return hasConflict;
	}
	
	private boolean checkTime(Event e, Event event) {
		if(e.getEndingTime() == 0 && event.getEndingTime() == 0) {
			return true;
		}
		else if(e.getEndingTime() == 0 && event.getEndingTime() != 0) {
			if(e.getStartingTime() >= event.getEndingTime())
				return false;
			else
				return true;
		}
		else if(e.getEndingTime() != 0 && event.getEndingTime() == 0) {
			if(e.getEndingTime() <= event.getStartingTime())
				return false;
			else
				return true;
		}
		else {
			if(event.getEndingTime() <= e.getStartingTime() || event.getStartingTime() >= e.getEndingTime())
				return false;
			else 
				return true;
		}
	}
}
<<<<<<< HEAD
*/

