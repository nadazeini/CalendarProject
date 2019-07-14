package calendarProject;

public class SimpleFormatter implements EventFormatter{
	@Override
	public String formatEvent(Event event) {
		return event.getStartingTime() + " - " 
			 + event.getEndingTime() + ": " + event.getEventName() + '\n';
	}
}


