package calendarProject;

import java.time.LocalDate;
import java.util.HashMap;


public class CalendarTester {


	public static void main(String[] args) {
		
		HashMap<LocalDate, Event> hashMap = new HashMap<>();
		 DataModel dataModel = new DataModel(hashMap);
		

	        EventFrame eventFrame = new EventFrame();
	        CalendarFrame calendarFrame = new CalendarFrame(dataModel);
	        eventFrame.setCalendarFrame(calendarFrame);
	        calendarFrame.setEventFrame(eventFrame);
	        dataModel.addChangeListener(eventFrame);
	}

}
