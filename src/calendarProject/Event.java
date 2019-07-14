package calendarProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Event implements Comparable<Event>{
	public static final Map<String, Integer> daysAndWeek = new HashMap<>();
	private String eventName;
	private int year;
	private int startingMonth;
	private int endingMonth;
	private ArrayList<Integer> days;
	private int startingTime;
	private int endingTime;
	
	public Event(String eventName, int year, int startingMonth, int endingMonth,
				 String dayOfWeek, int startingTime, int endingTime) {
		daysAndWeek.put("M", 1);
		daysAndWeek.put("T", 2);
		daysAndWeek.put("W", 3);
		daysAndWeek.put("R", 4);
		daysAndWeek.put("F", 5);
		daysAndWeek.put("A", 6);
		daysAndWeek.put("S", 7);
		this.eventName = eventName;
		this.year = year;
		this.startingMonth = startingMonth;
		this.endingMonth = endingMonth;
		for(int i = 0; i < dayOfWeek.length(); i++) {
			days.add(daysAndWeek.get(dayOfWeek.substring(i, i + 1)));
		}
		this.startingTime = startingTime;
		this.endingTime = endingTime;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getStartingMonth() {
		return startingMonth;
	}
	
	public int getEndingMonth() {
		return endingMonth;
	}
	
	public ArrayList<Integer> days() {
		return days;
	}
	
	public int startingTime() {
		return startingTime;
	}
	
	public int endingTime() {
		return endingTime;
	}

	@Override
	public int compareTo(Event event) {
		int compareYear = Integer.compare(year, event.year);
		if(compareYear < 0)
			return -1;
		else if(compareYear > 0)
			return 1;
		else {
			int compareStartingMonth = Integer.compare(startingMonth, event.startingMonth);
			if(compareStartingMonth < 0)
				return -1;
			else if(compareStartingMonth > 0)
				return 1;
			else {
				int compareEndingMonth = Integer.compare(endingMonth, event.endingMonth);
				if(compareEndingMonth < 0)
					return -1;
				else if(compareStartingMonth > 0)
					return 1;
				else {
					int compareDayOfWeek = Integer.compare(days.get(0), days.get(0));
					if(compareDayOfWeek < 0)
						return -1;
					else if(compareDayOfWeek > 0)
						return 1;
					else {
						int compareStartingTime = Integer.compare(startingTime, event.startingTime);
						if(compareStartingTime < 0)
							return -1;
						else if(compareStartingTime > 0)
							return 1;
						else {
							int compareEndingTime = Integer.compare(endingTime, event.endingTime);
							if(compareEndingTime < 0)
								return -1;
							else if(compareEndingTime > 0)
								return 1;
							else
								return 0;
						}
					}
				}
			}
		}
	}
	
	public boolean conflict(Event e) {
		return true;
	} 
}
