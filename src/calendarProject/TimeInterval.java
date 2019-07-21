package calendarProject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * class that represents an interval of time suitable for events
 * 
 *
 */
public class TimeInterval  {
	
	private LocalTime start;
	private LocalTime end;
	
	/**
	 * constructor
	 * @param start
	 * @param end
	 * @throws Exception 
	 */
	public TimeInterval(LocalTime start,LocalTime end) throws Exception {
		this.start = start;
		this.end = end;
	
		
	}
	/**
	 * returns true if there is no overlap between two timeIntervals
	 * @param t2 timeInterval to check
	 * @return boolean
	 */
	public boolean noOverlap(TimeInterval t2) {
		   return (this.start.isBefore(t2.start)&& this.end.isBefore(t2.start))
		   || (this.start.isAfter(t2.end) && this.end.isAfter(t2.end)) ;
		}
	/**
	 * sets start time
	 */
	public void setStart() {
		this.start=start;
	}
	/**
	 * sets end time
	 */
	public void setEnd() {
		this.end= end;
	}
	/**
	 * gets start time
	 * @return start
	 */
	public LocalTime getStart() {
		return start;
	}
	/**
	 * gets end time
	 * @return
	 */
	public LocalTime getEnd() {
		return end;
	}
	/** 
	 * returns timeInterval in string format
	 */
	public String toString() {

		return start.format(DateTimeFormatter.ofPattern("H:mm")) 
				+ "-"+end.format(DateTimeFormatter.ofPattern("H:mm"));
	}

	/** 
	 * returns timeInterval in string format without separator 
	 */
	public String toString1() {

		return start.format(DateTimeFormatter.ofPattern("H:mm")) 
				+ " "+end.format(DateTimeFormatter.ofPattern("H:mm"));
	}

 
}
