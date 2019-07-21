package calendarProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Scanner;
	/**
	 * class represents an event 
	 * an event consists of the name and the timeInterval of that event
	 * 
	 *
	 */
public class Event implements Comparable<Event> {

		private String eventName;
		private TimeInterval timeInterval;

		public Event(String eventName, TimeInterval timeInterval ) {
			this.eventName= eventName;
			this.timeInterval= timeInterval;
			
		}
		public void setEventName() {
			this.eventName=eventName;
		}
		public void setTimeInterval() {
			this.timeInterval=timeInterval;
			
		}
		public String getEventName() {
			return eventName;
			
		}
	    public TimeInterval getTimeInterval() {
	    	return timeInterval;
	    }
	    public String toString() {
	    	return eventName + ": "+ timeInterval.toString();
	    }
	   /**
	    * compares events by time interval to sort later by timeInterval
	    */
		public int compareTo(Event e) {
				if(this.getTimeInterval().getStart().isAfter(e.getTimeInterval().getStart())) {
					return 1;
				}
				else if (this.getTimeInterval().getStart().isBefore(e.getTimeInterval().getStart()))
					return -1;
				else 
					return 0;
		}
	/**
	 * gets timeInterval and eventName into a string
	 * @return String
	 */
		 public String toString1() {


		return getTimeInterval().toString()+ " "+getEventName();
		 }
	}
	
/*	public static Map<String, Integer> DAYS_AND_WEEK = new HashMap<>() {
		private static final long serialVersionUID = 1L;
	{
		put("M", 1);
		put("T", 2);
		put("W", 3);
		put("R", 4);
		put("F", 5);
		put("A", 6);
		put("S", 7);
	}};
	public static Map<Integer, String> WEEK_AND_DAYS  = new HashMap<>() {
		private static final long serialVersionUID = 1L;
	{
		put(1, "M");
		put(2, "T");
		put(3, "W");
		put(4, "R");
		put(5, "F");
		put(6, "A");
		put(7, "S");
	}};
	private String eventName;
	private int year;
	private int startingMonth;
	private int endingMonth;
	private ArrayList<Integer> days;
	private int startingTime;
	private int endingTime;
	
	public Event(String eventName, int year, int startingMonth, int endingMonth,
				 String dayOfWeek, int startingTime, int endingTime) {
		days = new ArrayList<>();
		this.eventName = eventName;
		this.year = year;
		this.startingMonth = startingMonth;
		this.endingMonth = endingMonth;
		if(endingMonth == 0) {
			days.add(Integer.parseInt(dayOfWeek));
		}
		else {
			for(int i = 0; i < dayOfWeek.length(); i++) {
				days.add(DAYS_AND_WEEK.get(dayOfWeek.substring(i, i + 1)));
			}
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
	
	public ArrayList<Integer> getDays() {
		return days;
	}
	
	public int getStartingTime() {
		return startingTime;
	}
	
	public int getEndingTime() {
		return endingTime;
	}
>>>>>>> Stashed changes

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
        for (int i = 0; i < dayOfWeek.length(); i++) {
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

    public ArrayList<Integer> getDays() {
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
        if (compareYear < 0)
            return -1;
        else if (compareYear > 0)
            return 1;
        else {
            int compareStartingMonth = Integer.compare(startingMonth, event.startingMonth);
            if (compareStartingMonth < 0)
                return -1;
            else if (compareStartingMonth > 0)
                return 1;
            else {
                int compareEndingMonth = Integer.compare(endingMonth, event.endingMonth);
                if (compareEndingMonth < 0)
                    return -1;
                else if (compareStartingMonth > 0)
                    return 1;
                else {
                    int compareDayOfWeek = Integer.compare(days.get(0), days.get(0));
                    if (compareDayOfWeek < 0)
                        return -1;
                    else if (compareDayOfWeek > 0)
                        return 1;
                    else {
                        int compareStartingTime = Integer.compare(startingTime, event.startingTime);
                        if (compareStartingTime < 0)
                            return -1;
                        else if (compareStartingTime > 0)
                            return 1;
                        else {
                            int compareEndingTime = Integer.compare(endingTime, event.endingTime);
                            if (compareEndingTime < 0)
                                return -1;
                            else if (compareEndingTime > 0)
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
*/
