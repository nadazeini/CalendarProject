package calendarProject;

import java.util.ArrayList;
import java.util.Collections;

public class DataModel{
	private ArrayList<Event> events;
	
	public DataModel() {
		events = new ArrayList<Event>();
		Collections.sort(events);
	}
}
