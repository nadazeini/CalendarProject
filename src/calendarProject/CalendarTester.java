package calendarProject;

public class CalendarTester {
	public static void main(String[] args) {
		DataModel dataModel = new DataModel();
		
		EventFrame eventFrame = new EventFrame(dataModel);
		CalendarFrame calendarFrame = new CalendarFrame(dataModel);
		
		eventFrame.setCalendarFrame(calendarFrame);
		calendarFrame.setEventFrame(eventFrame);
		
		dataModel.addChangeListener(eventFrame);
	}
}
