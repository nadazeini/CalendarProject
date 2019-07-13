package calendarProject;

public class Event implements Comparable<Event>{
	private String eventName;
	private int year;
	private int startingMonth;
	private int endingMonth;
	private String dayOfWeek;
	private int startingTime;
	private int endingTime;
	
	public Event(String eventName, int year, int startingMonth, int endingMonth,
				 String dayOfWeek, int startingTime, int endingTime) {
		this.eventName = eventName;
		this.year = year;
		this.startingMonth = startingMonth;
		this.endingMonth = endingMonth;
		this.dayOfWeek = dayOfWeek;
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
		return startingMonth;
	}
	
	public String dayOfWeek() {
		return dayOfWeek;
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
					int compareDayOfWeek = Character.compare(dayOfWeek.charAt(0), event.dayOfWeek.charAt(0));
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
