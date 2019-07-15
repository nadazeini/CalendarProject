package calendarProject;

public interface EventFormatter {
	String formatHeader();
	String formatEvent(Event event);
	String formatFooter();
}
